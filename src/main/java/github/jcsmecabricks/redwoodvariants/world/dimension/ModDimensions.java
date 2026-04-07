package github.jcsmecabricks.redwoodvariants.world.dimension;

import github.jcsmecabricks.redwoodvariants.RedwoodVariants;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.attribute.EnvironmentAttributeMap;
import net.minecraft.world.attribute.EnvironmentAttributes;
import net.minecraft.world.clock.WorldClock;
import net.minecraft.world.level.CardinalLighting;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.dimension.LevelStem;

import java.util.Optional;

public class ModDimensions {

    public static final ResourceKey<LevelStem> REDWOODDIM_KEY =
            ResourceKey.create(Registries.LEVEL_STEM, Identifier.fromNamespaceAndPath(RedwoodVariants.MOD_ID, "redwooddim"));

    public static final ResourceKey<Level> REDWOODDIM_LEVEL_KEY =
            ResourceKey.create(Registries.DIMENSION, Identifier.fromNamespaceAndPath(RedwoodVariants.MOD_ID, "redwooddim"));

    public static final ResourceKey<DimensionType> REDWOOD_DIM_TYPE =
            ResourceKey.create(Registries.DIMENSION_TYPE, Identifier.fromNamespaceAndPath(RedwoodVariants.MOD_ID, "redwooddim_type"));

    static EnvironmentAttributeMap attributes =
            EnvironmentAttributeMap.builder()
                    .set(EnvironmentAttributes.SKY_COLOR, 7907327)     // vanilla overworld sky
                    .set(EnvironmentAttributes.FOG_COLOR, 12638463)
                    .set(EnvironmentAttributes.WATER_FOG_COLOR, 4159204)
                    .build();

    public static void bootstrapType(BootstrapContext<DimensionType> context) {

        context.register(REDWOOD_DIM_TYPE, new DimensionType(
                true,
                true,   // hasSkyLight
                false,
                false,// hasCeiling
                1.0,    // coordinateScale
                -64,    // minY
                256,    // height
                256,    // logicalHeight
                BlockTags.INFINIBURN_OVERWORLD,
                0.0f,   // ambientLight

                // ❌ NO MONSTERS
                new DimensionType.MonsterSettings(
                        UniformInt.of(15, 15),
                        0
                ),

                DimensionType.Skybox.OVERWORLD,
                CardinalLighting.Type.DEFAULT,
                attributes,
                HolderSet.empty(),
                Optional.empty()
        ));
    }

}
