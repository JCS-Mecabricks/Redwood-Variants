package github.jcsmecabricks.redwoodvariants.data.provider;

import github.jcsmecabricks.redwoodvariants.init.BlockInit;
import github.jcsmecabricks.redwoodvariants.init.ItemInit;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.model.ModelTemplates;

public class RedwoodVariantsModelProvider extends FabricModelProvider {
    public RedwoodVariantsModelProvider(FabricPackOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockModelGenerators blockStateModelGenerator) {
        blockStateModelGenerator.createCrossBlockWithDefaultItem(BlockInit.REDWOOD_SAPLING, BlockModelGenerators.PlantType.NOT_TINTED);
        blockStateModelGenerator.createTrivialCube(BlockInit.REDWOOD_LEAVES);
        BlockModelGenerators.BlockFamilyProvider redwoodPool = blockStateModelGenerator.family(BlockInit.REDWOOD_PLANKS);
        redwoodPool.button(BlockInit.REDWOOD_BUTTON);
        redwoodPool.slab(BlockInit.REDWOOD_SLAB);
        redwoodPool.stairs(BlockInit.REDWOOD_STAIRS);
        redwoodPool.fence(BlockInit.REDWOOD_FENCE);
        redwoodPool.generateFor(BlockInit.REDWOOD_FAMILY);
        redwoodPool.generateFor(BlockInit.REDWOOD_HANGING_FAMILY);
        redwoodPool.fenceGate(BlockInit.REDWOOD_FENCE_GATE);
        redwoodPool.pressurePlate(BlockInit.REDWOOD_PRESSURE_PLATE);
        blockStateModelGenerator.woodProvider(BlockInit.REDWOOD_LOG).logWithHorizontal(BlockInit.REDWOOD_LOG).wood(BlockInit.REDWOOD_WOOD);
        blockStateModelGenerator.woodProvider(BlockInit.STRIPPED_REDWOOD_LOG).logWithHorizontal(BlockInit.STRIPPED_REDWOOD_LOG).wood(BlockInit.STRIPPED_REDWOOD_WOOD);
        blockStateModelGenerator.createDoor(BlockInit.REDWOOD_DOOR);
        blockStateModelGenerator.createTrapdoor(BlockInit.REDWOOD_TRAPDOOR);
    }

    @Override
    public void generateItemModels(ItemModelGenerators itemModelGenerator) {
        itemModelGenerator.generateFlatItem(ItemInit.REDWOOD_BOAT, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ItemInit.GRIZZLY_SPAWN_EGG, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ItemInit.RESOURCE_REALM, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ItemInit.REDWOOD_CHEST_BOAT, ModelTemplates.FLAT_ITEM);
    }

}
