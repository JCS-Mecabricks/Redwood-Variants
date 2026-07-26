package github.jcsmecabricks.redwoodvariants.init;

import github.jcsmecabricks.redwoodvariants.RedwoodVariants;
import github.jcsmecabricks.redwoodvariants.entity.ModEntities;
import github.jcsmecabricks.redwoodvariants.item.ResourceRealmItem;
import java.util.function.Function;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.TypedEntityData;
import net.minecraft.world.level.block.Block;

public class ItemInit {
    public static final Item REDWOOD_SIGN = registerItem("redwood_sign",
            properties -> new SignItem(BlockInit.REDWOOD_SIGN, BlockInit.REDWOOD_WALL_SIGN,  properties.stacksTo(16)
                    .setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(RedwoodVariants.MOD_ID, "redwood_sign")))));
    public static final Item HANGING_REDWOOD_SIGN = registerItem("hanging_redwood_sign",
            properties -> new HangingSignItem(BlockInit.REDWOOD_HANGING_SIGN, BlockInit.REDWOOD_WALL_HANGING_SIGN,  properties.stacksTo(16)
                    .setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(RedwoodVariants.MOD_ID, "hanging_redwood_sign")))));

    public static final Item RESOURCE_REALM = registerItem("resource_realm",
            properties -> new ResourceRealmItem( properties
                    .stacksTo(1)
                    .setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(RedwoodVariants.MOD_ID, "resource_realm")))));

    public static final Item GRIZZLY_SPAWN_EGG = registerSpawnEggItem("grizzly_spawn_egg",
            settings -> new SpawnEggItem(settings.component(
                    DataComponents.ENTITY_DATA,
                    TypedEntityData.of(ModEntities.GRIZZLY_BEAR, new CompoundTag())
            ))
    );

    private static Item registerItem(String name, Function<Item.Properties, Item> function) {
        return Registry.register(BuiltInRegistries.ITEM, Identifier.fromNamespaceAndPath(RedwoodVariants.MOD_ID, name),
                function.apply(new Item.Properties().setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(RedwoodVariants.MOD_ID, name)))));
    }

    private static Item registerSpawnEggItem(String name, Function<Item.Properties, Item> function) {
        return Registry.register(BuiltInRegistries.ITEM, Identifier.fromNamespaceAndPath(RedwoodVariants.MOD_ID, name),
                function.apply(new Item.Properties().setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(RedwoodVariants.MOD_ID, name)))));
    }

    public static final Item REDWOOD_BOAT = registerItem(
            "redwood_boat",
            properties -> new BoatItem(
                    ModEntities.REDWOOD_BOAT,
                    properties.stacksTo(1)
            )
    );

    public static final Item REDWOOD_CHEST_BOAT = registerItem(
            "redwood_chest_boat",
            properties -> new BoatItem(
                    ModEntities.REDWOOD_CHEST_BOAT,
                     properties.stacksTo(1)
            )
    );

    public static ResourceKey<Item> getRK(Item item) {
        return BuiltInRegistries.ITEM.getResourceKey(item).get();
    }

    public static void load() {}
}
