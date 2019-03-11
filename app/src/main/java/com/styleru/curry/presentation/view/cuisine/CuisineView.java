package com.styleru.curry.presentation.view.cuisine;

import com.styleru.curry.domain.cuisine.models.CuisineRecipes;

public interface CuisineView {

    void setDataOne(CuisineRecipes cuisineRecipes);
    void setDataTwo(CuisineRecipes cuisineRecipes);
    void setDataThree(CuisineRecipes cuisineRecipes);

    void showError();
}
