package github.jcsmecabricks.redwoodvariants.block;

import github.jcsmecabricks.redwoodvariants.init.BlockInit;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.BlockUtil;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.apache.commons.lang3.mutable.MutableInt;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;
import java.util.function.Predicate;

public class RedwoodPortal {
    private static final int MIN_WIDTH = 2;
    public static final int MAX_WIDTH = 21;
    private static final int MIN_HEIGHT = 3;
    public static final int MAX_HEIGHT = 21;
    private static final BlockBehaviour.StatePredicate IS_VALID_FRAME_BLOCK = (state, world, pos) -> state.is(BlockInit.REDWOOD_PLANKS);
    private static final float FALLBACK_THRESHOLD = 4.0F;
    private static final double HEIGHT_STRETCH = (double)1.0F;
    private final Direction.Axis axis;
    private final Direction negativeDir;
    private final int foundPortalBlocks;
    private final BlockPos lowerCorner;
    private final int height;
    private final int width;

    private RedwoodPortal(Direction.Axis axis, int foundPortalBlocks, Direction negativeDir, BlockPos lowerCorner, int width, int height) {
        this.axis = axis;
        this.foundPortalBlocks = foundPortalBlocks;
        this.negativeDir = negativeDir;
        this.lowerCorner = lowerCorner;
        this.width = width;
        this.height = height;
    }
    public static Optional<RedwoodPortal> getNewPortal(LevelAccessor world, BlockPos pos, Direction.Axis firstCheckedAxis) {
        return getOrEmpty(world, pos, (areaHelper) -> areaHelper.isValid() && areaHelper.foundPortalBlocks == 0, firstCheckedAxis);
    }

    public static Optional<RedwoodPortal> getOrEmpty(LevelAccessor world, BlockPos pos, Predicate<RedwoodPortal> validator, Direction.Axis firstCheckedAxis) {
        Optional<RedwoodPortal> optional = Optional.of(getOnAxis(world, pos, firstCheckedAxis)).filter(validator);
        if (optional.isPresent()) {
            return optional;
        } else {
            Direction.Axis axis = firstCheckedAxis == Direction.Axis.X ? Direction.Axis.Z : Direction.Axis.X;
            return Optional.of(getOnAxis(world, pos, axis)).filter(validator);
        }
    }

    public static RedwoodPortal getOnAxis(BlockGetter world, BlockPos pos, Direction.Axis axis) {
        Direction direction = axis == Direction.Axis.X ? Direction.WEST : Direction.SOUTH;
        BlockPos blockPos = getLowerCorner(world, direction, pos);
        if (blockPos == null) {
            return new RedwoodPortal(axis, 0, direction, pos, 0, 0);
        } else {
            int i = getValidatedWidth(world, blockPos, direction);
            if (i == 0) {
                return new RedwoodPortal(axis, 0, direction, blockPos, 0, 0);
            } else {
                MutableInt mutableInt = new MutableInt();
                int j = getHeight(world, blockPos, direction, i, mutableInt);
                return new RedwoodPortal(axis, mutableInt.getValue(), direction, blockPos, i, j);
            }
        }
    }

    @Nullable
    private static BlockPos getLowerCorner(BlockGetter world, Direction direction, BlockPos pow) {
        for(int i = Math.max(world.getMinY(), pow.getY() - 21); pow.getY() > i && validStateInsidePortal(world.getBlockState(pow.below())); pow = pow.below()) {
        }

        Direction direction2 = direction.getOpposite();
        int j = getWidth(world, pow, direction2) - 1;
        return j < 0 ? null : pow.relative(direction2, j);
    }

    private static int getValidatedWidth(BlockGetter world, BlockPos lowerCorner, Direction negativeDir) {
        int i = getWidth(world, lowerCorner, negativeDir);
        return i >= 2 && i <= 21 ? i : 0;
    }

    private static int getWidth(BlockGetter world, BlockPos lowerCorner, Direction negativeDir) {
        BlockPos.MutableBlockPos mutable = new BlockPos.MutableBlockPos();

        for(int i = 0; i <= 21; ++i) {
            mutable.set(lowerCorner).move(negativeDir, i);
            BlockState blockState = world.getBlockState(mutable);
            if (!validStateInsidePortal(blockState)) {
                if (IS_VALID_FRAME_BLOCK.test(blockState, world, mutable)) {
                    return i;
                }
                break;
            }

            BlockState blockState2 = world.getBlockState(mutable.move(Direction.DOWN));
            if (!IS_VALID_FRAME_BLOCK.test(blockState2, world, mutable)) {
                break;
            }
        }

        return 0;
    }

    private static int getHeight(BlockGetter world, BlockPos lowerCorner, Direction negativeDir, int width, MutableInt foundPortalBlocks) {
        BlockPos.MutableBlockPos mutable = new BlockPos.MutableBlockPos();
        int i = getPotentialHeight(world, lowerCorner, negativeDir, mutable, width, foundPortalBlocks);
        return i >= 3 && i <= 21 && isHorizontalFrameValid(world, lowerCorner, negativeDir, mutable, width, i) ? i : 0;
    }

