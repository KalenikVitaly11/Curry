package com.styleru.curry.domain.bookmarks.get;

import com.styleru.curry.data.models.recipe.Recipe;

import java.util.List;

public interface GetBookmarksInteractor {

    List<Recipe> getRecipes();
}
