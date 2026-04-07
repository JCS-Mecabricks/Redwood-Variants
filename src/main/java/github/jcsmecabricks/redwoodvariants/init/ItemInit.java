package github.jcsmecabricks.redwoodvariants.init;

import com.terraformersmc.terraform.boat.api.item.TerraformBoatItemHelper;
import github.jcsmecabricks.redwoodvariants.RedwoodVariants;
import github.jcsmecabricks.redwoodvariants.entity.ModBoats;
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
import net.minecraft.world.item.HangingSignItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SignItem;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.item.component.TypedEntityData;

public class ItemInit {
    public static final Item REDWOOD_SIGN = registerItem("redwood_sign",
            new SignItem(BlockInit.STANDING_REDWOOD_SIGN, BlockInit.WALL_REDWOOD_SIGN, new Item.Properties().stacksTo(16)
                    .setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(RedwoodVariants.MOD_ID, "redwood_sign")))));
    public static final Item HANGING_REDWOOD_SIGN = registerItem("hanging_redwood_sign",
            new HangingSignItem(BlockInit.HANGING_REDWOOD_SIGN, BlockInit.WALL_HANGING_REDWOOD_SIGN, new Item.Properties().stacksTo(16)
                    .setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(RedwoodVariants.MOD_ID, "hanging_redwood_sign")))));

    public static final Item RESOURCE_REALM = registerItem("resource_realm",
            new ResourceRealmItem(new Item.Properties()
                    .stacksTo(1)
                    .setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(RedwoodVariants.MOD_ID, "resource_realm")))));

    public static final Item GRIZZLY_SPAWN_EGG = registerSpawnEggItem("grizzly_spawn_egg",
            settings -> new SpawnEggItem(settings.component(
                    DataComponents.ENTITY_DATA,
                    TypedEntityData.of(ModEntities.GRIZZLY_BEAR, new CompoundTag())
            ))
    );

    private static Item registerItem(String name, Item item) {
        return Registry.register(BuiltInRegistries.ITEM, Identifier.fromNamespaceAndPath(RedwoodVariants.MOD_ID, name), item);
    }

    private static Item registerSpawnEggItem(String name, Function<Item.Properties, Item> function) {
        return Registry.register(BuiltInRegistries.ITEM, Identifier.fromNamespaceAndPath(RedwoodVariants.MOD_ID, name),
                function.apply(new Item.Properties().setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(RedwoodVariants.MOD_ID, name)))));
    }

    public static final Item REDWOOD_BOAT = TerraformBoatItemHelper.registerBoatItem(
            ModBoats.REDWOOD_BOATS_ID, false);

    public static final Item REDWOOD_CHEST_BOAT = TerraformBoatItemHelper.registerBoatItem(
            ModBoats.REDWOOD_BOATS_ID, true);

    public static void load() {}
}
