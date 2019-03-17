package com.styleru.curry.presentation.view.bookmarks;

import com.styleru.curry.data.models.recipe.Recipe;

import java.util.List;

public interface BookmarksView {

    void setData(List<Recipe> recipeList);

    void showError();
}
