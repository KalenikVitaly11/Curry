package com.styleru.curry.data.repositories.recipe;

import com.styleru.curry.data.local.dataStore.DbDataStore;
import com.styleru.curry.data.models.recipe.Recipe;
import com.styleru.curry.data.network.dataStore.WebDataStore;
import com.styleru.curry.domain.recipe.RecipeRepository;

import javax.inject.Inject;

import io.reactivex.Single;

public class RecipeRepositoryImpl implements RecipeRepository {

    private WebDataStore webDataStore;
    private DbDataStore dbDataStore;

    @Inject
    public RecipeRepositoryImpl(WebDataStore webDataStore, DbDataStore dbDataStore) {
        this.webDataStore = webDataStore;
        this.dbDataStore = dbDataStore;
    }

    @Override
    public Single<Recipe> getRecipeById(int id) {
        Recipe recipeFromDb = dbDataStore.getRecipeById(id);
        if (recipeFromDb != null) {
            recipeFromDb.setfRomDb(true);
            return Single.just(recipeFromDb);
        } else {
            return webDataStore.getRecipeById(id);
        }
    }
}
