package github.jcsmecabricks.redwoodvariants.init;

import github.jcsmecabricks.redwoodvariants.RedwoodVariants;
import github.jcsmecabricks.redwoodvariants.block.RedwoodPortalBlock;
import github.jcsmecabricks.redwoodvariants.init.worldgen.tree.ModSaplingGenerators;
import github.jcsmecabricks.redwoodvariants.registry.ModWoodType;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.BlockFamilies;
import net.minecraft.data.BlockFamily;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ButtonBlock;
import net.minecraft.world.level.block.CeilingHangingSignBlock;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.FenceBlock;
import net.minecraft.world.level.block.FenceGateBlock;
import net.minecraft.world.level.block.PressurePlateBlock;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.StandingSignBlock;
import net.minecraft.world.level.block.TintedParticleLeavesBlock;
import net.minecraft.world.level.block.TrapDoorBlock;
import net.minecraft.world.level.block.WallHangingSignBlock;
import net.minecraft.world.level.block.WallSignBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import java.util.function.Function;

public class BlockInit {

    public static final Block REDWOOD_PORTAL = registerBlock("redwood_portal",
            settings -> new RedwoodPortalBlock(BlockBehaviour.Properties.of()
                    .noCollision()
                    .randomTicks()
                    .strength(-1.0F)
                    .sound(SoundType.GLASS)
                    .lightLevel(state -> 11)
                    .pushReaction(PushReaction.BLOCK)
                    .setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(RedwoodVariants.MOD_ID, "redwood_portal")))));

    public static final Block REDWOOD_PLANKS = registerBlock("redwood_planks",
            settings -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)
                    .setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(RedwoodVariants.MOD_ID, "redwood_planks")))));

    public static final Block REDWOOD_PRESSURE_PLATE = registerBlock("redwood_pressure_plate",
            settings -> new PressurePlateBlock(BlockSetType.OAK,
                    BlockBehaviour.Properties.of()
                            .strength(2.0F, 3.0F)
                            .sound(SoundType.WOOD)
                            .ignitedByLava()
                            .instrument(NoteBlockInstrument.BASS)
                            .setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(RedwoodVariants.MOD_ID, "redwood_pressure_plate")))));

    public static final Block REDWOOD_BUTTON = registerBlock("redwood_button",
            settings -> new ButtonBlock(BlockSetType.OAK, 1, BlockBehaviour.Properties.of()
                    .strength(2.0F, 3.0F)
                    .sound(SoundType.WOOD)
                    .ignitedByLava()
                    .noCollision()
                    .instrument(NoteBlockInstrument.BASS)
                    .setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(RedwoodVariants.MOD_ID, "redwood_button")))));

    public static final Block REDWOOD_SLAB = registerBlock("redwood_slab",
            settings -> new SlabBlock(BlockBehaviour.Properties.of()
                    .strength(2.0F, 3.0F)
                    .sound(SoundType.WOOD)
                    .ignitedByLava()
                    .instrument(NoteBlockInstrument.BASS)
                    .setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(RedwoodVariants.MOD_ID, "redwood_slab")))));

    public static final Block REDWOOD_FENCE = registerBlock("redwood_fence",
            settings -> new FenceBlock(BlockBehaviour.Properties.of()
                    .strength(2.0F, 3.0F)
                    .sound(SoundType.WOOD)
                    .ignitedByLava()
                    .instrument(NoteBlockInstrument.BASS)
                    .setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(RedwoodVariants.MOD_ID, "redwood_fence")))));

    public static final Block REDWOOD_FENCE_GATE = registerBlock("redwood_fence_gate",
            settings -> new FenceGateBlock(WoodType.OAK, BlockBehaviour.Properties.of()
                    .strength(2.0F, 3.0F)
                    .sound(SoundType.WOOD)
                    .ignitedByLava()
                    .instrument(NoteBlockInstrument.BASS)
                    .setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(RedwoodVariants.MOD_ID, "redwood_fence_gate")))));

    public static final Block REDWOOD_DOOR = registerBlock("redwood_door",
            settings -> new DoorBlock(BlockSetType.OAK, BlockBehaviour.Properties.of()
                    .strength(2.0F, 3.0F)
                    .sound(SoundType.WOOD)
                    .ignitedByLava()
                    .instrument(NoteBlockInstrument.BASS)
                    .setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(RedwoodVariants.MOD_ID, "redwood_door")))));

    public static final Block REDWOOD_TRAPDOOR = registerBlock("redwood_trapdoor",
            settings -> new TrapDoorBlock(BlockSetType.OAK, BlockBehaviour.Properties.of()
                    .strength(2.0F, 3.0F)
                    .sound(SoundType.WOOD)
                    .ignitedByLava()
                    .instrument(NoteBlockInstrument.BASS)
                    .setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(RedwoodVariants.MOD_ID, "redwood_trapdoor")))));

    public static final Block REDWOOD_LOG = registerBlock("redwood_log",
            settings -> new RotatedPillarBlock(settings.ofFullCopy(Blocks.OAK_LOG)
                    .sound(SoundType.WOOD)
                    .ignitedByLava()
                    .instrument(NoteBlockInstrument.BASS)
                    .strength(3f)
                    .setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(RedwoodVariants.MOD_ID, "redwood_log")))));

    public static final Block STRIPPED_REDWOOD_LOG = registerBlock("stripped_redwood_log",
            settings -> new RotatedPillarBlock(settings.ofFullCopy(Blocks.OAK_LOG)
                    .sound(SoundType.WOOD)
                    .ignitedByLava()
                    .instrument(NoteBlockInstrument.BASS)
                    .strength(3f)
                    .setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(RedwoodVariants.MOD_ID, "stripped_redwood_log")))));

    public static final Block REDWOOD_WOOD = registerBlock("redwood_wood",
            settings -> new RotatedPillarBlock(BlockBehaviour.Properties.of()
                    .sound(SoundType.WOOD)
                    .ignitedByLava()
                    .instrument(NoteBlockInstrument.BASS)
                    .strength(3f)
                    .setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(RedwoodVariants.MOD_ID, "redwood_wood")))));

    public static final Block STRIPPED_REDWOOD_WOOD = registerBlock("stripped_redwood_wood",
            settings -> new RotatedPillarBlock(BlockBehaviour.Properties.of()
                    .sound(SoundType.WOOD)
                    .ignitedByLava()
                    .instrument(NoteBlockInstrument.BASS)
                    .strength(3f)
                    .setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(RedwoodVariants.MOD_ID, "stripped_redwood_wood")))));

    public static final Block REDWOOD_STAIRS = registerBlock("redwood_stairs",
            settings -> new StairBlock(REDWOOD_PLANKS.defaultBlockState(), BlockBehaviour.Properties.of()
                    .strength(2.0F, 3.0F)
                    .sound(SoundType.WOOD)
                    .ignitedByLava()
                    .instrument(NoteBlockInstrument.BASS)
                    .setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(RedwoodVariants.MOD_ID, "redwood_stairs")))));

    public static final Block REDWOOD_LEAVES = registerBlock("redwood_leaves",
            settings -> new TintedParticleLeavesBlock(0F, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES)
                    .strength(0.2f)
                    .randomTicks()
                    .sound(SoundType.CHERRY_LEAVES)
                    .isValidSpawn(Blocks::ocelotOrParrot)
                    .isSuffocating(Blocks::never)
                    .ignitedByLava()
                    .isViewBlocking(Blocks::never)
                    .pushReaction(PushReaction.DESTROY)
                    .isRedstoneConductor(Blocks::never)
                    .setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(RedwoodVariants.MOD_ID, "redwood_leaves")))));

    public static final Block REDWOOD_SAPLING = registerBlock("redwood_sapling",
            settings -> new SaplingBlock(ModSaplingGenerators.REDWOOD, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING)
                    .setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(RedwoodVariants.MOD_ID, "redwood_sapling")))));

    public static final Block REDWOOD_SIGN = register("redwood_sign", (p) -> new StandingSignBlock(ModWoodType.REDWOOD, p),
            BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_RED)
                    .forceSolidOn()
                    .instrument(NoteBlockInstrument.BASS)
                    .noCollision()
                    .strength(1.0F)
                    .ignitedByLava());

    public static final Block REDWOOD_HANGING_SIGN = register("redwood_hanging_sign", (p) -> new CeilingHangingSignBlock(ModWoodType.REDWOOD, p),
            BlockBehaviour.Properties.of().mapColor(REDWOOD_LOG.defaultMapColor()).forceSolidOn()
                    .instrument(NoteBlockInstrument.BASS)
                    .noCollision().strength(1.0F).ignitedByLava());

    public static final Block REDWOOD_WALL_SIGN = register("redwood_wall_sign", (p) -> new WallSignBlock(ModWoodType.REDWOOD, p),
            wallVariant(REDWOOD_SIGN, true).mapColor(MapColor.WOOD).
                    forceSolidOn().instrument(NoteBlockInstrument.BASS)
                    .noCollision().strength(1.0F).ignitedByLava());

    public static final Block REDWOOD_WALL_HANGING_SIGN = register("redwood_wall_hanging_sign", (p) -> new WallHangingSignBlock(ModWoodType.REDWOOD, p),
            wallVariant(REDWOOD_HANGING_SIGN, true).mapColor(REDWOOD_LOG.defaultMapColor())
                    .forceSolidOn().instrument(NoteBlockInstrument.BASS).noCollision()
                    .strength(1.0F).ignitedByLava());

    public static final BlockFamily REDWOOD_FAMILY = BlockFamilies.familyBuilder(REDWOOD_PLANKS)
            .sign(REDWOOD_SIGN, REDWOOD_WALL_SIGN)
            .hangingSign(
                    REDWOOD_HANGING_SIGN,
                    REDWOOD_WALL_HANGING_SIGN
            )
            .recipeGroupPrefix("wooden")
            .recipeUnlockedBy("has_planks")
            .getFamily();

    private static Block registerBlock(String name, Function<BlockBehaviour.Properties, Block> function) {
        Block toRegister = function.apply(BlockBehaviour.Properties.of().setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(RedwoodVariants.MOD_ID, name))));
        registerBlockItem(name, toRegister);
        return Registry.register(BuiltInRegistries.BLOCK, Identifier.fromNamespaceAndPath(RedwoodVariants.MOD_ID, name), toRegister);
    }

    public static Block register(final ResourceKey<Block> id, final Function<BlockBehaviour.Properties, Block> factory, final BlockBehaviour.Properties properties) {
        Block block = (Block)factory.apply(properties.setId(id));
        return (Block)Registry.register(BuiltInRegistries.BLOCK, id, block);
    }

    public static Block register(final ResourceKey<Block> id, final BlockBehaviour.Properties properties) {
        return register(id, Block::new, properties);
    }

    private static BlockBehaviour.Properties wallVariant(final Block standingBlock, final boolean copyName) {
        BlockBehaviour.Properties wallProperties = BlockBehaviour.Properties.of().overrideLootTable(standingBlock.getLootTable());
        if (copyName) {
            wallProperties = wallProperties.overrideDescription(standingBlock.getDescriptionId());
        }

        return wallProperties;
    }

    public static ResourceKey<Block> getRK(Block block) {
        return BuiltInRegistries.BLOCK.getResourceKey(block).get();
    }


    private static ResourceKey<Block> BlockId(final String name) {
        return ResourceKey.create(
                Registries.BLOCK,
                Identifier.fromNamespaceAndPath(RedwoodVariants.MOD_ID, name)
        );
    }

    private static Block register(final String id, final Function<BlockBehaviour.Properties, Block> factory, final BlockBehaviour.Properties properties) {
        return register(BlockId(id), factory, properties);
    }

    private static void registerBlockItem(String name, Block block) {
        Registry.register(BuiltInRegistries.ITEM, Identifier.fromNamespaceAndPath(RedwoodVariants.MOD_ID, name),
                new BlockItem(block, new Item.Properties().useBlockDescriptionPrefix()
                        .setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(RedwoodVariants.MOD_ID, name)))));
    }

    public static void load() {
        // Method to trigger class loading and registration if needed
    }
}
