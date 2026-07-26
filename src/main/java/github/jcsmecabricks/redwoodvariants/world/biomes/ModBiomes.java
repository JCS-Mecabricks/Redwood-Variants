package github.jcsmecabricks.redwoodvariants.world.biomes;

import github.jcsmecabricks.redwoodvariants.RedwoodVariants;
import github.jcsmecabricks.redwoodvariants.entity.ModEntities;
import github.jcsmecabricks.redwoodvariants.init.worldgen.PlacedFeatureInit;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.attribute.BackgroundMusic;
import net.minecraft.world.attribute.EnvironmentAttributes;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.biome.BiomeSpecialEffects;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.GenerationStep;

import static net.minecraft.data.worldgen.biome.OverworldBiomes.calculateSkyColor;

public class ModBiomes {
    public static boolean redwood;
    public static final ResourceKey<Biome> REDWOOD_BIOME = ResourceKey.create(Registries.BIOME,
            Identifier.fromNamespaceAndPath(RedwoodVariants.MOD_ID, "redwood_biome"));

    public static void boostrap(BootstrapContext<Biome> context) {
        context.register(REDWOOD_BIOME, redwoodBiome(context, redwood));
    }

    private static Biome.BiomeBuilder biome(float temperature, float downfall) {
        return new Biome.BiomeBuilder()
                .hasPrecipitation(true)
                .temperature(temperature)
                .downfall(downfall)
                .specialEffects(new BiomeSpecialEffects.Builder()
                        .waterColor(4159204)
                        .build());
    }

    public static void globalOverworldGeneration(BiomeGenerationSettings.Builder builder) {
        BiomeDefaultFeatures.addDefaultCarversAndLakes(builder);
        BiomeDefaultFeatures.addDefaultCrystalFormations(builder);
        BiomeDefaultFeatures.addDefaultUndergroundVariety(builder);
        BiomeDefaultFeatures.addDefaultSprings(builder);
        BiomeDefaultFeatures.addSurfaceFreezing(builder);
    }

    public static Biome redwoodBiome(BootstrapContext<Biome> context, boolean redwood) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();

        spawnBuilder.addSpawn(MobCategory.CREATURE, 5,
                new MobSpawnSettings.SpawnerData(EntityTypes.WOLF, 4, 4));
        spawnBuilder.addSpawn(MobCategory.CREATURE, 6,
                new MobSpawnSettings.SpawnerData(EntityTypes.FOX, 3, 5));

        BiomeDefaultFeatures.farmAnimals(spawnBuilder);

        BiomeGenerationSettings.Builder genBuilder =
                new BiomeGenerationSettings.Builder(
                        context.lookup(Registries.PLACED_FEATURE),
                        context.lookup(Registries.CONFIGURED_CARVER)
                );

        globalOverworldGeneration(genBuilder);
        BiomeDefaultFeatures.addDefaultOres(genBuilder);
        BiomeDefaultFeatures.addMossyStoneBlock(genBuilder);

        genBuilder.addFeature(
                GenerationStep.Decoration.VEGETAL_DECORATION,
                PlacedFeatureInit.REDWOOD_PLACED_KEY
        );

        BiomeDefaultFeatures.addFerns(genBuilder);
        BiomeDefaultFeatures.addForestFlowers(genBuilder);
        BiomeDefaultFeatures.addDefaultExtraVegetation(genBuilder, true);

        return biome(0.4F, 0.7F)
                // ✅ VISUAL ENVIRONMENT
                .setAttribute(
                        EnvironmentAttributes.SKY_COLOR,
                        calculateSkyColor(0.4F)
                )
                .setAttribute(
                        EnvironmentAttributes.WATER_FOG_COLOR,
                        12638463
                )
                .setAttribute(
                        EnvironmentAttributes.BACKGROUND_MUSIC,
                        new BackgroundMusic(SoundEvents.AMBIENT_WARPED_FOREST_MOOD)
                )

                // ✅ BIOME EFFECTS (COLORS ONLY)
                .specialEffects(new BiomeSpecialEffects.Builder()
                        .waterColor(4159204)
                        .grassColorOverride(9470285)
                        .foliageColorOverride(16262185)
                        .build())

                .mobSpawnSettings(spawnBuilder.build())
                .generationSettings(genBuilder.build())
                .build();
    }

}