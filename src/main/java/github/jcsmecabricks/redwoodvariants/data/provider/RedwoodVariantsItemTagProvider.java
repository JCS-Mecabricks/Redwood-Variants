package github.jcsmecabricks.redwoodvariants.data.provider;

import github.jcsmecabricks.redwoodvariants.init.BlockInit;
import github.jcsmecabricks.redwoodvariants.init.ItemInit;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.ItemTags;
import java.util.concurrent.CompletableFuture;

public class RedwoodVariantsItemTagProvider extends FabricTagsProvider.ItemTagsProvider {
    public RedwoodVariantsItemTagProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider wrapperLookup) {

        tag(ItemTags.SIGNS)
                .add(ItemInit.getRK(ItemInit.REDWOOD_SIGN));

        tag(ItemTags.HANGING_SIGNS)
                .add(ItemInit.getRK(ItemInit.HANGING_REDWOOD_SIGN));

        tag(ItemTags.BOATS)
                .add(ItemInit.getRK(ItemInit.REDWOOD_BOAT));

        tag(ItemTags.LOGS_THAT_BURN)
                .add(ItemInit.getRK(BlockInit.REDWOOD_WOOD.asItem()))
                .add(ItemInit.getRK(BlockInit.STRIPPED_REDWOOD_WOOD.asItem()))
                .add(ItemInit.getRK(BlockInit.STRIPPED_REDWOOD_LOG.asItem()))
                .add(ItemInit.getRK(BlockInit.REDWOOD_LOG.asItem()));

        tag(ItemTags.LOGS)
                .add(ItemInit.getRK(BlockInit.REDWOOD_WOOD.asItem()))
                .add(ItemInit.getRK(BlockInit.STRIPPED_REDWOOD_WOOD.asItem()))
                .add(ItemInit.getRK(BlockInit.STRIPPED_REDWOOD_LOG.asItem()))
                .add(ItemInit.getRK(BlockInit.REDWOOD_LOG.asItem()));

        tag(ItemTags.CHEST_BOATS)
                .add(ItemInit.getRK(ItemInit.REDWOOD_CHEST_BOAT));

    }
}
