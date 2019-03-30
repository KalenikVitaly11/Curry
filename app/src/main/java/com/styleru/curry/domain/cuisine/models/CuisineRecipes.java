package com.styleru.curry.domain.cuisine.models;

import com.styleru.curry.data.models.recipe.ShortRecipe;

import java.util.ArrayList;
import java.util.List;

/**
 * Объект, содержащий кухню и рецепты к ней
 */
public class CuisineRecipes {

    private String cuisine;
    private List<ShortRecipe> shortRecipes;

    public CuisineRecipes(){}

    public CuisineRecipes(List<ShortRecipe> recipes, String cuisine){
        this.shortRecipes = new ArrayList<>();
        this.shortRecipes.addAll(recipes);
        this.cuisine = cuisine;
    }

    public CuisineRecipes(CuisineRecipes cuisineRecipes){
        this.shortRecipes = new ArrayList<>();
        this.shortRecipes.addAll(cuisineRecipes.getShortRecipes());
        this.cuisine = cuisineRecipes.getCuisine();
    }

    public List<ShortRecipe> getShortRecipes() {
        return shortRecipes;
    }

    public String getCuisine() {
        return cuisine;
    }

    public void setCuisine(String cuisine) {
        this.cuisine = cuisine;
    }

    public void setShortRecipes(List<ShortRecipe> shortRecipes) {
        this.shortRecipes = shortRecipes;
    }
}
