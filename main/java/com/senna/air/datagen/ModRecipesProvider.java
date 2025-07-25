package com.senna.air.datagen;

import com.senna.air.block.ModBlocks;
import com.senna.air.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.util.Identifier;

import java.util.List;
import java.util.function.Consumer;

public class ModRecipesProvider extends FabricRecipeProvider {

    private static final List<ItemConvertible> AIR_MOD_LIST = List.of(ModItems.HIGH_CARBON_STEEL_INGOT,ModItems.MILE_CARBON_STEEL_INGOT);

    public ModRecipesProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generate(Consumer<RecipeJsonProvider> exporter) {
        offerReversibleCompactingRecipes(exporter, RecipeCategory.MISC, ModItems.HIGH_CARBON_STEEL_INGOT,RecipeCategory.BUILDING_BLOCKS, ModBlocks.HIGH_CARBON_STEEL_BLOCK);
        offerReversibleCompactingRecipes(exporter, RecipeCategory.MISC, ModItems.MILE_CARBON_STEEL_INGOT,RecipeCategory.BUILDING_BLOCKS, ModBlocks.MILE_CARBON_STEEL_BLOCK);

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.HIGH_CARBON_STEEL_SWORD, 1)
                .pattern("#")
                .pattern("#")
                .pattern("A")
                .input('#', Ingredient.ofItems(ModItems.HIGH_CARBON_STEEL_INGOT))
                .input('A', Ingredient.ofItems(Items.STICK))
                .criterion("has_high_carbon_steel_ingot", FabricRecipeProvider.conditionsFromItem(ModItems.HIGH_CARBON_STEEL_INGOT))
                .offerTo(exporter, new Identifier("air-mod", "high_steel_ingot_sword"));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.MILE_CARBON_STEEL_SWORD, 1)
                .pattern("#")
                .pattern("#")
                .pattern("A")
                .input('#', Ingredient.ofItems(ModItems.MILE_CARBON_STEEL_INGOT))
                .input('A', Ingredient.ofItems(Items.STICK))
                .criterion("has_high_carbon_steel_ingot", FabricRecipeProvider.conditionsFromItem(ModItems.MILE_CARBON_STEEL_INGOT))
                .offerTo(exporter, new Identifier("air-mod", "mile_steel_ingot_sword"));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.HIGH_CARBON_STEEL_AXE, 1)
                .pattern("##")
                .pattern("#A")
                .pattern(" A")
                .input('#', Ingredient.ofItems(ModItems.HIGH_CARBON_STEEL_INGOT))
                .input('A', Ingredient.ofItems(Items.STICK))
                .criterion("has_high_carbon_steel_ingot", FabricRecipeProvider.conditionsFromItem(ModItems.HIGH_CARBON_STEEL_INGOT))
                .offerTo(exporter, new Identifier("air-mod", "high_steel_ingot_axe"));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.HIGH_CARBON_STEEL_HOE, 1)
                .pattern("##")
                .pattern(" A")
                .pattern(" A")
                .input('#', Ingredient.ofItems(ModItems.HIGH_CARBON_STEEL_INGOT))
                .input('A', Ingredient.ofItems(Items.STICK))
                .criterion("has_high_carbon_steel_ingot", FabricRecipeProvider.conditionsFromItem(ModItems.HIGH_CARBON_STEEL_INGOT))
                .offerTo(exporter, new Identifier("air-mod", "high_steel_ingot_hoe"));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.HIGH_CARBON_STEEL_PICKAXE, 1)
                .pattern("###")
                .pattern(" A ")
                .pattern(" A ")
                .input('#', Ingredient.ofItems(ModItems.HIGH_CARBON_STEEL_INGOT))
                .input('A', Ingredient.ofItems(Items.STICK))
                .criterion("has_high_carbon_steel_ingot", FabricRecipeProvider.conditionsFromItem(ModItems.HIGH_CARBON_STEEL_INGOT))
                .offerTo(exporter, new Identifier("air-mod", "high_steel_ingot_pickaxe"));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.HIGH_CARBON_STEEL_SHOVE, 1)
                .pattern("#")
                .pattern("A")
                .pattern("A")
                .input('#', Ingredient.ofItems(ModItems.HIGH_CARBON_STEEL_INGOT))
                .input('A', Ingredient.ofItems(Items.STICK))
                .criterion("has_high_carbon_steel_ingot", FabricRecipeProvider.conditionsFromItem(ModItems.HIGH_CARBON_STEEL_INGOT))
                .offerTo(exporter, new Identifier("air-mod", "high_steel_ingot_shove"));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.MILE_CARBON_STEEL_AXE, 1)
                .pattern("##")
                .pattern("#A")
                .pattern(" A")
                .input('#', Ingredient.ofItems(ModItems.MILE_CARBON_STEEL_INGOT))
                .input('A', Ingredient.ofItems(Items.STICK))
                .criterion("has_mile_carbon_steel_ingot", FabricRecipeProvider.conditionsFromItem(ModItems.MILE_CARBON_STEEL_INGOT))
                .offerTo(exporter, new Identifier("air-mod", "mile_steel_ingot_axe"));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.MILE_CARBON_STEEL_HOE, 1)
                .pattern("##")
                .pattern(" A")
                .pattern(" A")
                .input('#', Ingredient.ofItems(ModItems.MILE_CARBON_STEEL_INGOT))
                .input('A', Ingredient.ofItems(Items.STICK))
                .criterion("has_mile_carbon_steel_ingot", FabricRecipeProvider.conditionsFromItem(ModItems.MILE_CARBON_STEEL_INGOT))
                .offerTo(exporter, new Identifier("air-mod", "mile_steel_ingot_hoe"));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.MILE_CARBON_STEEL_PICKAXE, 1)
                .pattern("###")
                .pattern(" A ")
                .pattern(" A ")
                .input('#', Ingredient.ofItems(ModItems.MILE_CARBON_STEEL_INGOT))
                .input('A', Ingredient.ofItems(Items.STICK))
                .criterion("has_mile_carbon_steel_ingot", FabricRecipeProvider.conditionsFromItem(ModItems.MILE_CARBON_STEEL_INGOT))
                .offerTo(exporter, new Identifier("air-mod", "mile_steel_ingot_pickaxe"));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.MILE_CARBON_STEEL_SHOVE, 1)
                .pattern("#")
                .pattern("A")
                .pattern("A")
                .input('#', Ingredient.ofItems(ModItems.MILE_CARBON_STEEL_INGOT))
                .input('A', Ingredient.ofItems(Items.STICK))
                .criterion("has_mile_carbon_steel_ingot", FabricRecipeProvider.conditionsFromItem(ModItems.MILE_CARBON_STEEL_INGOT))
                .offerTo(exporter, new Identifier("air-mod", "mile_steel_ingot_shove"));

        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.FIRE_BOW, 1)
                .pattern(" #@")
                .pattern("# @")
                .pattern(" #@")
                .input('#', Items.BLAZE_ROD)
                .input('@', ModItems.FLAMED_STRING)
                .criterion("has_flamed_string", FabricRecipeProvider.conditionsFromItem(ModItems.FLAMED_STRING))
                .criterion("has_blaze_rod", FabricRecipeProvider.conditionsFromItem(Items.BLAZE_ROD))
                .offerTo(exporter, new Identifier("air", "fire_bow"));
    }
}
