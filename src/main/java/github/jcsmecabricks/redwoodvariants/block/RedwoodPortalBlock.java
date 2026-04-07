package github.jcsmecabricks.redwoodvariants.block;

import com.mojang.serialization.MapCodec;
import github.jcsmecabricks.redwoodvariants.world.dimension.ModDimensions;
import github.jcsmecabricks.redwoodvariants.world.dimension.ModTeleporter;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.BlockUtil;
import net.minecraft.util.RandomSource;
import net.minecraft.world.*;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.InsideBlockEffectApplier;
import net.minecraft.world.entity.Relative;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.Portal;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.border.WorldBorder;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.gamerules.GameRules;
import net.minecraft.world.level.portal.TeleportTransition;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import com.mojang.logging.LogUtils;
import java.util.Optional;


public class RedwoodPortalBlock extends Block implements Portal{
    public static final MapCodec<RedwoodPortalBlock> CODEC = simpleCodec(RedwoodPortalBlock::new);
    public static final EnumProperty<Direction.Axis> AXIS;
    private static final Logger LOGGER = LogUtils.getLogger();

    protected static final int field_31196 = 2;
    protected static final VoxelShape X_SHAPE;
    protected static final VoxelShape Z_SHAPE;

    public MapCodec<RedwoodPortalBlock> codec() {
        return CODEC;
    }

    public RedwoodPortalBlock(Properties settings) {
        super(settings);
        this.registerDefaultState(this.stateDefinition.any().setValue(AXIS, Direction.Axis.X));
    }

    public boolean trySpawnPortal(Level world, BlockPos pos) {
        Optional<RedwoodPortal> optionalPortal = RedwoodPortal.getNewPortal(world, pos, Direction.Axis.X);if (optionalPortal.isPresent()) {
        RedwoodPortal portal = optionalPortal.get();if (!portal.wasAlreadyValid()) {
            portal.createPortal(world);
            return true;
        }
    }
        return false;
    }

    public BlockState getStateForPlacement(BlockPlaceContext context) {
        Direction.Axis axis = context.getHorizontalDirection().getClockWise().getAxis();
        return this.defaultBlockState().setValue(AXIS, axis);
    }

