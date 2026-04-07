package github.jcsmecabricks.redwoodvariants.util;

import github.jcsmecabricks.redwoodvariants.RedwoodVariants;
import github.jcsmecabricks.redwoodvariants.init.BlockInit;
import net.fabricmc.fabric.api.object.builder.v1.world.poi.PoiHelper;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import java.util.Set;

public class ModPointOfInterestTypes {
    public static PoiType REDWOOD_PORTAL;

    public static void register() {
        REDWOOD_PORTAL = PoiHelper.register(
                Identifier.fromNamespaceAndPath(RedwoodVariants.MOD_ID, "redwood_portal"),
                1,
                1 ,
                getAllStates(BlockInit.REDWOOD_PORTAL)
        );
    }

    private static Set<BlockState> getAllStates(Block block) {
        return Set.copyOf(block.getStateDefinition().getPossibleStates());
    }
}
