package github.jcsmecabricks.redwoodvariants.world.biomes;

import github.jcsmecabricks.redwoodvariants.RedwoodVariants;
import github.jcsmecabricks.redwoodvariants.entity.ModEntities;
import github.jcsmecabricks.redwoodvariants.init.worldgen.PlacedFeatureInit;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.BiomeMoodSound;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.world.attribute.BackgroundMusic;
import net.minecraft.world.attribute.EnvironmentAttributes;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeEffects;
import net.minecraft.world.biome.GenerationSettings;
import net.minecraft.world.biome.SpawnSettings;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.DefaultBiomeFeatures;

import static net.minecraft.world.biome.OverworldBiomeCreator.getSkyColor;

public class ModBiomes {
    public static boolean redwood;
    public static final RegistryKey<Biome> REDWOOD_BIOME = RegistryKey.of(RegistryKeys.BIOME,
            Identifier.of(RedwoodVariants.MOD_ID, "redwood_biome"));

    public static void boostrap(Registerable<Biome> context) {
        context.register(REDWOOD_BIOME, redwoodBiome(context, redwood));
    }

    private static Biome.Builder biome(float temperature, float downfall) {
        return new Biome.Builder()
                .precipitation(true)
                .temperature(temperature)
                .downfall(downfall)
                .effects(new BiomeEffects.Builder()
                        .waterColor(4159204)
                        .build());
    }

    public static void globalOverworldGeneration(GenerationSettings.LookupBackedBuilder builder) {
        DefaultBiomeFeatures.addLandCarvers(builder);
        DefaultBiomeFeatures.addAmethystGeodes(builder);
        DefaultBiomeFeatures.addDungeons(builder);
        DefaultBiomeFeatures.addMineables(builder);
        DefaultBiomeFeatures.addSprings(builder);
        DefaultBiomeFeatures.addFrozenTopLayer(builder);
    }

    public static Biome redwoodBiome(Registerable<Biome> context, boolean redwood) {
        SpawnSettings.Builder spawnBuilder = new SpawnSettings.Builder();

        spawnBuilder.spawn(SpawnGroup.CREATURE, 5,
                new SpawnSettings.SpawnEntry(EntityType.WOLF, 4, 4));
        spawnBuilder.spawn(SpawnGroup.CREATURE, 6,
                new SpawnSettings.SpawnEntry(EntityType.FOX, 3, 5));

        DefaultBiomeFeatures.addFarmAnimals(spawnBuilder);

        GenerationSettings.LookupBackedBuilder genBuilder =
                new GenerationSettings.LookupBackedBuilder(
                        context.getRegistryLookup(RegistryKeys.PLACED_FEATURE),
                        context.getRegistryLookup(RegistryKeys.CONFIGURED_CARVER)
                );

        globalOverworldGeneration(genBuilder);
        DefaultBiomeFeatures.addDefaultOres(genBuilder);
        DefaultBiomeFeatures.addMossyRocks(genBuilder);

        genBuilder.feature(
                GenerationStep.Feature.VEGETAL_DECORATION,
                PlacedFeatureInit.REDWOOD_PLACED_KEY
        );

        DefaultBiomeFeatures.addLargeFerns(genBuilder);
        DefaultBiomeFeatures.addForestFlowers(genBuilder);
        DefaultBiomeFeatures.addDefaultVegetation(genBuilder, true);

        return biome(0.4F, 0.7F)
                // ✅ VISUAL ENVIRONMENT
                .setEnvironmentAttribute(
                        EnvironmentAttributes.SKY_COLOR_VISUAL,
                        getSkyColor(0.4F)
                )
                .setEnvironmentAttribute(
                        EnvironmentAttributes.WATER_FOG_COLOR_VISUAL,
                        12638463
                )
                .setEnvironmentAttribute(
                        EnvironmentAttributes.BACKGROUND_MUSIC_AUDIO,
                        new BackgroundMusic(SoundEvents.AMBIENT_WARPED_FOREST_MOOD)
                )

                // ✅ BIOME EFFECTS (COLORS ONLY)
                .effects(new BiomeEffects.Builder()
                        .waterColor(4159204)
                        .grassColor(9470285)
                        .foliageColor(16262185)
                        .build())

                .spawnSettings(spawnBuilder.build())
                .generationSettings(genBuilder.build())
                .build();
    }

}