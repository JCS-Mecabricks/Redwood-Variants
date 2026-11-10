package github.jcsmecabricks.redwoodvariants.init.worldgen.tree;

import github.jcsmecabricks.redwoodvariants.RedwoodVariants;
import github.jcsmecabricks.redwoodvariants.init.worldgen.ConfiguredFeatureInit;
import java.util.Optional;

import net.minecraft.util.random.WeightedList;
import net.minecraft.world.level.block.grower.TreeGrower;

public class ModSaplingGenerators {
    public static final TreeGrower REDWOOD = new
            TreeGrower(RedwoodVariants.MOD_ID + ":redwood",
            WeightedList.of(ConfiguredFeatureInit.REDWOOD_TREE_KEY), WeightedList.of(), WeightedList.of(), null);
}