package github.jcsmecabricks.redwoodvariants.registry;

import com.terraformersmc.terraform.sign.api.block.TerraformSignBlockHelper;
import github.jcsmecabricks.redwoodvariants.RedwoodVariants;
import net.minecraft.block.WoodType;
import net.minecraft.util.Identifier;

public class ModWoodType {
    public static final WoodType REDWOOD = TerraformSignBlockHelper.registerDefaultWoodType(
            Identifier.of(RedwoodVariants.MOD_ID, "redwood"));

}
