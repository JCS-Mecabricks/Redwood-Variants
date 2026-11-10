package github.jcsmecabricks.redwoodvariants;

import github.jcsmecabricks.redwoodvariants.data.generator.ModWorldGenerator;
import github.jcsmecabricks.redwoodvariants.data.provider.*;
import github.jcsmecabricks.redwoodvariants.init.worldgen.ConfiguredFeatureInit;
import github.jcsmecabricks.redwoodvariants.init.worldgen.PlacedFeatureInit;
import github.jcsmecabricks.redwoodvariants.world.biomes.ModBiomes;
import github.jcsmecabricks.redwoodvariants.world.dimension.ModDimensions;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;

public class RedwoodVariantsDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
		pack.addProvider(RedwoodVariantsRecipeProvider::new);
		pack.addProvider(RedwoodVariantsModelProvider::new);
		pack.addProvider(RedwoodVariantsItemTagProvider::new);
		pack.addProvider(RedwoodVariantsBlockTagProvider::new);
		pack.addProvider(RedwoodVariantsBlockLootTableProvider::new);
		pack.addProvider(ModWorldGenerator::new);




	}

	@Override
	public void buildRegistry(RegistrySetBuilder registryBuilder) {
		DataGeneratorEntrypoint.super.buildRegistry(registryBuilder);
		registryBuilder.add(Registries.FEATURE, ConfiguredFeatureInit::boostrap);
		registryBuilder.add(Registries.PLACED_FEATURE, PlacedFeatureInit::boostrap);
		registryBuilder.add(Registries.DIMENSION_TYPE, ModDimensions::bootstrapType);
		registryBuilder.add(Registries.BIOME, ModBiomes::boostrap);

	}
}

