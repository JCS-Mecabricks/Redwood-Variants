package github.jcsmecabricks.redwoodvariants.world.dimension;

import github.jcsmecabricks.redwoodvariants.RedwoodVariants;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionOptions;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.dimension.DimensionTypes;

import java.util.Optional;
import java.util.OptionalInt;
import java.util.OptionalLong;

public class ModDimensions {
    public static final RegistryKey<DimensionOptions> REDWOODDIM_KEY = RegistryKey.of(RegistryKeys.DIMENSION,
            Identifier.of(RedwoodVariants.MOD_ID, "redwooddim"));
    public static final RegistryKey<World> REDWOODDIM_LEVEL_KEY = RegistryKey.of(RegistryKeys.WORLD,
            Identifier.of(RedwoodVariants.MOD_ID, "redwooddim"));
    public static final RegistryKey<DimensionType> REDWOOD_DIM_TYPE = RegistryKey.of(RegistryKeys.DIMENSION_TYPE,
            Identifier.of(RedwoodVariants.MOD_ID, "redwooddim_type"));

    public static void bootstrapType(Registerable<DimensionType> context) {
        context.register(REDWOOD_DIM_TYPE, new DimensionType(
                OptionalLong.of(12000), // fixedTime
                false, // hasSkylight
                false, // hasCeiling
                false, // ultraWarm
                true, // natural
                1.0, // coordinateScale
                true, // bedWorks
                false, // respawnAnchorWorks
                -64, // minY
                256, // height
                256, // logicalHeight
                BlockTags.INFINIBURN_OVERWORLD, // infiniburn
                DimensionTypes.OVERWORLD_ID, // effectsLocation
                1.0f, // ambientLight
                Optional.of(128), // Cloud Height
        new DimensionType.MonsterSettings(false, false, UniformIntProvider.create(0, 0), 0)));
    }
}
