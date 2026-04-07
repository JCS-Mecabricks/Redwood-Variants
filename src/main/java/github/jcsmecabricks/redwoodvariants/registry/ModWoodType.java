package github.jcsmecabricks.redwoodvariants.registry;

import com.terraformersmc.terraform.sign.api.block.TerraformSignBlockHelper;
import github.jcsmecabricks.redwoodvariants.RedwoodVariants;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.state.properties.WoodType;

public class ModWoodType {
    public static final WoodType REDWOOD = TerraformSignBlockHelper.registerDefaultWoodType(
            Identifier.fromNamespaceAndPath(RedwoodVariants.MOD_ID, "redwood"));

}
