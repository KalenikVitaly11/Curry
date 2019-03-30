package com.styleru.curry.domain.bookmarks;

import com.styleru.curry.data.models.recipe.Recipe;

import java.util.List;

import io.reactivex.Single;

public interface BookmarksRepository {

    void saveRecipe(Recipe recipe);

    void removeRecipe(Recipe recipe);

    Single<List<Recipe>> getRecipes();

    boolean checkRecipe(Recipe recipe);
}
