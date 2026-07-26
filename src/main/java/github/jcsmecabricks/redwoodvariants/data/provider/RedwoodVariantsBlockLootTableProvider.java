package github.jcsmecabricks.redwoodvariants.data.provider;

import github.jcsmecabricks.redwoodvariants.init.BlockInit;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootSubProvider;
import net.minecraft.core.HolderLookup;
import java.util.concurrent.CompletableFuture;

public class RedwoodVariantsBlockLootTableProvider extends FabricBlockLootSubProvider {
    public RedwoodVariantsBlockLootTableProvider(FabricPackOutput dataOutput, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generate() {
        dropSelf(BlockInit.REDWOOD_STAIRS);
                dropSelf(BlockInit.REDWOOD_PRESSURE_PLATE);
                dropSelf(BlockInit.REDWOOD_SAPLING);
                dropSelf(BlockInit.REDWOOD_BUTTON);
                dropSelf(BlockInit.REDWOOD_TRAPDOOR);
                dropSelf(BlockInit.REDWOOD_FENCE);
                add(BlockInit.REDWOOD_LEAVES, createLeavesDrops(BlockInit.REDWOOD_LEAVES, BlockInit.REDWOOD_SAPLING, NORMAL_LEAVES_SAPLING_CHANCES));
                dropSelf(BlockInit.REDWOOD_FENCE_GATE);
                dropSelf(BlockInit.REDWOOD_PLANKS);
                dropSelf(BlockInit.REDWOOD_LOG);
                dropSelf(BlockInit.REDWOOD_WOOD);
                dropSelf(BlockInit.REDWOOD_SIGN);
                dropSelf(BlockInit.REDWOOD_HANGING_SIGN);
                dropSelf(BlockInit.REDWOOD_WALL_HANGING_SIGN);
                dropSelf(BlockInit.REDWOOD_WALL_SIGN);
                dropSelf(BlockInit.STRIPPED_REDWOOD_WOOD);
                dropSelf(BlockInit.STRIPPED_REDWOOD_LOG);
                add(BlockInit.REDWOOD_SLAB, createSlabItemTable(BlockInit.REDWOOD_SLAB));
                add(BlockInit.REDWOOD_DOOR, createDoorTable(BlockInit.REDWOOD_DOOR));
    }
}
