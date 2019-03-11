package com.styleru.curry.data.repositories.recipe;

import com.styleru.curry.data.models.recipe.Recipe;
import com.styleru.curry.data.network.dataStore.WebDataStore;
import com.styleru.curry.domain.recipe.RecipeRepository;

import javax.inject.Inject;

import io.reactivex.Single;

public class RecipeRepositoryImpl implements RecipeRepository {

    private WebDataStore webDataStore;

    @Inject
    public RecipeRepositoryImpl(WebDataStore webDataStore) {
        this.webDataStore = webDataStore;
    }

    @Override
    public Single<Recipe> getRecipeById(int id) {
        return webDataStore.getRecipeById(id);
    }
}
