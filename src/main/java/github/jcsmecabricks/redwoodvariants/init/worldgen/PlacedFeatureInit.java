package github.jcsmecabricks.redwoodvariants.init.worldgen;

import github.jcsmecabricks.redwoodvariants.RedwoodVariants;
import github.jcsmecabricks.redwoodvariants.init.BlockInit;
import java.util.List;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;

public class PlacedFeatureInit {
    public static final ResourceKey<PlacedFeature> REDWOOD_PLACED_KEY = registerKey("redwood_placed");
    public static final ResourceKey<PlacedFeature> GIANT_REDWOOD_PLACED_KEY = registerKey("giant_redwood_placed");

    public static void boostrap(BootstrapContext<PlacedFeature> context) {
        var configuredFeatureRegistryEntryLookup = context.lookup(Registries.CONFIGURED_FEATURE);

        register(context, REDWOOD_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(ConfiguredFeatureInit.REDWOOD_TREE_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(2, 0.1f, 2),
                        BlockInit.REDWOOD_SAPLING));
        register(context, GIANT_REDWOOD_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(ConfiguredFeatureInit.GIANT_REDWOOD_TREE_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(2, 0.1f, 2),
                        BlockInit.REDWOOD_SAPLING));
    }

    public static ResourceKey<PlacedFeature> registerKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, Identifier.fromNamespaceAndPath(RedwoodVariants.MOD_ID, name));
    }

    private static void register(BootstrapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, Holder<ConfiguredFeature<?, ?>> configuration,
                                 List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }
}