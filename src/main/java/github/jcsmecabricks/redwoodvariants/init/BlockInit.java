package github.jcsmecabricks.redwoodvariants.init;

import com.terraformersmc.terraform.sign.api.block.TerraformSignBlockHelper;
import github.jcsmecabricks.redwoodvariants.RedwoodVariants;
import github.jcsmecabricks.redwoodvariants.block.RedwoodPortalBlock;
import github.jcsmecabricks.redwoodvariants.entity.ModSigns;
import github.jcsmecabricks.redwoodvariants.init.worldgen.tree.ModSaplingGenerators;
import github.jcsmecabricks.redwoodvariants.registry.ModWoodType;
import net.minecraft.block.*;
import net.minecraft.block.enums.NoteBlockInstrument;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.data.family.BlockFamilies;
import net.minecraft.data.family.BlockFamily;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

import java.util.function.Function;

public class BlockInit {

    public static final Block REDWOOD_PORTAL = registerBlock("redwood_portal",
            settings -> new RedwoodPortalBlock(AbstractBlock.Settings.create()
                    .noCollision()
                    .ticksRandomly()
                    .strength(-1.0F)
                    .sounds(BlockSoundGroup.GLASS)
                    .luminance(state -> 11)
                    .pistonBehavior(PistonBehavior.BLOCK)
                    .registryKey(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(RedwoodVariants.MOD_ID, "redwood_portal")))));

    public static final Block REDWOOD_PLANKS = registerBlock("redwood_planks",
            settings -> new Block(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS)
                    .registryKey(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(RedwoodVariants.MOD_ID, "redwood_planks")))));

    public static final Block REDWOOD_PRESSURE_PLATE = registerBlock("redwood_pressure_plate",
            settings -> new PressurePlateBlock(BlockSetType.OAK,
                    AbstractBlock.Settings.create()
                            .strength(2.0F, 3.0F)
                            .sounds(BlockSoundGroup.WOOD)
                            .burnable()
                            .instrument(NoteBlockInstrument.BASS)
                            .registryKey(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(RedwoodVariants.MOD_ID, "redwood_pressure_plate")))));

    public static final Block REDWOOD_BUTTON = registerBlock("redwood_button",
            settings -> new ButtonBlock(BlockSetType.OAK, 1, AbstractBlock.Settings.create()
                    .strength(2.0F, 3.0F)
                    .sounds(BlockSoundGroup.WOOD)
                    .burnable()
                    .noCollision()
                    .instrument(NoteBlockInstrument.BASS)
                    .registryKey(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(RedwoodVariants.MOD_ID, "redwood_button")))));

    public static final Block REDWOOD_SLAB = registerBlock("redwood_slab",
            settings -> new SlabBlock(AbstractBlock.Settings.create()
                    .strength(2.0F, 3.0F)
                    .sounds(BlockSoundGroup.WOOD)
                    .burnable()
                    .instrument(NoteBlockInstrument.BASS)
                    .registryKey(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(RedwoodVariants.MOD_ID, "redwood_slab")))));

    public static final Block REDWOOD_FENCE = registerBlock("redwood_fence",
            settings -> new FenceBlock(AbstractBlock.Settings.create()
                    .strength(2.0F, 3.0F)
                    .sounds(BlockSoundGroup.WOOD)
                    .burnable()
                    .instrument(NoteBlockInstrument.BASS)
                    .registryKey(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(RedwoodVariants.MOD_ID, "redwood_fence")))));

    public static final Block REDWOOD_FENCE_GATE = registerBlock("redwood_fence_gate",
            settings -> new FenceGateBlock(WoodType.OAK, AbstractBlock.Settings.create()
                    .strength(2.0F, 3.0F)
                    .sounds(BlockSoundGroup.WOOD)
                    .burnable()
                    .instrument(NoteBlockInstrument.BASS)
                    .registryKey(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(RedwoodVariants.MOD_ID, "redwood_fence_gate")))));

    public static final Block REDWOOD_DOOR = registerBlock("redwood_door",
            settings -> new DoorBlock(BlockSetType.OAK, AbstractBlock.Settings.create()
                    .strength(2.0F, 3.0F)
                    .sounds(BlockSoundGroup.WOOD)
                    .burnable()
                    .instrument(NoteBlockInstrument.BASS)
                    .registryKey(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(RedwoodVariants.MOD_ID, "redwood_door")))));

    public static final Block REDWOOD_TRAPDOOR = registerBlock("redwood_trapdoor",
            settings -> new TrapdoorBlock(BlockSetType.OAK, AbstractBlock.Settings.create()
                    .strength(2.0F, 3.0F)
                    .sounds(BlockSoundGroup.WOOD)
                    .burnable()
                    .instrument(NoteBlockInstrument.BASS)
                    .registryKey(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(RedwoodVariants.MOD_ID, "redwood_trapdoor")))));

    public static final Block REDWOOD_LOG = registerBlock("redwood_log",
            settings -> new PillarBlock(AbstractBlock.Settings.copy(Blocks.OAK_LOG)
                    .sounds(BlockSoundGroup.WOOD)
                    .burnable()
                    .instrument(NoteBlockInstrument.BASS)
                    .strength(3f)
                    .registryKey(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(RedwoodVariants.MOD_ID, "redwood_log")))));

    public static final Block STRIPPED_REDWOOD_LOG = registerBlock("stripped_redwood_log",
            settings -> new PillarBlock(AbstractBlock.Settings.copy(Blocks.OAK_LOG)
                    .sounds(BlockSoundGroup.WOOD)
                    .burnable()
                    .instrument(NoteBlockInstrument.BASS)
                    .strength(3f)
                    .registryKey(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(RedwoodVariants.MOD_ID, "stripped_redwood_log")))));

    public static final Block REDWOOD_WOOD = registerBlock("redwood_wood",
            settings -> new PillarBlock(AbstractBlock.Settings.create()
                    .sounds(BlockSoundGroup.WOOD)
                    .burnable()
                    .instrument(NoteBlockInstrument.BASS)
                    .strength(3f)
                    .registryKey(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(RedwoodVariants.MOD_ID, "redwood_wood")))));

    public static final Block STRIPPED_REDWOOD_WOOD = registerBlock("stripped_redwood_wood",
            settings -> new PillarBlock(AbstractBlock.Settings.create()
                    .sounds(BlockSoundGroup.WOOD)
                    .burnable()
                    .instrument(NoteBlockInstrument.BASS)
                    .strength(3f)
                    .registryKey(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(RedwoodVariants.MOD_ID, "stripped_redwood_wood")))));

    public static final Block REDWOOD_STAIRS = registerBlock("redwood_stairs",
            settings -> new StairsBlock(REDWOOD_PLANKS.getDefaultState(), AbstractBlock.Settings.create()
                    .strength(2.0F, 3.0F)
                    .sounds(BlockSoundGroup.WOOD)
                    .burnable()
                    .instrument(NoteBlockInstrument.BASS)
                    .registryKey(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(RedwoodVariants.MOD_ID, "redwood_stairs")))));

    public static final Block REDWOOD_LEAVES = registerBlock("redwood_leaves",
            settings -> new TintedParticleLeavesBlock(0F, AbstractBlock.Settings.copy(Blocks.OAK_LEAVES)
                    .strength(0.2f)
                    .ticksRandomly()
                    .sounds(BlockSoundGroup.CHERRY_LEAVES)
                    .allowsSpawning(Blocks::canSpawnOnLeaves)
                    .suffocates(Blocks::never)
                    .burnable()
                    .blockVision(Blocks::never)
                    .pistonBehavior(PistonBehavior.DESTROY)
                    .solidBlock(Blocks::never)
                    .registryKey(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(RedwoodVariants.MOD_ID, "redwood_leaves")))));

    public static final Block REDWOOD_SAPLING = registerBlock("redwood_sapling",
            settings -> new SaplingBlock(ModSaplingGenerators.REDWOOD, AbstractBlock.Settings.copy(Blocks.OAK_SAPLING)
                    .registryKey(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(RedwoodVariants.MOD_ID, "redwood_sapling")))));

    public static final Block STANDING_REDWOOD_SIGN = TerraformSignBlockHelper.registerSignBlock(
            ModSigns.REDWOOD_SIGN_ID, settings ->
                    new SignBlock(ModWoodType.REDWOOD, settings), AbstractBlock.Settings.copy(Blocks.OAK_SIGN));

    public static final Block WALL_REDWOOD_SIGN = TerraformSignBlockHelper.registerSignBlock(
            ModSigns.REDWOOD_WALL_SIGN_ID, settings ->
                    new WallSignBlock(ModWoodType.REDWOOD, settings), AbstractBlock.Settings.copy(Blocks.OAK_SIGN));

    public static final Block HANGING_REDWOOD_SIGN = TerraformSignBlockHelper.registerSignBlock(
            ModSigns.REDWOOD_HANGING_SIGN_ID, settings ->
                    new HangingSignBlock(ModWoodType.REDWOOD, settings), AbstractBlock.Settings.copy(Blocks.OAK_SIGN));

    public static final Block WALL_HANGING_REDWOOD_SIGN = TerraformSignBlockHelper.registerSignBlock(
            ModSigns.REDWOOD_WALL_HANGING_SIGN_ID, settings ->
                    new WallHangingSignBlock(ModWoodType.REDWOOD, settings), AbstractBlock.Settings.copy(Blocks.OAK_SIGN));

    public static final BlockFamily REDWOOD_FAMILY = BlockFamilies.register(REDWOOD_PLANKS)
            .sign(STANDING_REDWOOD_SIGN, WALL_REDWOOD_SIGN)
            .group("wooden")
            .unlockCriterionName("has_planks")
            .build();

    public static final BlockFamily REDWOOD_HANGING_FAMILY = BlockFamilies.register(REDWOOD_LOG)
            .sign(HANGING_REDWOOD_SIGN, WALL_HANGING_REDWOOD_SIGN)
            .group("wooden")
            .unlockCriterionName("has_planks")
            .build();

    private static Block registerBlock(String name, Function<AbstractBlock.Settings, Block> function) {
        Block toRegister = function.apply(AbstractBlock.Settings.create().registryKey(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(RedwoodVariants.MOD_ID, name))));
        registerBlockItem(name, toRegister);
        return Registry.register(Registries.BLOCK, Identifier.of(RedwoodVariants.MOD_ID, name), toRegister);
    }

    private static void registerBlockItem(String name, Block block) {
        Registry.register(Registries.ITEM, Identifier.of(RedwoodVariants.MOD_ID, name),
                new BlockItem(block, new Item.Settings().useBlockPrefixedTranslationKey()
                        .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(RedwoodVariants.MOD_ID, name)))));
    }

    public static void load() {
        // Method to trigger class loading and registration if needed
    }
}
