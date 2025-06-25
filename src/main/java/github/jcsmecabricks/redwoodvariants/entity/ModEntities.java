package github.jcsmecabricks.redwoodvariants.entity;

import github.jcsmecabricks.redwoodvariants.RedwoodVariants;
import github.jcsmecabricks.redwoodvariants.entity.custom.GrizzlyBearEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

public class ModEntities {
    public static final EntityType<GrizzlyBearEntity> GRIZZLY_BEAR = Registry.register(Registries.ENTITY_TYPE,
            Identifier.of(RedwoodVariants.MOD_ID, "grizzly_bear"),
            EntityType.Builder.create(GrizzlyBearEntity::new, SpawnGroup.CREATURE)
                    .dimensions(1.6f, 1.6f)
                    .build(RegistryKey.of(RegistryKeys.ENTITY_TYPE,
                            Identifier.of(RedwoodVariants.MOD_ID, "grizzly_bear"))));

    public static void registerEntities() {

    }
}
