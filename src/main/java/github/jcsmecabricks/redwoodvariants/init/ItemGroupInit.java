package github.jcsmecabricks.redwoodvariants.init;

import github.jcsmecabricks.redwoodvariants.RedwoodVariants;
import net.fabricmc.fabric.api.creativetab.v1.FabricCreativeModeTab;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Items;

public class ItemGroupInit {
    public static final Component REDWOOD_TITLE = Component.translatable("itemGroup." + RedwoodVariants.MOD_ID + ".redwood_group");
    public static final CreativeModeTab REDWOOD_GROUP = register("redwood_group", FabricCreativeModeTab.builder()
            .title(REDWOOD_TITLE)
            .icon(Items.SPRUCE_LOG::getDefaultInstance)
            .displayItems((displayContext, entries) -> {
                entries.accept(ItemInit.REDWOOD_SIGN);
                entries.accept(ItemInit.GRIZZLY_SPAWN_EGG);
                entries.accept(ItemInit.HANGING_REDWOOD_SIGN);
                entries.accept(ItemInit.RESOURCE_REALM);
                entries.accept(ItemInit.REDWOOD_BOAT);
                entries.accept(ItemInit.REDWOOD_CHEST_BOAT);
                entries.accept(BlockInit.REDWOOD_PLANKS);
                entries.accept(BlockInit.REDWOOD_PRESSURE_PLATE);
                entries.accept(BlockInit.REDWOOD_BUTTON);
                entries.accept(BlockInit.REDWOOD_SLAB);
                entries.accept(BlockInit.REDWOOD_FENCE);
                entries.accept(BlockInit.REDWOOD_STAIRS);
                entries.accept(BlockInit.REDWOOD_FENCE_GATE);
                entries.accept(BlockInit.REDWOOD_TRAPDOOR);
                entries.accept(BlockInit.REDWOOD_DOOR);
                entries.accept(BlockInit.REDWOOD_LOG);
                entries.accept(BlockInit.STRIPPED_REDWOOD_LOG);
                entries.accept(BlockInit.REDWOOD_WOOD);
                entries.accept(BlockInit.STRIPPED_REDWOOD_WOOD);
                entries.accept(BlockInit.REDWOOD_SAPLING);
                entries.accept(BlockInit.REDWOOD_LEAVES);
            }).build());
    public static <T extends CreativeModeTab> T register(String name, T itemGroup) {
        return Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, RedwoodVariants.id(name), itemGroup);
    }
    public static void load() {

    }
}
