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
        tag(BlockTags.MINEABLE_WITH_AXE)
                .add(BlockInit.getRK(BlockInit.REDWOOD_STAIRS))
                .add(BlockInit.getRK(BlockInit.REDWOOD_PRESSURE_PLATE))
                .add(BlockInit.getRK(BlockInit.REDWOOD_BUTTON))
                .add(BlockInit.getRK(BlockInit.REDWOOD_DOOR))
                .add(BlockInit.getRK(BlockInit.REDWOOD_WOOD))
                .add(BlockInit.getRK(BlockInit.REDWOOD_LOG))
                .add(BlockInit.getRK(BlockInit.REDWOOD_FENCE))
                .add(BlockInit.getRK(BlockInit.REDWOOD_FENCE_GATE))
                .add(BlockInit.getRK(BlockInit.REDWOOD_SLAB))
                .add(BlockInit.getRK(BlockInit.REDWOOD_PLANKS))
                .add(BlockInit.getRK(BlockInit.STRIPPED_REDWOOD_LOG))
                .add(BlockInit.getRK(BlockInit.STRIPPED_REDWOOD_WOOD))
                .add(BlockInit.getRK(BlockInit.REDWOOD_TRAPDOOR));

        tag(TagList.Blocks.REDWOOD_VARIANTS_TAG)
                .add(BlockInit.getRK(BlockInit.REDWOOD_STAIRS))
                .add(BlockInit.getRK(BlockInit.REDWOOD_PRESSURE_PLATE))
                .add(BlockInit.getRK(BlockInit.REDWOOD_BUTTON))
                .add(BlockInit.getRK(BlockInit.REDWOOD_DOOR))
                .add(BlockInit.getRK(BlockInit.REDWOOD_FENCE))
                .add(BlockInit.getRK(BlockInit.REDWOOD_FENCE_GATE))
                .add(BlockInit.getRK(BlockInit.REDWOOD_SLAB))
                .add(BlockInit.getRK(BlockInit.REDWOOD_PLANKS))
                .add(BlockInit.getRK(BlockInit.REDWOOD_TRAPDOOR));

        tag(BlockTags.FENCES).add(BlockInit.getRK(BlockInit.REDWOOD_FENCE));

        tag(BlockTags.OVERWORLD_NATURAL_LOGS)
                .add(BlockInit.getRK(BlockInit.REDWOOD_LOG))
                .add(BlockInit.getRK(BlockInit.REDWOOD_WOOD))
                .add(BlockInit.getRK(BlockInit.STRIPPED_REDWOOD_LOG))
                .add(BlockInit.getRK(BlockInit.STRIPPED_REDWOOD_WOOD));

        tag(BlockTags.LOGS)
                .add(BlockInit.getRK(BlockInit.REDWOOD_LOG))
                .add(BlockInit.getRK(BlockInit.REDWOOD_WOOD))
                .add(BlockInit.getRK(BlockInit.STRIPPED_REDWOOD_LOG))
                .add(BlockInit.getRK(BlockInit.STRIPPED_REDWOOD_WOOD));

        tag(BlockTags.FENCE_GATES).add(BlockInit.getRK(BlockInit.REDWOOD_FENCE_GATE));

    }
}
