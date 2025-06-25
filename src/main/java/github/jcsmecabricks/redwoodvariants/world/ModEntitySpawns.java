package github.jcsmecabricks.redwoodvariants.world;

import github.jcsmecabricks.redwoodvariants.entity.ModEntities;
import github.jcsmecabricks.redwoodvariants.world.biomes.ModBiomes;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.SpawnLocationTypes;
import net.minecraft.entity.SpawnRestriction;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.world.Heightmap;
import net.minecraft.world.biome.BiomeKeys;

public class ModEntitySpawns {
    public static void addSpawns() {
        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(BiomeKeys.FOREST, ModBiomes.REDWOOD_BIOME),
                SpawnGroup.CREATURE, ModEntities.GRIZZLY_BEAR, 25, 2, 3);
        SpawnRestriction.register(ModEntities.GRIZZLY_BEAR, SpawnLocationTypes.ON_GROUND,
                Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AnimalEntity::isValidNaturalSpawn);
    }
}
