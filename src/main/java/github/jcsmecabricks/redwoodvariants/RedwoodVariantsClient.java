package github.jcsmecabricks.redwoodvariants;

import com.terraformersmc.terraform.boat.api.TerraformBoatClientHelper;
import github.jcsmecabricks.redwoodvariants.entity.ModBoats;
import github.jcsmecabricks.redwoodvariants.entity.ModEntities;
import github.jcsmecabricks.redwoodvariants.entity.client.GrizzlyBearModel;
import github.jcsmecabricks.redwoodvariants.entity.client.GrizzlyBearRenderer;
import github.jcsmecabricks.redwoodvariants.init.BlockInit;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.BlockColorRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.ModelLayerRegistry;
import net.minecraft.client.renderer.chunk.ChunkSectionLayer;

public class RedwoodVariantsClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		TerraformBoatClientHelper.registerModelLayers(ModBoats.REDWOOD_BOATS_ID);
		ModelLayerRegistry.registerModelLayer(GrizzlyBearModel.GRIZZLY_BEAR, GrizzlyBearModel::getTexturedModelData);
		EntityRendererRegistry.register(ModEntities.GRIZZLY_BEAR, GrizzlyBearRenderer::new);
	}
}