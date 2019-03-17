package com.styleru.curry.domain.bookmarks;

import com.styleru.curry.data.models.recipe.Recipe;

import java.util.List;

public interface BookmarksRepository {

    void saveRecipe(Recipe recipe);

    void removeRecipe(Recipe recipe);

    List<Recipe> getRecipes();

    boolean checkRecipe(Recipe recipe);
}
