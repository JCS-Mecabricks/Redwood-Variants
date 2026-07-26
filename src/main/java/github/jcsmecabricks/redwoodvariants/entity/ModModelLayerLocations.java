package github.jcsmecabricks.redwoodvariants.entity;

import github.jcsmecabricks.redwoodvariants.RedwoodVariants;
import github.jcsmecabricks.redwoodvariants.registry.ModWoodType;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.resources.Identifier;

public class ModModelLayerLocations {

    public static final ModelLayerLocation REDWOOD_BOAT =
            new ModelLayerLocation(
                    Identifier.fromNamespaceAndPath(
                            RedwoodVariants.MOD_ID,
                            "boat/redwood"
                    ),
                    "main"
            );

    public static final ModelLayerLocation REDWOOD_CHEST_BOAT =
            new ModelLayerLocation(
                    Identifier.fromNamespaceAndPath(
                            RedwoodVariants.MOD_ID,
                            "chest_boat/redwood"
                    ),
                    "main"
            );

    public static final ModelLayerLocation REDWOOD_SIGN =
            new ModelLayerLocation(
                    Identifier.withDefaultNamespace(
                            "sign/standing/redwood"
                    ),
                    "main"
            );

    public static final ModelLayerLocation REDWOOD_WALL_SIGN =
            new ModelLayerLocation(
                    Identifier.withDefaultNamespace(
                            "sign/wall/redwood"
                    ),
                    "main"
            );

    public static final ModelLayerLocation REDWOOD_HANGING_SIGN =
            new ModelLayerLocation(
                    Identifier.withDefaultNamespace(
                            "sign/hanging/redwood"
                    ),
                    "main"
            );
}