package github.jcsmecabricks.redwoodvariants.init;

import github.jcsmecabricks.redwoodvariants.RedwoodVariants;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;

public class ItemGroupInit {
    public static final Text REDWOOD_TITLE = Text.translatable("itemGroup." + RedwoodVariants.MOD_ID + ".redwood_group");
    public static final ItemGroup REDWOOD_GROUP = register("redwood_group", FabricItemGroup.builder()
            .displayName(REDWOOD_TITLE)
            .icon(Items.SPRUCE_LOG::getDefaultStack)
            .entries((displayContext, entries) -> {
                entries.add(ItemInit.REDWOOD_SIGN);
                entries.add(ItemInit.GRIZZLY_SPAWN_EGG);
                entries.add(ItemInit.HANGING_REDWOOD_SIGN);
                entries.add(ItemInit.RESOURCE_REALM);
                entries.add(ItemInit.REDWOOD_BOAT);
                entries.add(ItemInit.REDWOOD_CHEST_BOAT);
                entries.add(BlockInit.REDWOOD_PLANKS);
                entries.add(BlockInit.REDWOOD_PRESSURE_PLATE);
                entries.add(BlockInit.REDWOOD_BUTTON);
                entries.add(BlockInit.REDWOOD_SLAB);
                entries.add(BlockInit.REDWOOD_FENCE);
                entries.add(BlockInit.REDWOOD_STAIRS);
                entries.add(BlockInit.REDWOOD_FENCE_GATE);
                entries.add(BlockInit.REDWOOD_TRAPDOOR);
                entries.add(BlockInit.REDWOOD_DOOR);
                entries.add(BlockInit.REDWOOD_LOG);
                entries.add(BlockInit.STRIPPED_REDWOOD_LOG);
                entries.add(BlockInit.REDWOOD_WOOD);
                entries.add(BlockInit.STRIPPED_REDWOOD_WOOD);
                entries.add(BlockInit.REDWOOD_SAPLING);
                entries.add(BlockInit.REDWOOD_LEAVES);
            }).build());
    public static <T extends ItemGroup> T register(String name, T itemGroup) {
        return Registry.register(Registries.ITEM_GROUP, RedwoodVariants.id(name), itemGroup);
    }
    public static void load() {

    }
}
