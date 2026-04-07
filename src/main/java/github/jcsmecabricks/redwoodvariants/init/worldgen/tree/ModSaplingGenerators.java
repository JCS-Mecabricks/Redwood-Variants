package github.jcsmecabricks.redwoodvariants.init.worldgen.tree;

import github.jcsmecabricks.redwoodvariants.init.worldgen.ConfiguredFeatureInit;
import java.util.Optional;
import net.minecraft.world.level.block.grower.TreeGrower;

public class ModSaplingGenerators {
    public static final TreeGrower REDWOOD =
            new TreeGrower("redwood", 0f, Optional.of(ConfiguredFeatureInit.GIANT_REDWOOD_TREE_KEY),
                    Optional.empty(),
                    Optional.of(ConfiguredFeatureInit.REDWOOD_TREE_KEY),
                    Optional.empty(),
                    Optional.empty(),
                    Optional.empty());

}