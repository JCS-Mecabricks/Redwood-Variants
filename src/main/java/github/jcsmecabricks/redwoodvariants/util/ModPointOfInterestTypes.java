package github.jcsmecabricks.redwoodvariants.util;

import github.jcsmecabricks.redwoodvariants.RedwoodVariants;
import github.jcsmecabricks.redwoodvariants.init.BlockInit;
import net.fabricmc.fabric.api.object.builder.v1.world.poi.PointOfInterestHelper;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.Identifier;
import net.minecraft.world.poi.PointOfInterestType;

import java.util.Set;

public class ModPointOfInterestTypes {
    public static PointOfInterestType REDWOOD_PORTAL;

    public static void register() {
        REDWOOD_PORTAL = PointOfInterestHelper.register(
                Identifier.of(RedwoodVariants.MOD_ID, "redwood_portal"),
                1,
                1 ,
                getAllStates(BlockInit.REDWOOD_PORTAL)
        );
    }

    private static Set<BlockState> getAllStates(Block block) {
        return Set.copyOf(block.getStateManager().getStates());
    }
}
