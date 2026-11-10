package github.jcsmecabricks.redwoodvariants.data.provider;

import github.jcsmecabricks.redwoodvariants.init.BlockInit;
import github.jcsmecabricks.redwoodvariants.init.ItemInit;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.advancements.Advancement;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.block.Blocks;
import java.util.concurrent.CompletableFuture;

public class RedwoodVariantsRecipeProvider extends FabricRecipeProvider {
    public RedwoodVariantsRecipeProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected RecipeProvider createRecipeProvider(HolderLookup.Provider wrapperLookup, BootstrapContext<Recipe<?>> recipes, BootstrapContext<Advancement> advancments) {
        return new RecipeProvider(recipes, advancments) {
            @Override
            public void buildRecipes() {
                shaped(RecipeCategory.MISC, Items.STICK, 4)
                        .define('A', BlockInit.REDWOOD_PLANKS)
                        .pattern(" A")
                        .pattern(" A")
                        .unlockedBy(getHasName(BlockInit.REDWOOD_PLANKS), has(BlockInit.REDWOOD_PLANKS))
                        .save(output);
                shaped(RecipeCategory.MISC, Blocks.CRAFTING_TABLE)
                        .define('E', BlockInit.REDWOOD_PLANKS)
                        .pattern("EE")
                        .pattern("EE")
                        .unlockedBy(getHasName(BlockInit.REDWOOD_PLANKS), has(BlockInit.REDWOOD_PLANKS))
                        .save(output);
                shaped(RecipeCategory.MISC, BlockInit.REDWOOD_FENCE_GATE)
                        .define('E', BlockInit.REDWOOD_PLANKS)
                        .define('A', Items.STICK)
                        .pattern("EAE")
                        .pattern("EAE")
                        .unlockedBy(getHasName(BlockInit.REDWOOD_PLANKS), has(BlockInit.REDWOOD_PLANKS))
                        .unlockedBy(getHasName(Items.STICK), has(Items.STICK))
                        .save(output);
                shaped(RecipeCategory.MISC, BlockInit.REDWOOD_TRAPDOOR)
                        .define('E', BlockInit.REDWOOD_PLANKS)
                        .pattern("EEE")
                        .pattern("EEE")
                        .unlockedBy(getHasName(BlockInit.REDWOOD_PLANKS), has(BlockInit.REDWOOD_PLANKS))
                        .save(output);
                shaped(RecipeCategory.MISC, BlockInit.REDWOOD_DOOR, 3)
                        .define('E', BlockInit.REDWOOD_PLANKS)
                        .pattern("EE ")
                        .pattern("EE ")
                        .pattern("EE ")
                        .unlockedBy(getHasName(BlockInit.REDWOOD_PLANKS), has(BlockInit.REDWOOD_PLANKS))
                        .save(output);
                shaped(RecipeCategory.MISC, BlockInit.REDWOOD_FENCE, 3)
                        .define('E', BlockInit.REDWOOD_PLANKS)
                        .define('A', Items.STICK)
                        .pattern("AEA")
                        .pattern("AEA")
                        .unlockedBy(getHasName(BlockInit.REDWOOD_PLANKS), has(BlockInit.REDWOOD_PLANKS))
                        .unlockedBy(getHasName(Items.STICK), has(Items.STICK))
                        .save(output);
                shaped(RecipeCategory.MISC, BlockInit.REDWOOD_SLAB, 6)
                        .define('E', BlockInit.REDWOOD_PLANKS)
                        .pattern("EEE")
                        .unlockedBy(getHasName(BlockInit.REDWOOD_PLANKS), has(BlockInit.REDWOOD_PLANKS))
                        .save(output);
                shapeless(RecipeCategory.MISC, BlockInit.REDWOOD_BUTTON)
                        .requires(BlockInit.REDWOOD_PLANKS)
                        .unlockedBy(getHasName(BlockInit.REDWOOD_PLANKS), has(BlockInit.REDWOOD_PLANKS))
                        .save(output);
                shaped(RecipeCategory.MISC, BlockInit.REDWOOD_PRESSURE_PLATE)
                        .define('E', BlockInit.REDWOOD_PLANKS)
                        .pattern("EE")
                        .unlockedBy(getHasName(BlockInit.REDWOOD_PLANKS), has(BlockInit.REDWOOD_PLANKS))
                        .save(output);
                shaped(RecipeCategory.MISC, BlockInit.REDWOOD_STAIRS, 4)
                        .define('E', BlockInit.REDWOOD_PLANKS)
                        .pattern("  E")
                        .pattern(" EE")
                        .pattern("EEE")
                        .unlockedBy(getHasName(BlockInit.REDWOOD_PLANKS), has(BlockInit.REDWOOD_PLANKS))
                        .save(output);
                shaped(RecipeCategory.MISC, BlockInit.REDWOOD_WOOD, 3)
                        .define('E', BlockInit.REDWOOD_LOG)
                        .pattern("EE")
                        .pattern("EE")
                        .unlockedBy(getHasName(BlockInit.REDWOOD_LOG), has(BlockInit.REDWOOD_LOG))
                        .save(output);
                shaped(RecipeCategory.MISC, BlockInit.STRIPPED_REDWOOD_WOOD, 3)
                        .define('E', BlockInit.STRIPPED_REDWOOD_LOG)
                        .pattern("EE")
                        .pattern("EE")
                        .unlockedBy(getHasName(BlockInit.STRIPPED_REDWOOD_LOG), has(BlockInit.STRIPPED_REDWOOD_LOG))
                        .save(output);
                shaped(RecipeCategory.MISC, Items.WOODEN_AXE)
                        .define('E', BlockInit.REDWOOD_PLANKS)
                        .define('A', Items.STICK)
                        .pattern(" EE")
                        .pattern(" AE")
                        .pattern(" A ")
                        .unlockedBy(getHasName(BlockInit.REDWOOD_PLANKS), has(BlockInit.REDWOOD_PLANKS))
                        .save(output);
                shaped(RecipeCategory.MISC, ItemInit.RESOURCE_REALM)
                        .define('E', BlockInit.REDWOOD_LOG)
                        .define('A', Items.DIAMOND)
                        .define('S', Items.STICK)
                        .pattern("EAE")
                        .pattern("ASA")
                        .pattern("EAE")
                        .unlockedBy(getHasName(BlockInit.REDWOOD_LOG), has(BlockInit.REDWOOD_LOG))
                        .unlockedBy(getHasName(Items.DIAMOND), has(Items.DIAMOND))
                        .unlockedBy(getHasName(Items.STICK), has(Items.STICK))
                        .save(output);
                shaped(RecipeCategory.MISC, Items.WOODEN_HOE)
                        .define('E', BlockInit.REDWOOD_PLANKS)
                        .define('A', Items.STICK)
                        .pattern(" EE")
                        .pattern(" A ")
                        .pattern(" A ")
                        .unlockedBy(getHasName(BlockInit.REDWOOD_PLANKS), has(BlockInit.REDWOOD_PLANKS))
                        .unlockedBy(getHasName(Items.STICK), has(Items.STICK))
                        .save(output);
                shaped(RecipeCategory.MISC, Items.WOODEN_PICKAXE)
                        .define('E', BlockInit.REDWOOD_PLANKS)
                        .define('A', Items.STICK)
                        .pattern("EEE")
                        .pattern(" A ")
                        .pattern(" A ")
                        .unlockedBy(getHasName(BlockInit.REDWOOD_PLANKS), has(BlockInit.REDWOOD_PLANKS))
                        .unlockedBy(getHasName(Items.STICK), has(Items.STICK))
                        .save(output);
                shaped(RecipeCategory.MISC, Items.WOODEN_SHOVEL)
                        .define('E', BlockInit.REDWOOD_PLANKS)
                        .define('A', Items.STICK)
                        .pattern(" E ")
                        .pattern(" A ")
                        .pattern(" A ")
                        .unlockedBy(getHasName(BlockInit.REDWOOD_PLANKS), has(BlockInit.REDWOOD_PLANKS))
                        .unlockedBy(getHasName(Items.STICK), has(Items.STICK))
                        .save(output);
                shaped(RecipeCategory.MISC, Items.WOODEN_SWORD)
                        .define('E', BlockInit.REDWOOD_PLANKS)
                        .define('A', Items.STICK)
                        .pattern(" E ")
                        .pattern(" E ")
                        .pattern(" A ")
                        .unlockedBy(getHasName(BlockInit.REDWOOD_PLANKS), has(BlockInit.REDWOOD_PLANKS))
                        .unlockedBy(getHasName(Items.STICK), has(Items.STICK))
                        .save(output);
                shaped(RecipeCategory.MISC, Blocks.CHEST)
                        .define('E', BlockInit.REDWOOD_PLANKS)
                        .pattern("EEE")
                        .pattern("E E")
                        .pattern("EEE")
                        .unlockedBy(getHasName(BlockInit.REDWOOD_PLANKS), has(BlockInit.REDWOOD_PLANKS))
                        .unlockedBy(getHasName(Items.STICK), has(Items.STICK))
                        .save(output);
                shaped(RecipeCategory.MISC, ItemInit.REDWOOD_HANGING_SIGN, 6)
                        .define('E', BlockInit.STRIPPED_REDWOOD_LOG)
                        .define('A', Blocks.IRON_CHAIN)
                        .pattern("A A")
                        .pattern("EEE")
                        .pattern("EEE")
                        .unlockedBy(getHasName(BlockInit.STRIPPED_REDWOOD_LOG), has(BlockInit.STRIPPED_REDWOOD_LOG))
                        .unlockedBy(getHasName(Blocks.IRON_CHAIN), has(Blocks.IRON_CHAIN))
                        .save(output);
                shaped(RecipeCategory.MISC, ItemInit.REDWOOD_BOAT)
                        .define('E', BlockInit.REDWOOD_PLANKS)
                        .pattern("E E")
                        .pattern("EEE")
                        .unlockedBy(getHasName(BlockInit.REDWOOD_PLANKS), has(BlockInit.REDWOOD_PLANKS))
                        .save(output);
                shapeless(RecipeCategory.MISC, ItemInit.REDWOOD_CHEST_BOAT)
                        .requires(ItemInit.REDWOOD_BOAT)
                        .requires(Blocks.CHEST)
                        .unlockedBy(getHasName(Blocks.CHEST), has(Blocks.CHEST))
                        .unlockedBy(getHasName(ItemInit.REDWOOD_BOAT), has(ItemInit.REDWOOD_BOAT))
                        .save(output);
                shaped(RecipeCategory.MISC, ItemInit.REDWOOD_SIGN, 3)
                        .define('E', BlockInit.REDWOOD_PLANKS)
                        .define('A', Items.STICK)
                        .pattern("EEE")
                        .pattern("EEE")
                        .pattern(" A ")
                        .unlockedBy(getHasName(BlockInit.REDWOOD_PLANKS), has(BlockInit.REDWOOD_PLANKS))
                        .unlockedBy(getHasName(Items.STICK), has(Items.STICK))
                        .save(output);
                shapeless(RecipeCategory.MISC, BlockInit.REDWOOD_PLANKS, 4)
                        .requires(BlockInit.REDWOOD_LOG)
                        .unlockedBy(getHasName(BlockInit.REDWOOD_LOG), has(BlockInit.REDWOOD_LOG))
                        .save(output);
                shapeless(RecipeCategory.MISC, BlockInit.REDWOOD_PLANKS, 4)
                        .requires(BlockInit.REDWOOD_WOOD)
                        .unlockedBy(getHasName(BlockInit.REDWOOD_WOOD), has(BlockInit.REDWOOD_WOOD))
                        .save(output, "redwood_planks_from_wood");
                shapeless(RecipeCategory.MISC, BlockInit.REDWOOD_PLANKS, 4)
                        .requires(BlockInit.STRIPPED_REDWOOD_LOG)
                        .unlockedBy(getHasName(BlockInit.STRIPPED_REDWOOD_LOG), has(BlockInit.STRIPPED_REDWOOD_LOG))
                        .save(output, "redwood_planks_from_stripped_log");
                shapeless(RecipeCategory.MISC, BlockInit.REDWOOD_PLANKS, 4)
                        .requires(BlockInit.STRIPPED_REDWOOD_WOOD)
                        .unlockedBy(getHasName(BlockInit.STRIPPED_REDWOOD_WOOD), has(BlockInit.STRIPPED_REDWOOD_WOOD))
                        .save(output, "redwood_planks_from_stripped_wood");
            }
            private static String hasTag(TagKey<Item> tag) {
                return "has_" + tag.location().toString();
            }
        };
    }

    @Override
    public String getName() {
        return "Redwood Variants Recipes";
    }
}
