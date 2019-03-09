package com.styleru.curry.data.models.recipe;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RecipeResponse {

    @SerializedName("results")
    private List<ShortRecipe> recipeList;

    public List<ShortRecipe> getRecipeList() {
        return recipeList;
    }

    public void setRecipeList(List<ShortRecipe> recipeList) {
        this.recipeList = recipeList;
    }
}
