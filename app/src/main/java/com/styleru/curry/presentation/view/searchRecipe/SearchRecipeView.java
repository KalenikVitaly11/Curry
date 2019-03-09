package com.styleru.curry.presentation.view.searchRecipe;

import com.styleru.curry.domain.cuisine.models.CuisineRecipes;

public interface SearchRecipeView {

    void setData(CuisineRecipes cuisineRecipes);

    void showError();
}