    protected VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        switch (state.getValue(AXIS)) {
            case Z:
                return Z_SHAPE;
            case X:
            default:
                return X_SHAPE;
        }
    }

    protected BlockState updateShape(BlockState state, LevelReader world, ScheduledTickAccess tickView, BlockPos pos, Direction direction, BlockPos neighborPos, BlockState neighborState, RandomSource random) {
        Direction.Axis axis = direction.getAxis();
        Direction.Axis axis2 = state.getValue(AXIS);
        boolean bl = axis2 != axis && axis.isHorizontal();
        return !bl && !neighborState.is(this) && !RedwoodPortal.getOnAxis(world, pos, axis2).wasAlreadyValid() ? Blocks.AIR.defaultBlockState() : super.updateShape(state, world, tickView, pos, direction, neighborPos, neighborState, random);
    }

    @Override
    protected void entityInside(BlockState state, Level world, BlockPos pos, Entity entity, InsideBlockEffectApplier handler, boolean bl) {
        if (entity.canUsePortal(false)) {
            entity.setAsInsidePortal(this, pos);
        }
    }

    public int getPortalTransitionTime(ServerLevel world, Entity entity) {
        if (entity instanceof Player playerEntity) {
            return Math.max(0, world.getGameRules().get(playerEntity.getAbilities().invulnerable ? GameRules.PLAYERS_NETHER_PORTAL_CREATIVE_DELAY : GameRules.PLAYERS_NETHER_PORTAL_DEFAULT_DELAY));
        } else {
            return 0;
        }
    }

    @Nullable
    public TeleportTransition getPortalDestination(ServerLevel world, Entity entity, BlockPos pos) {
        ResourceKey<Level> registryKey = world.dimension() == ModDimensions.REDWOODDIM_LEVEL_KEY ? Level.OVERWORLD : ModDimensions.REDWOODDIM_LEVEL_KEY;
        ServerLevel serverWorld = world.getServer().getLevel(registryKey);
        if (serverWorld == null) {
            return null;
        } else {
            boolean bl = serverWorld.dimension() == ModDimensions.REDWOODDIM_LEVEL_KEY;
            WorldBorder worldBorder = serverWorld.getWorldBorder();
            double d = DimensionType.getTeleportationScale(world.dimensionType(), serverWorld.dimensionType());
            BlockPos blockPos = worldBorder.clampToBounds(entity.getX() * d, entity.getY(), entity.getZ() * d);
            return this.getOrCreateExitPortalTarget(serverWorld, entity, pos, blockPos, bl, worldBorder);
        }
    }

    @Nullable
    private TeleportTransition getOrCreateExitPortalTarget(ServerLevel world, Entity entity, BlockPos pos, BlockPos scaledPos, boolean inRedwood, WorldBorder worldBorder) {
        ModTeleporter modTeleporter = new ModTeleporter(world);
        Optional<BlockPos> optional = modTeleporter.getPortalPos(scaledPos, inRedwood, worldBorder);
        BlockUtil.FoundRectangle rectangle;
        TeleportTransition.PostTeleportTransition postDimensionTransition;
        if (optional.isPresent()) {
            BlockPos blockPos = optional.get();
            BlockState blockState = world.getBlockState(blockPos);
            rectangle = BlockUtil.getLargestRectangleAround(blockPos, blockState.getValue(BlockStateProperties.HORIZONTAL_AXIS), 21, Direction.Axis.Y, 21, (posx) -> world.getBlockState(posx) == blockState);
            postDimensionTransition = TeleportTransition.PLAY_PORTAL_SOUND.then((entityx) -> entityx.placePortalTicket(blockPos));
        } else {
            Direction.Axis axis = entity.level().getBlockState(pos).getOptionalValue(AXIS).orElse(Direction.Axis.X);
            Optional<BlockUtil.FoundRectangle> optional2 = modTeleporter.createPortal(scaledPos, axis);
            if (optional2.isEmpty()) {
                LOGGER.error("Unable to create a portal, likely target out of worldborder");
                return null;
            }

            rectangle = (BlockUtil.FoundRectangle)optional2.get();
            postDimensionTransition = TeleportTransition.PLAY_PORTAL_SOUND.then(TeleportTransition.PLACE_PORTAL_TICKET);
        }

        return getExitPortalTarget(entity, pos, rectangle, world, postDimensionTransition);
    }

    private static TeleportTransition getExitPortalTarget(Entity entity, BlockPos pos, BlockUtil.FoundRectangle exitPortalRectangle, ServerLevel world, TeleportTransition.PostTeleportTransition postDimensionTransition) {
        BlockState blockState = entity.level().getBlockState(pos);
        Direction.Axis axis;
        Vec3 vec3d;
        if (blockState.hasProperty(BlockStateProperties.HORIZONTAL_AXIS)) {
            axis = blockState.getValue(BlockStateProperties.HORIZONTAL_AXIS);
            BlockUtil.FoundRectangle rectangle = BlockUtil.getLargestRectangleAround(pos, axis, 21, Direction.Axis.Y, 21, (posx) -> entity.level().getBlockState(posx) == blockState);
            vec3d = entity.getRelativePortalPosition(axis, rectangle);
        } else {
            axis = Direction.Axis.X;
            vec3d = new Vec3(0.5F, 0.0F, 0.0F);
        }

        return getExitPortalTarget(world, exitPortalRectangle, axis, vec3d, entity, postDimensionTransition);
    }

    private static TeleportTransition getExitPortalTarget(ServerLevel world, BlockUtil.FoundRectangle exitPortalRectangle, Direction.Axis axis, Vec3 positionInPortal, Entity entity, TeleportTransition.PostTeleportTransition postDimensionTransition) {
        BlockPos blockPos = exitPortalRectangle.minCorner;
        BlockState blockState = world.getBlockState(blockPos);
        Direction.Axis axis2 = blockState.getOptionalValue(BlockStateProperties.HORIZONTAL_AXIS).orElse(Direction.Axis.X);
        double d = exitPortalRectangle.axis1Size;
        double e = exitPortalRectangle.axis2Size;
        EntityDimensions entityDimensions = entity.getDimensions(entity.getPose());
        int i = axis == axis2 ? 0 : 90;
        double f = (double)entityDimensions.width() / (double)2.0F + (d - (double)entityDimensions.width()) * positionInPortal.x();
        double g = (e - (double)entityDimensions.height()) * positionInPortal.y();
        double h = (double)0.5F + positionInPortal.z();
        boolean bl = axis2 == Direction.Axis.X;
        Vec3 vec3d = new Vec3((double)blockPos.getX() + (bl ? f : h), (double)blockPos.getY() + g, (double)blockPos.getZ() + (bl ? h : f));
        Vec3 vec3d2 = RedwoodPortal.findOpenPosition(vec3d, world, entity, entityDimensions);
        return new TeleportTransition(world, vec3d2, Vec3.ZERO, (float)i, 0.0F, Relative.union(Relative.DELTA, Relative.ROTATION), postDimensionTransition);
    }

    public Transition getLocalTransition() {
        return Transition.CONFUSION;
    }

    public void animateTick(BlockState state, Level world, BlockPos pos, RandomSource random) {
        if (random.nextInt(100) == 0) {
            world.playSound((null), pos, SoundEvents.PORTAL_AMBIENT, SoundSource.BLOCKS, 0.5F, random.nextFloat() * 0.4F + 0.8F);
        }

        for(int i = 0; i < 4; ++i) {
            double d = (double)pos.getX() + random.nextDouble();
            double e = (double)pos.getY() + random.nextDouble();
            double f = (double)pos.getZ() + random.nextDouble();
            double g = ((double)random.nextFloat() - (double)0.5F) * (double)0.5F;
            double h = ((double)random.nextFloat() - (double)0.5F) * (double)0.5F;
            double j = ((double)random.nextFloat() - (double)0.5F) * (double)0.5F;
            int k = random.nextInt(2) * 2 - 1;
            if (!world.getBlockState(pos.west()).is(this) && !world.getBlockState(pos.east()).is(this)) {
                d = (double)pos.getX() + (double)0.5F + (double)0.25F * (double)k;
                g = random.nextFloat() * 2.0F * (float)k;
            } else {
                f = (double)pos.getZ() + (double)0.5F + (double)0.25F * (double)k;
                j = random.nextFloat() * 2.0F * (float)k;
            }
        }

    }

    protected ItemStack getCloneItemStack(LevelReader world, BlockPos pos, BlockState state, boolean includeData) {
        return ItemStack.EMPTY;
    }

    protected BlockState rotate(BlockState state, Rotation rotation) {
        switch (rotation) {
            case COUNTERCLOCKWISE_90:
            case CLOCKWISE_90:
                switch (state.getValue(AXIS)) {
                    case Z -> {
                        return state.setValue(AXIS, Direction.Axis.X);
                    }
                    case X -> {
                        return state.setValue(AXIS, Direction.Axis.Z);
                    }
                    default -> {
                        return state;
                    }
                }
            default:
                return state;
        }
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(AXIS);
    }

    static {
        AXIS = BlockStateProperties.HORIZONTAL_AXIS;
        X_SHAPE = Block.box(0.0F, 0.0F, 6.0F, 16.0F, 16.0F, 10.0F);
        Z_SHAPE = Block.box(6.0F, 0.0F, 0.0F, 10.0F, 16.0F, 16.0F);
    }
}
