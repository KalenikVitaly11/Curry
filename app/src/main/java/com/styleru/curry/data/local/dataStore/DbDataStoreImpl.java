package com.styleru.curry.data.local.dataStore;

import com.styleru.curry.data.local.RecipeDatabase;
import com.styleru.curry.data.models.recipe.Recipe;

import java.util.List;

import javax.inject.Inject;

public class DbDataStoreImpl implements DbDataStore {

    private RecipeDatabase database;

    @Inject
    public DbDataStoreImpl(RecipeDatabase database){
        this.database = database;
    }

    @Override
    public void saveRecipe(Recipe recipe) {
        database.getRecipeDao().insert(recipe);
    }

    @Override
    public void removeRecipe(Recipe recipe) {
        database.getRecipeDao().delete(recipe);
    }

    @Override
    public List<Recipe> getRecipes() {
        return database.getRecipeDao().getRecipes();
    }

    @Override
    public Recipe getRecipeById(int id) {
        return database.getRecipeDao().getRecipeById(id);
    }
}
