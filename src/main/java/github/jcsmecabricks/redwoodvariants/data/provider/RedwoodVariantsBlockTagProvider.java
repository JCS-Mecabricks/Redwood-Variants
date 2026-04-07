package github.jcsmecabricks.redwoodvariants.data.provider;

import github.jcsmecabricks.redwoodvariants.init.BlockInit;
import github.jcsmecabricks.redwoodvariants.list.TagList;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.BlockTags;
import java.util.concurrent.CompletableFuture;

public class RedwoodVariantsBlockTagProvider extends FabricTagsProvider.BlockTagsProvider {
    public RedwoodVariantsBlockTagProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider wrapperLookup) {

        valueLookupBuilder(BlockTags.MINEABLE_WITH_AXE)
                .add(BlockInit.REDWOOD_STAIRS)
                .add(BlockInit.REDWOOD_PRESSURE_PLATE)
                .add(BlockInit.REDWOOD_BUTTON)
                .add(BlockInit.REDWOOD_DOOR)
                .add(BlockInit.REDWOOD_WOOD)
                .add(BlockInit.REDWOOD_LOG)
                .add(BlockInit.REDWOOD_FENCE)
                .add(BlockInit.REDWOOD_FENCE_GATE)
                .add(BlockInit.REDWOOD_SLAB)
                .add(BlockInit.REDWOOD_PLANKS)
                .add(BlockInit.STRIPPED_REDWOOD_LOG)
                .add(BlockInit.STRIPPED_REDWOOD_WOOD)
                .add(BlockInit.REDWOOD_TRAPDOOR);

        valueLookupBuilder(TagList.Blocks.REDWOOD_VARIANTS_TAG)
                .add(BlockInit.REDWOOD_STAIRS)
                .add(BlockInit.REDWOOD_PRESSURE_PLATE)
                .add(BlockInit.REDWOOD_BUTTON)
                .add(BlockInit.REDWOOD_DOOR)
                .add(BlockInit.REDWOOD_FENCE)
                .add(BlockInit.REDWOOD_FENCE_GATE)
                .add(BlockInit.REDWOOD_SLAB)
                .add(BlockInit.REDWOOD_PLANKS)
                .add(BlockInit.REDWOOD_TRAPDOOR);

        valueLookupBuilder(BlockTags.FENCES).add(BlockInit.REDWOOD_FENCE);

        valueLookupBuilder(BlockTags.LOGS_THAT_BURN)
                .add(BlockInit.REDWOOD_LOG, BlockInit.REDWOOD_WOOD,
                        BlockInit.STRIPPED_REDWOOD_LOG, BlockInit.STRIPPED_REDWOOD_WOOD);

        valueLookupBuilder(BlockTags.LOGS)
                .add(BlockInit.REDWOOD_LOG, BlockInit.REDWOOD_WOOD,
                        BlockInit.STRIPPED_REDWOOD_LOG, BlockInit.STRIPPED_REDWOOD_WOOD);

        valueLookupBuilder(BlockTags.FENCE_GATES).add(BlockInit.REDWOOD_FENCE_GATE);

    }
}
