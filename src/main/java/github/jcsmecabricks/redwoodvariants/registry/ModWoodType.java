package github.jcsmecabricks.redwoodvariants.registry;

import github.jcsmecabricks.redwoodvariants.mixin.WoodTypeAccessor;
import github.jcsmecabricks.redwoodvariants.util.ModBlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;

public class ModWoodType {
    public static final WoodType REDWOOD =
            WoodTypeAccessor.redwoodvariants$register(
                    new WoodType(
                            "redwood",
                            ModBlockSetType.REDWOOD
                    )
            );

    public static void registerWoodTypes() {

    }

}
