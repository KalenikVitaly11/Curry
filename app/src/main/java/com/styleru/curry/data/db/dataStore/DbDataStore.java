package com.styleru.curry.data.db.dataStore;

import com.styleru.curry.data.models.recipe.Recipe;

import java.util.List;

public interface DbDataStore {

    void saveRecipe(Recipe recipe);

    void removeRecipe(Recipe recipe);

    List<Recipe> getRecipes();

    Recipe getRecipeById(int id);
}
