package github.jcsmecabricks.redwoodvariants.init.worldgen;

import github.jcsmecabricks.redwoodvariants.RedwoodVariants;
import github.jcsmecabricks.redwoodvariants.init.BlockInit;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.TreeFeature;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;

public class ConfiguredFeatureInit {
    public static final ResourceKey<Feature> REDWOOD_TREE_KEY = registerKey("redwood_tree");
    public static final ResourceKey<Feature> GIANT_REDWOOD_TREE_KEY = registerKey("giant_redwood_tree");

    public static void boostrap(BootstrapContext<Feature> context) {

        context.register(REDWOOD_TREE_KEY, new TreeFeature.Builder(
                BlockStateProvider.of(BlockInit.REDWOOD_LOG),
                new StraightTrunkPlacer(5, 6, 3),

                BlockStateProvider.of(BlockInit.REDWOOD_LEAVES),
                new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 4),

                new TwoLayersFeatureSize(1, 0, 2),
                BlockStateProvider.holderOf(Blocks.DIRT)).build());

        context.register(GIANT_REDWOOD_TREE_KEY, new TreeFeature.Builder(
                BlockStateProvider.of(BlockInit.REDWOOD_LOG),
                new StraightTrunkPlacer(5, 6, 3),

                BlockStateProvider.of(BlockInit.REDWOOD_LEAVES),
                new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 4),

                new TwoLayersFeatureSize(1, 0, 2),
                BlockStateProvider.holderOf(Blocks.DIRT)).build());
    }

    public static ResourceKey<Feature> registerKey(String name) {
        return ResourceKey.create(Registries.FEATURE, Identifier.fromNamespaceAndPath(RedwoodVariants.MOD_ID, name));
    }


}
