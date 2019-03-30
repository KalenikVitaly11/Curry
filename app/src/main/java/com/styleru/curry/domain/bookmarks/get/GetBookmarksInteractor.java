package com.styleru.curry.domain.bookmarks.get;

import com.styleru.curry.data.models.recipe.Recipe;

import java.util.List;

import io.reactivex.Single;

public interface GetBookmarksInteractor {

    Single<List<Recipe>> getRecipes();
}