    private static boolean isHorizontalFrameValid(BlockGetter world, BlockPos lowerCorner, Direction direction, BlockPos.MutableBlockPos pos, int width, int height) {
        for(int i = 0; i < width; ++i) {
            BlockPos.MutableBlockPos mutable = pos.set(lowerCorner).move(Direction.UP, height).move(direction, i);
            if (!IS_VALID_FRAME_BLOCK.test(world.getBlockState(mutable), world, mutable)) {
                return false;
            }
        }

        return true;
    }

    private static int getPotentialHeight(BlockGetter world, BlockPos lowerCorner, Direction negativeDir, BlockPos.MutableBlockPos pos, int width, MutableInt foundPortalBlocks) {
        for(int i = 0; i < 21; ++i) {
            pos.set(lowerCorner).move(Direction.UP, i).move(negativeDir, -1);
            if (!IS_VALID_FRAME_BLOCK.test(world.getBlockState(pos), world, pos)) {
                return i;
            }

            pos.set(lowerCorner).move(Direction.UP, i).move(negativeDir, width);
            if (!IS_VALID_FRAME_BLOCK.test(world.getBlockState(pos), world, pos)) {
                return i;
            }

            for(int j = 0; j < width; ++j) {
                pos.set(lowerCorner).move(Direction.UP, i).move(negativeDir, j);
                BlockState blockState = world.getBlockState(pos);
                if (!validStateInsidePortal(blockState)) {
                    return i;
                }

                if (blockState.is(BlockInit.REDWOOD_PORTAL)) {
                    foundPortalBlocks.increment();
                }
            }
        }

        return 21;
    }

    private static boolean validStateInsidePortal(BlockState state) {
        return state.isAir() || state.is(BlockTags.FIRE) || state.is(BlockInit.REDWOOD_PORTAL);
    }

    public boolean isValid() {
        return this.width >= 2 && this.width <= 21 && this.height >= 3 && this.height <= 21;
    }

    public void createPortal(LevelAccessor world) {
        BlockState blockState = BlockInit.REDWOOD_PORTAL.defaultBlockState().setValue(RedwoodPortalBlock.AXIS, this.axis);
        BlockPos.betweenClosed(this.lowerCorner, this.lowerCorner.relative(Direction.UP, this.height - 1).relative(this.negativeDir, this.width - 1)).forEach((pos) -> world.setBlock(pos, blockState, 18));
    }

    public boolean wasAlreadyValid() {
        return this.isValid() && this.foundPortalBlocks == this.width * this.height;
    }

    public static Vec3 entityPosInPortal(BlockUtil.FoundRectangle portalRect, Direction.Axis portalAxis, Vec3 entityPos, EntityDimensions entityDimensions) {
        double d = (double)portalRect.axis1Size - (double)entityDimensions.width();
        double e = (double)portalRect.axis2Size - (double)entityDimensions.height();
        BlockPos blockPos = portalRect.minCorner;
        double g;
        if (d > (double)0.0F) {
            double f = (double)blockPos.get(portalAxis) + (double)entityDimensions.width() / (double)2.0F;
            g = Mth.clamp(Mth.inverseLerp(entityPos.get(portalAxis) - f, (double)0.0F, d), (double)0.0F, (double)1.0F);
        } else {
            g = (double)0.5F;
        }

        double f;
        if (e > (double)0.0F) {
            Direction.Axis axis = Direction.Axis.Y;
            f = Mth.clamp(Mth.inverseLerp(entityPos.get(axis) - (double)blockPos.get(axis), (double)0.0F, e), (double)0.0F, (double)1.0F);
        } else {
            f = (double)0.0F;
        }

        Direction.Axis axis = portalAxis == Direction.Axis.X ? Direction.Axis.Z : Direction.Axis.X;
        double h = entityPos.get(axis) - ((double)blockPos.get(axis) + (double)0.5F);
        return new Vec3(g, f, h);
    }

    public static Vec3 findOpenPosition(Vec3 fallback, ServerLevel world, Entity entity, EntityDimensions dimensions) {
        if (!(dimensions.width() > 4.0F) && !(dimensions.height() > 4.0F)) {
            double d = (double)dimensions.height() / (double)2.0F;
            Vec3 vec3d = fallback.add((double)0.0F, d, (double)0.0F);
            VoxelShape voxelShape = Shapes.create(AABB.ofSize(vec3d, (double)dimensions.width(), (double)0.0F, (double)dimensions.width()).expandTowards((double)0.0F, (double)1.0F, (double)0.0F).inflate(1.0E-6));
            Optional<Vec3> optional = world.findFreePosition(entity, voxelShape, vec3d, (double)dimensions.width(), (double)dimensions.height(), (double)dimensions.width());
            Optional<Vec3> optional2 = optional.map((pos) -> pos.subtract((double)0.0F, d, (double)0.0F));
            return (Vec3)optional2.orElse(fallback);
        } else {
            return fallback;
        }
    }
}
