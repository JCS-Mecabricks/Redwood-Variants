package github.jcsmecabricks.redwoodvariants.world.biomes;

import github.jcsmecabricks.redwoodvariants.RedwoodVariants;
import github.jcsmecabricks.redwoodvariants.world.biomes.surface.ModMaterialRules;
import net.minecraft.resources.Identifier;
import terrablender.api.Regions;
import terrablender.api.SurfaceRuleManager;
import terrablender.api.TerraBlenderApi;

public class ModTerrablenderAPI implements TerraBlenderApi {
    @Override
    public void onTerraBlenderInitialized() {
        Regions.register(new ModOverworldRegion(Identifier.fromNamespaceAndPath(RedwoodVariants.MOD_ID, "overworld"), 1));

        SurfaceRuleManager.addSurfaceRules(SurfaceRuleManager.RuleCategory.OVERWORLD, RedwoodVariants.MOD_ID, ModMaterialRules.makeRules());
    }
}