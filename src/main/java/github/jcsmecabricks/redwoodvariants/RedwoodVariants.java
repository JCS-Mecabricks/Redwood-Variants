package github.jcsmecabricks.redwoodvariants;

import github.jcsmecabricks.redwoodvariants.entity.ModModelLayerLocations;
import github.jcsmecabricks.redwoodvariants.entity.ModEntities;
import github.jcsmecabricks.redwoodvariants.entity.custom.GrizzlyBearEntity;
import github.jcsmecabricks.redwoodvariants.init.BlockInit;
import github.jcsmecabricks.redwoodvariants.init.ItemGroupInit;
import github.jcsmecabricks.redwoodvariants.init.ItemInit;
import github.jcsmecabricks.redwoodvariants.init.worldgen.ModWorldGeneration;
import github.jcsmecabricks.redwoodvariants.registry.ModWoodType;
import github.jcsmecabricks.redwoodvariants.util.ModBlockSetType;
import github.jcsmecabricks.redwoodvariants.util.ModPointOfInterestTypes;
import github.jcsmecabricks.redwoodvariants.world.ModEntitySpawns;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.BlockEntityTypes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RedwoodVariants implements ModInitializer {
	public static final String MOD_ID = "redwood-variants";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	@Override
	public void onInitialize() {
		LOGGER.info("Loading...");
		BlockInit.load();
		ModWoodType.registerWoodTypes();
		ModBlockSetType.registerBlockSetTypes();
		BlockEntityTypes.SIGN.addValidBlock(BlockInit.REDWOOD_SIGN);
		BlockEntityTypes.SIGN.addValidBlock(BlockInit.REDWOOD_WALL_SIGN);
		BlockEntityTypes.HANGING_SIGN.addValidBlock(BlockInit.REDWOOD_HANGING_SIGN);
		BlockEntityTypes.HANGING_SIGN.addValidBlock(BlockInit.REDWOOD_WALL_HANGING_SIGN);
		ModEntitySpawns.addSpawns();
		ModEntities.registerEntities();
		ItemGroupInit.load();
		ItemInit.load();
		ModPointOfInterestTypes.register();
		ModWorldGeneration.generateWorldGen();

		FabricDefaultAttributeRegistry.register(ModEntities.GRIZZLY_BEAR, GrizzlyBearEntity.createGrizzlyBearAttributes());

		FlammableBlockRegistry.getDefaultInstance().add(BlockInit.REDWOOD_PLANKS, 5, 20);
		FlammableBlockRegistry.getDefaultInstance().add(BlockInit.REDWOOD_LEAVES, 30, 60);
		FlammableBlockRegistry.getDefaultInstance().add(BlockInit.REDWOOD_WOOD, 5, 5);
		FlammableBlockRegistry.getDefaultInstance().add(BlockInit.REDWOOD_LOG, 5, 5);
		FlammableBlockRegistry.getDefaultInstance().add(BlockInit.STRIPPED_REDWOOD_WOOD, 5, 5);
		FlammableBlockRegistry.getDefaultInstance().add(BlockInit.STRIPPED_REDWOOD_LOG, 5, 5);
		FlammableBlockRegistry.getDefaultInstance().add(BlockInit.REDWOOD_SLAB, 5, 20);
		FlammableBlockRegistry.getDefaultInstance().add(BlockInit.REDWOOD_STAIRS, 5, 20);
		FlammableBlockRegistry.getDefaultInstance().add(BlockInit.REDWOOD_FENCE, 5, 20);
		FlammableBlockRegistry.getDefaultInstance().add(BlockInit.REDWOOD_FENCE_GATE, 5, 20);
		FlammableBlockRegistry.getDefaultInstance().add(BlockInit.REDWOOD_PRESSURE_PLATE, 5, 20);
		FlammableBlockRegistry.getDefaultInstance().add(BlockInit.REDWOOD_DOOR, 5, 20);
		FlammableBlockRegistry.getDefaultInstance().add(BlockInit.REDWOOD_TRAPDOOR, 5, 20);
		FlammableBlockRegistry.getDefaultInstance().add(BlockInit.REDWOOD_BUTTON, 5, 20);

		StrippableBlockRegistry.register(BlockInit.REDWOOD_LOG, BlockInit.STRIPPED_REDWOOD_LOG);
		StrippableBlockRegistry.register(BlockInit.REDWOOD_WOOD, BlockInit.STRIPPED_REDWOOD_WOOD);

		CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.BUILDING_BLOCKS).register(entries -> {
			entries.insertAfter(Items.WARPED_BUTTON, BlockInit.REDWOOD_LOG);
			entries.insertAfter(BlockInit.REDWOOD_LOG, BlockInit.REDWOOD_WOOD);
			entries.insertAfter(BlockInit.REDWOOD_WOOD, BlockInit.REDWOOD_PLANKS);
			entries.insertAfter(BlockInit.REDWOOD_PLANKS, BlockInit.REDWOOD_STAIRS);
			entries.insertAfter(BlockInit.REDWOOD_STAIRS, BlockInit.REDWOOD_SLAB);
			entries.insertAfter(BlockInit.REDWOOD_SLAB, BlockInit.REDWOOD_FENCE);
			entries.insertAfter(BlockInit.REDWOOD_FENCE, BlockInit.REDWOOD_FENCE_GATE);
			entries.insertAfter(BlockInit.REDWOOD_FENCE_GATE, BlockInit.REDWOOD_DOOR);
			entries.insertBefore(BlockInit.REDWOOD_LOG, BlockInit.STRIPPED_REDWOOD_LOG);
			entries.insertAfter(BlockInit.STRIPPED_REDWOOD_LOG, BlockInit.STRIPPED_REDWOOD_WOOD);
			entries.insertAfter(BlockInit.REDWOOD_DOOR, BlockInit.REDWOOD_TRAPDOOR);
			entries.insertAfter(BlockInit.REDWOOD_TRAPDOOR, BlockInit.REDWOOD_PRESSURE_PLATE);
			entries.insertAfter(BlockInit.REDWOOD_PRESSURE_PLATE, BlockInit.REDWOOD_BUTTON);

		});

		CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.NATURAL_BLOCKS).register(entries -> {
			entries.insertAfter(Items.DARK_OAK_SAPLING, BlockInit.REDWOOD_SAPLING);
			entries.insertAfter(Blocks.CHERRY_LEAVES, BlockInit.REDWOOD_LEAVES);

		});

		CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.TOOLS_AND_UTILITIES).register(entries -> {
			entries.insertAfter(Items.CHERRY_CHEST_BOAT, ItemInit.REDWOOD_CHEST_BOAT);
			entries.insertAfter(ItemInit.REDWOOD_CHEST_BOAT, ItemInit.REDWOOD_BOAT);

		});

		CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.FUNCTIONAL_BLOCKS).register(entries -> {
			entries.insertBefore(Items.WARPED_HANGING_SIGN, ItemInit.REDWOOD_SIGN);
			entries.insertBefore(ItemInit.REDWOOD_SIGN, ItemInit.HANGING_REDWOOD_SIGN);

		});
	}
	public static Identifier id(String path) {
		return Identifier.fromNamespaceAndPath(MOD_ID, path);
	}
}