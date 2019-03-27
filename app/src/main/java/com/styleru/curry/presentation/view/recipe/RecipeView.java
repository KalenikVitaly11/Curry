package com.styleru.curry.presentation.view.recipe;

import com.styleru.curry.data.models.recipe.Recipe;

public interface RecipeView {

    void setDataToViews(Recipe recipe);

    void showError();

    void showEmptyIcon();

    void showFilledIcon();

    void showViews();

    void initViewPager(Recipe recipe);

    void initListeners();
}
