package github.jcsmecabricks.redwoodvariants.entity;

import github.jcsmecabricks.redwoodvariants.RedwoodVariants;
import github.jcsmecabricks.redwoodvariants.entity.custom.GrizzlyBearEntity;
import github.jcsmecabricks.redwoodvariants.init.ItemInit;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.vehicle.boat.Boat;
import net.minecraft.world.entity.vehicle.boat.ChestBoat;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

import java.util.function.Supplier;

public class ModEntities {

    public static final ResourceKey<EntityType<?>> REDWOOD_BOAT_KEY = ResourceKey.create(Registries.ENTITY_TYPE,
            Identifier.fromNamespaceAndPath(RedwoodVariants.MOD_ID, "redwood_boat"));
    public static final ResourceKey<EntityType<?>> REDWOOD_CHEST_BOAT_KEY = ResourceKey.create(Registries.ENTITY_TYPE,
            Identifier.fromNamespaceAndPath(RedwoodVariants.MOD_ID, "redwood_chest_boat"));
    public static final EntityType<Boat> REDWOOD_BOAT = Registry.register(BuiltInRegistries.ENTITY_TYPE,
            Identifier.fromNamespaceAndPath(RedwoodVariants.MOD_ID, "redwood_boat"),
            EntityType.Builder.<Boat>of((entityType, level) -> new Boat(entityType, level, () -> ItemInit.REDWOOD_BOAT),
                            MobCategory.MISC).sized(1.375f, 0.5625f)
                    .clientTrackingRange(10).build(REDWOOD_BOAT_KEY));
    public static final EntityType<ChestBoat> REDWOOD_CHEST_BOAT = Registry.register(BuiltInRegistries.ENTITY_TYPE,
            Identifier.fromNamespaceAndPath(RedwoodVariants.MOD_ID, "redwood_chest_boat"),
            EntityType.Builder.<ChestBoat>of((entityType, level) -> new ChestBoat(entityType, level, () -> ItemInit.REDWOOD_CHEST_BOAT),
                            MobCategory.MISC).sized(1.375f, 0.5625f)
                    .clientTrackingRange(10).build(REDWOOD_CHEST_BOAT_KEY));

    public static final EntityType<GrizzlyBearEntity> GRIZZLY_BEAR = Registry.register(BuiltInRegistries.ENTITY_TYPE,
            Identifier.fromNamespaceAndPath(RedwoodVariants.MOD_ID, "grizzly_bear"),
            EntityType.Builder.of(GrizzlyBearEntity::new, MobCategory.CREATURE)
                    .sized(1.6f, 1.6f)
                    .build(ResourceKey.create(Registries.ENTITY_TYPE,
                            Identifier.fromNamespaceAndPath(RedwoodVariants.MOD_ID, "grizzly_bear"))));

    private static EntityType.EntityFactory<Boat> boatFactory(final Supplier<Item> boatItem) {
        return (entityType, level) -> new Boat(entityType, level, boatItem);
    }

    private static EntityType.EntityFactory<ChestBoat> chestBoatFactory(final Supplier<Item> dropItem) {
        return (entityType, level) -> new ChestBoat(entityType, level, dropItem);
    }

    private static <T extends Entity> EntityType<T> register(final ResourceKey<EntityType<?>> id, final EntityType.Builder<T> builder) {
        return (EntityType)Registry.register(BuiltInRegistries.ENTITY_TYPE, id, builder.build(id));
    }

    private static ResourceKey<EntityType<?>> vanillaEntityId(final String vanillaId) {
        return ResourceKey.create(Registries.ENTITY_TYPE, Identifier.withDefaultNamespace(vanillaId));
    }

    private static <T extends Entity> EntityType<T> register(final String vanillaId, final EntityType.Builder<T> builder) {
        return register(vanillaEntityId(vanillaId), builder);
    }

    public static void registerEntities() {

    }
}
