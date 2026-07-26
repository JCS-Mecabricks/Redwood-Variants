package github.jcsmecabricks.redwoodvariants;

import github.jcsmecabricks.redwoodvariants.entity.ModModelLayerLocations;
import github.jcsmecabricks.redwoodvariants.entity.ModEntities;
import github.jcsmecabricks.redwoodvariants.entity.client.GrizzlyBearModel;
import github.jcsmecabricks.redwoodvariants.entity.client.GrizzlyBearRenderer;
import github.jcsmecabricks.redwoodvariants.registry.ModWoodType;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.ModelLayerRegistry;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.object.boat.BoatModel;
import net.minecraft.client.renderer.blockentity.HangingSignRenderer;
import net.minecraft.client.renderer.blockentity.StandingSignRenderer;
import net.minecraft.client.renderer.entity.BoatRenderer;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.world.level.block.HangingSignBlock;
import net.minecraft.world.level.block.state.properties.WoodType;

public class RedwoodVariantsClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		ModelLayerRegistry.registerModelLayer(
				ModModelLayerLocations.REDWOOD_BOAT,
				BoatModel::createBoatModel
		);

		ModelLayerRegistry.registerModelLayer(
				ModModelLayerLocations.REDWOOD_CHEST_BOAT,
				BoatModel::createChestBoatModel
		);

		EntityRenderers.register(
				ModEntities.REDWOOD_BOAT,
				context -> new BoatRenderer(context, ModModelLayerLocations.REDWOOD_BOAT)
		);

		EntityRenderers.register(
				ModEntities.REDWOOD_CHEST_BOAT,
				context -> new BoatRenderer(context, ModModelLayerLocations.REDWOOD_CHEST_BOAT)
		);

		ModelLayerRegistry.registerModelLayer(
				GrizzlyBearModel.GRIZZLY_BEAR,
				GrizzlyBearModel::getTexturedModelData
		);

		EntityRendererRegistry.register(
				ModEntities.GRIZZLY_BEAR,
				GrizzlyBearRenderer::new
		);



	}
}