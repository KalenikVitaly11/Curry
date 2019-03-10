package com.styleru.curry.presentation.view.cuisine;

import com.styleru.curry.domain.cuisine.models.CuisineRecipes;

public interface CuisineView {

    void setData(CuisineRecipes cuisineRecipes);

    void showError();
}
