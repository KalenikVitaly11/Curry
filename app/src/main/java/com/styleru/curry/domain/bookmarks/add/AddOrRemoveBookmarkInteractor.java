package com.styleru.curry.domain.bookmarks.add;

import com.styleru.curry.data.models.recipe.Recipe;

public interface AddOrRemoveBookmarkInteractor {

    boolean saveRecipe(Recipe recipe);
}
