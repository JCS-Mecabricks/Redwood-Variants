package github.jcsmecabricks.redwoodvariants.entity;

import github.jcsmecabricks.redwoodvariants.RedwoodVariants;
import github.jcsmecabricks.redwoodvariants.entity.custom.GrizzlyBearEntity;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;

public class ModEntities {
    public static final EntityType<GrizzlyBearEntity> GRIZZLY_BEAR = Registry.register(BuiltInRegistries.ENTITY_TYPE,
            Identifier.fromNamespaceAndPath(RedwoodVariants.MOD_ID, "grizzly_bear"),
            EntityType.Builder.of(GrizzlyBearEntity::new, MobCategory.CREATURE)
                    .sized(1.6f, 1.6f)
                    .build(ResourceKey.create(Registries.ENTITY_TYPE,
                            Identifier.fromNamespaceAndPath(RedwoodVariants.MOD_ID, "grizzly_bear"))));

    public static void registerEntities() {

    }
}
