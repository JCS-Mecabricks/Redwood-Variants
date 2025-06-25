package github.jcsmecabricks.redwoodvariants.block;

import com.mojang.logging.LogUtils;
import com.mojang.serialization.MapCodec;
import github.jcsmecabricks.redwoodvariants.world.dimension.ModDimensions;
import github.jcsmecabricks.redwoodvariants.world.dimension.ModTeleporter;
import net.minecraft.block.*;
import net.minecraft.entity.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.network.packet.s2c.play.PositionFlag;
import net.minecraft.registry.RegistryKey;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.*;
import net.minecraft.world.border.WorldBorder;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.tick.ScheduledTickView;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;

import java.util.Optional;

public class RedwoodPortalBlock extends Block implements Portal{
    public static final MapCodec<RedwoodPortalBlock> CODEC = createCodec(RedwoodPortalBlock::new);
    public static final EnumProperty<Direction.Axis> AXIS;
    private static final Logger LOGGER;
    protected static final int field_31196 = 2;
    protected static final VoxelShape X_SHAPE;
    protected static final VoxelShape Z_SHAPE;

    public MapCodec<RedwoodPortalBlock> getCodec() {
        return CODEC;
    }

    public RedwoodPortalBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(AXIS, Direction.Axis.X));
    }

    public boolean trySpawnPortal(World world, BlockPos pos) {
        Optional<RedwoodPortal> optionalPortal = RedwoodPortal.getNewPortal(world, pos, Direction.Axis.X);if (optionalPortal.isPresent()) {
        RedwoodPortal portal = optionalPortal.get();if (!portal.wasAlreadyValid()) {
            portal.createPortal(world);
            return true;
        }
    }
        return false;
    }

    public BlockState getPlacementState(ItemPlacementContext context) {
        Direction.Axis axis = context.getHorizontalPlayerFacing().rotateYClockwise().getAxis();
        return this.getDefaultState().with(AXIS, axis);
    }

    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        switch (state.get(AXIS)) {
            case Z:
                return Z_SHAPE;
            case X:
            default:
                return X_SHAPE;
        }
    }

    protected BlockState getStateForNeighborUpdate(BlockState state, WorldView world, ScheduledTickView tickView, BlockPos pos, Direction direction, BlockPos neighborPos, BlockState neighborState, Random random) {
        Direction.Axis axis = direction.getAxis();
        Direction.Axis axis2 = state.get(AXIS);
        boolean bl = axis2 != axis && axis.isHorizontal();
        return !bl && !neighborState.isOf(this) && !RedwoodPortal.getOnAxis(world, pos, axis2).wasAlreadyValid() ? Blocks.AIR.getDefaultState() : super.getStateForNeighborUpdate(state, world, tickView, pos, direction, neighborPos, neighborState, random);
    }

    @Override
    protected void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity, EntityCollisionHandler handler) {
        if (entity.canUsePortals(false)) {
            entity.tryUsePortal(this, pos);
        }
    }

    public int getPortalDelay(ServerWorld world, Entity entity) {
        if (entity instanceof PlayerEntity playerEntity) {
            return Math.max(0, world.getGameRules().getInt(playerEntity.getAbilities().invulnerable ? GameRules.PLAYERS_NETHER_PORTAL_CREATIVE_DELAY : GameRules.PLAYERS_NETHER_PORTAL_DEFAULT_DELAY));
        } else {
            return 0;
        }
    }

    @Nullable
    public TeleportTarget createTeleportTarget(ServerWorld world, Entity entity, BlockPos pos) {
        RegistryKey<World> registryKey = world.getRegistryKey() == ModDimensions.REDWOODDIM_LEVEL_KEY ? World.OVERWORLD : ModDimensions.REDWOODDIM_LEVEL_KEY;
        ServerWorld serverWorld = world.getServer().getWorld(registryKey);
        if (serverWorld == null) {
            return null;
        } else {
            boolean bl = serverWorld.getRegistryKey() == ModDimensions.REDWOODDIM_LEVEL_KEY;
            WorldBorder worldBorder = serverWorld.getWorldBorder();
            double d = DimensionType.getCoordinateScaleFactor(world.getDimension(), serverWorld.getDimension());
            BlockPos blockPos = worldBorder.clampFloored(entity.getX() * d, entity.getY(), entity.getZ() * d);
            return this.getOrCreateExitPortalTarget(serverWorld, entity, pos, blockPos, bl, worldBorder);
        }
    }

    @Nullable
    private TeleportTarget getOrCreateExitPortalTarget(ServerWorld world, Entity entity, BlockPos pos, BlockPos scaledPos, boolean inRedwood, WorldBorder worldBorder) {
        ModTeleporter modTeleporter = new ModTeleporter(world);
        Optional<BlockPos> optional = modTeleporter.getPortalPos(scaledPos, inRedwood, worldBorder);
        BlockLocating.Rectangle rectangle;
        TeleportTarget.PostDimensionTransition postDimensionTransition;
        if (optional.isPresent()) {
            BlockPos blockPos = optional.get();
            BlockState blockState = world.getBlockState(blockPos);
            rectangle = BlockLocating.getLargestRectangle(blockPos, blockState.get(Properties.HORIZONTAL_AXIS), 21, Direction.Axis.Y, 21, (posx) -> world.getBlockState(posx) == blockState);
            postDimensionTransition = TeleportTarget.SEND_TRAVEL_THROUGH_PORTAL_PACKET.then((entityx) -> entityx.addPortalChunkTicketAt(blockPos));
        } else {
            Direction.Axis axis = entity.getWorld().getBlockState(pos).getOrEmpty(AXIS).orElse(Direction.Axis.X);
            Optional<BlockLocating.Rectangle> optional2 = modTeleporter.createPortal(scaledPos, axis);
            if (optional2.isEmpty()) {
                LOGGER.error("Unable to create a portal, likely target out of worldborder");
                return null;
            }

            rectangle = (BlockLocating.Rectangle)optional2.get();
            postDimensionTransition = TeleportTarget.SEND_TRAVEL_THROUGH_PORTAL_PACKET.then(TeleportTarget.ADD_PORTAL_CHUNK_TICKET);
        }

        return getExitPortalTarget(entity, pos, rectangle, world, postDimensionTransition);
    }

    private static TeleportTarget getExitPortalTarget(Entity entity, BlockPos pos, BlockLocating.Rectangle exitPortalRectangle, ServerWorld world, TeleportTarget.PostDimensionTransition postDimensionTransition) {
        BlockState blockState = entity.getWorld().getBlockState(pos);
        Direction.Axis axis;
        Vec3d vec3d;
        if (blockState.contains(Properties.HORIZONTAL_AXIS)) {
            axis = blockState.get(Properties.HORIZONTAL_AXIS);
            BlockLocating.Rectangle rectangle = BlockLocating.getLargestRectangle(pos, axis, 21, Direction.Axis.Y, 21, (posx) -> entity.getWorld().getBlockState(posx) == blockState);
            vec3d = entity.positionInPortal(axis, rectangle);
        } else {
            axis = Direction.Axis.X;
            vec3d = new Vec3d(0.5F, 0.0F, 0.0F);
        }

        return getExitPortalTarget(world, exitPortalRectangle, axis, vec3d, entity, postDimensionTransition);
    }

    private static TeleportTarget getExitPortalTarget(ServerWorld world, BlockLocating.Rectangle exitPortalRectangle, Direction.Axis axis, Vec3d positionInPortal, Entity entity, TeleportTarget.PostDimensionTransition postDimensionTransition) {
        BlockPos blockPos = exitPortalRectangle.lowerLeft;
        BlockState blockState = world.getBlockState(blockPos);
        Direction.Axis axis2 = blockState.getOrEmpty(Properties.HORIZONTAL_AXIS).orElse(Direction.Axis.X);
        double d = exitPortalRectangle.width;
        double e = exitPortalRectangle.height;
        EntityDimensions entityDimensions = entity.getDimensions(entity.getPose());
        int i = axis == axis2 ? 0 : 90;
        double f = (double)entityDimensions.width() / (double)2.0F + (d - (double)entityDimensions.width()) * positionInPortal.getX();
        double g = (e - (double)entityDimensions.height()) * positionInPortal.getY();
        double h = (double)0.5F + positionInPortal.getZ();
        boolean bl = axis2 == Direction.Axis.X;
        Vec3d vec3d = new Vec3d((double)blockPos.getX() + (bl ? f : h), (double)blockPos.getY() + g, (double)blockPos.getZ() + (bl ? h : f));
        Vec3d vec3d2 = RedwoodPortal.findOpenPosition(vec3d, world, entity, entityDimensions);
        return new TeleportTarget(world, vec3d2, Vec3d.ZERO, (float)i, 0.0F, PositionFlag.combine(PositionFlag.DELTA, PositionFlag.ROT), postDimensionTransition);
    }

    public Effect getPortalEffect() {
        return Effect.CONFUSION;
    }

    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        if (random.nextInt(100) == 0) {
            world.playSound((null), pos, SoundEvents.BLOCK_PORTAL_AMBIENT, SoundCategory.BLOCKS, 0.5F, random.nextFloat() * 0.4F + 0.8F);
        }

        for(int i = 0; i < 4; ++i) {
            double d = (double)pos.getX() + random.nextDouble();
            double e = (double)pos.getY() + random.nextDouble();
            double f = (double)pos.getZ() + random.nextDouble();
            double g = ((double)random.nextFloat() - (double)0.5F) * (double)0.5F;
            double h = ((double)random.nextFloat() - (double)0.5F) * (double)0.5F;
            double j = ((double)random.nextFloat() - (double)0.5F) * (double)0.5F;
            int k = random.nextInt(2) * 2 - 1;
            if (!world.getBlockState(pos.west()).isOf(this) && !world.getBlockState(pos.east()).isOf(this)) {
                d = (double)pos.getX() + (double)0.5F + (double)0.25F * (double)k;
                g = random.nextFloat() * 2.0F * (float)k;
            } else {
                f = (double)pos.getZ() + (double)0.5F + (double)0.25F * (double)k;
                j = random.nextFloat() * 2.0F * (float)k;
            }
        }

    }

    protected ItemStack getPickStack(WorldView world, BlockPos pos, BlockState state, boolean includeData) {
        return ItemStack.EMPTY;
    }

    protected BlockState rotate(BlockState state, BlockRotation rotation) {
        switch (rotation) {
            case COUNTERCLOCKWISE_90:
            case CLOCKWISE_90:
                switch (state.get(AXIS)) {
                    case Z -> {
                        return state.with(AXIS, Direction.Axis.X);
                    }
                    case X -> {
                        return state.with(AXIS, Direction.Axis.Z);
                    }
                    default -> {
                        return state;
                    }
                }
            default:
                return state;
        }
    }

    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(AXIS);
    }

    static {
        AXIS = Properties.HORIZONTAL_AXIS;
        LOGGER = LogUtils.getLogger();
        X_SHAPE = Block.createCuboidShape(0.0F, 0.0F, 6.0F, 16.0F, 16.0F, 10.0F);
        Z_SHAPE = Block.createCuboidShape(6.0F, 0.0F, 0.0F, 10.0F, 16.0F, 16.0F);
    }
}
