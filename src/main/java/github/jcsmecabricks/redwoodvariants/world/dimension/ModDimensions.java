package github.jcsmecabricks.redwoodvariants.world.dimension;

import github.jcsmecabricks.redwoodvariants.RedwoodVariants;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntryList;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.world.World;
import net.minecraft.world.attribute.EnvironmentAttributeMap;
import net.minecraft.world.attribute.EnvironmentAttributes;
import net.minecraft.world.dimension.DimensionOptions;
import net.minecraft.world.dimension.DimensionType;

public class ModDimensions {

    public static final RegistryKey<DimensionOptions> REDWOODDIM_KEY =
            RegistryKey.of(RegistryKeys.DIMENSION, Identifier.of(RedwoodVariants.MOD_ID, "redwooddim"));

    public static final RegistryKey<World> REDWOODDIM_LEVEL_KEY =
            RegistryKey.of(RegistryKeys.WORLD, Identifier.of(RedwoodVariants.MOD_ID, "redwooddim"));

    public static final RegistryKey<DimensionType> REDWOOD_DIM_TYPE =
            RegistryKey.of(RegistryKeys.DIMENSION_TYPE, Identifier.of(RedwoodVariants.MOD_ID, "redwooddim_type"));

    static EnvironmentAttributeMap attributes =
            EnvironmentAttributeMap.builder()
                    .with(EnvironmentAttributes.SKY_COLOR_VISUAL, 7907327)     // vanilla overworld sky
                    .with(EnvironmentAttributes.FOG_COLOR_VISUAL, 12638463)
                    .with(EnvironmentAttributes.WATER_FOG_COLOR_VISUAL, 4159204)
                    .build();


    public static void bootstrapType(Registerable<DimensionType> context) {

        context.register(REDWOOD_DIM_TYPE, new DimensionType(
                true,
                true,   // hasSkyLight
                false,  // hasCeiling
                1.0,    // coordinateScale
                -64,    // minY
                256,    // height
                256,    // logicalHeight
                BlockTags.INFINIBURN_OVERWORLD,
                0.0f,   // ambientLight

                // ❌ NO MONSTERS
                new DimensionType.MonsterSettings(
                        UniformIntProvider.create(15, 15),
                        0
                ),

                DimensionType.Skybox.OVERWORLD,
                DimensionType.CardinalLightType.DEFAULT,
                attributes,
                RegistryEntryList.empty()
        ));
    }

}
