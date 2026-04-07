package github.jcsmecabricks.redwoodvariants.world.dimension;

import github.jcsmecabricks.redwoodvariants.block.RedwoodPortalBlock;
import github.jcsmecabricks.redwoodvariants.init.BlockInit;
import github.jcsmecabricks.redwoodvariants.util.ModPointOfInterestTypes;
import java.util.Comparator;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Vec3i;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.BlockUtil;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.ai.village.poi.PoiManager;
import net.minecraft.world.entity.ai.village.poi.PoiRecord;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.border.WorldBorder;
import net.minecraft.world.level.levelgen.Heightmap;

public class ModTeleporter {
    public static final int field_31810 = 3;
    private static final int field_52248 = 16;
    private static final int field_52249 = 128;
    private static final int field_31813 = 5;
    private static final int field_31814 = 4;
    private static final int field_31815 = 3;
    private static final int field_31816 = -1;
    private static final int field_31817 = 4;
    private static final int field_31818 = -1;
    private static final int field_31819 = 3;
    private static final int field_31820 = -1;
    private static final int field_31821 = 2;
    private static final int field_31822 = -1;
    private final ServerLevel world;

    public ModTeleporter(ServerLevel world) {
        this.world = world;
    }

    public Optional<BlockPos> getPortalPos(BlockPos pos, boolean destIsRedwood, WorldBorder worldBorder) {
        PoiManager pointOfInterestStorage = this.world.getPoiManager();
        int i = destIsRedwood ? 16 : 128;
        pointOfInterestStorage.ensureLoadedAndValid(this.world, pos, i);
        Stream<BlockPos> var10000 = pointOfInterestStorage.getInSquare( poiType -> poiType.value() == ModPointOfInterestTypes.REDWOOD_PORTAL, pos, i, PoiManager.Occupancy.ANY).map(PoiRecord::getPos);
        Objects.requireNonNull(worldBorder);
        return var10000.filter(worldBorder::isWithinBounds).filter((portalPos) -> this.world.getBlockState(portalPos).hasProperty(BlockStateProperties.HORIZONTAL_AXIS)).min(Comparator.comparingDouble((BlockPos portalPos) -> portalPos.distSqr(pos)).thenComparingInt(Vec3i::getY));
    }


    public Optional<BlockUtil.FoundRectangle> createPortal(BlockPos pos, Direction.Axis axis) {
        Direction direction = Direction.get(Direction.AxisDirection.POSITIVE, axis);
        double d = -1.0F;
        BlockPos blockPos = null;
        double e = -1.0F;
        BlockPos blockPos2 = null;
        WorldBorder worldBorder = this.world.getWorldBorder();
        int i = Math.min(this.world.getMaxY(), this.world.getMinY() + this.world.getLogicalHeight() - 1);
        int j = 1;
        BlockPos.MutableBlockPos mutable = pos.mutable();

        for(BlockPos.MutableBlockPos mutable2 : BlockPos.spiralAround(pos, 16, Direction.EAST, Direction.SOUTH)) {
            int k = Math.min(i, this.world.getHeight(Heightmap.Types.MOTION_BLOCKING, mutable2.getX(), mutable2.getZ()));
            if (worldBorder.isWithinBounds(mutable2) && worldBorder.isWithinBounds(mutable2.move(direction, 1))) {
                mutable2.move(direction.getOpposite(), 1);

                for(int l = k; l >= this.world.getMinY(); --l) {
                    mutable2.setY(l);
                    if (this.isBlockStateValid(mutable2)) {
                        int m;
                        for(m = l; l > this.world.getMinY() && this.isBlockStateValid(mutable2.move(Direction.DOWN)); --l) {
                        }

                        if (l + 4 <= i) {
                            int n = m - l;
                            if (n <= 0 || n >= 3) {
                                mutable2.setY(l);
                                if (this.isValidPortalPos(mutable2, mutable, direction, 0)) {
                                    double f = pos.distSqr(mutable2);
                                    if (this.isValidPortalPos(mutable2, mutable, direction, -1) && this.isValidPortalPos(mutable2, mutable, direction, 1) && (d == (double)-1.0F || d > f)) {
                                        d = f;
                                        blockPos = mutable2.immutable();
                                    }

                                    if (d == (double)-1.0F && (e == (double)-1.0F || e > f)) {
                                        e = f;
                                        blockPos2 = mutable2.immutable();
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        if (d == (double)-1.0F && e != (double)-1.0F) {
            blockPos = blockPos2;
            d = e;
        }

        if (d == (double)-1.0F) {
            int o = Math.max(this.world.getMinY() - -1, 70);
            int p = i - 9;
            if (p < o) {
                return Optional.empty();
            }

            blockPos = (new BlockPos(pos.getX() - direction.getStepX() * 1, Mth.clamp(pos.getY(), o, p), pos.getZ() - direction.getStepZ() * 1)).immutable();
            blockPos = worldBorder.clampToBounds(blockPos);
            Direction direction2 = direction.getClockWise();

            for(int l = -1; l < 2; ++l) {
                for(int m = 0; m < 2; ++m) {
                    for(int n = -1; n < 3; ++n) {
                        BlockState blockState = n < 0 ? BlockInit.REDWOOD_PLANKS.defaultBlockState() : Blocks.AIR.defaultBlockState();
                        mutable.setWithOffset(blockPos, m * direction.getStepX() + l * direction2.getStepX(), n, m * direction.getStepZ() + l * direction2.getStepZ());
                        this.world.setBlockAndUpdate(mutable, blockState);
                    }
                }
            }
        }

        for(int o = -1; o < 3; ++o) {
            for(int p = -1; p < 4; ++p) {
                if (o == -1 || o == 2 || p == -1 || p == 3) {
                    mutable.setWithOffset(blockPos, o * direction.getStepX(), p, o * direction.getStepZ());
                    this.world.setBlock(mutable, BlockInit.REDWOOD_PLANKS.defaultBlockState(), 3);
                }
            }
        }

        BlockState blockState2 = BlockInit.REDWOOD_PORTAL.defaultBlockState().setValue(RedwoodPortalBlock.AXIS, axis);

        for(int p = 0; p < 2; ++p) {
            for(int k = 0; k < 3; ++k) {
                mutable.setWithOffset(blockPos, p * direction.getStepX(), k, p * direction.getStepZ());
                this.world.setBlock(mutable, blockState2, 18);
            }
        }

        return Optional.of(new BlockUtil.FoundRectangle(blockPos.immutable(), 2, 3));
    }


    private boolean isBlockStateValid(BlockPos.MutableBlockPos pos) {
        BlockState blockState = this.world.getBlockState(pos);
        return blockState.canBeReplaced() && blockState.getFluidState().isEmpty();
    }

    private boolean isValidPortalPos(BlockPos pos, BlockPos.MutableBlockPos temp, Direction portalDirection, int distanceOrthogonalToPortal) {
        Direction direction = portalDirection.getClockWise();

        for(int i = -1; i < 3; ++i) {
            for(int j = -1; j < 4; ++j) {
                temp.setWithOffset(pos, portalDirection.getStepX() * i + direction.getStepX() * distanceOrthogonalToPortal, j, portalDirection.getStepZ() * i + direction.getStepZ() * distanceOrthogonalToPortal);
                if (j < 0 && !this.world.getBlockState(temp).isSolid()) {
                    return false;
                }

                if (j >= 0 && !this.isBlockStateValid(temp)) {
                    return false;
                }
            }
        }

        return true;
    }
}
