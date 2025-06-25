package github.jcsmecabricks.redwoodvariants;

import com.terraformersmc.terraform.boat.api.client.TerraformBoatClientHelper;
import github.jcsmecabricks.redwoodvariants.entity.ModBoats;
import github.jcsmecabricks.redwoodvariants.entity.ModEntities;
import github.jcsmecabricks.redwoodvariants.entity.client.GrizzlyBearModel;
import github.jcsmecabricks.redwoodvariants.entity.client.GrizzlyBearRenderer;
import github.jcsmecabricks.redwoodvariants.init.BlockInit;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.BlockRenderLayer;
import net.minecraft.client.render.entity.model.BoatEntityModel;

public class RedwoodVariantsClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		BlockRenderLayerMap.putBlock(BlockInit.REDWOOD_SAPLING, BlockRenderLayer.CUTOUT);
		BlockRenderLayerMap.putBlock(BlockInit.REDWOOD_PORTAL, BlockRenderLayer.TRANSLUCENT);
		BlockRenderLayerMap.putBlock(BlockInit.REDWOOD_LEAVES, BlockRenderLayer.CUTOUT);
		TerraformBoatClientHelper.registerModelLayers(ModBoats.REDWOOD_BOATS_ID);
		EntityModelLayerRegistry.registerModelLayer(GrizzlyBearModel.GRIZZLY_BEAR, GrizzlyBearModel::getTexturedModelData);
		EntityRendererRegistry.register(ModEntities.GRIZZLY_BEAR, GrizzlyBearRenderer::new);
	}
}