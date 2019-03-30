package com.styleru.curry.data.repositories.bookmarks;

import com.styleru.curry.data.local.dataStore.DbDataStore;
import com.styleru.curry.data.models.recipe.Recipe;
import com.styleru.curry.domain.bookmarks.BookmarksRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;

public class BookmarksRepositoryImpl implements BookmarksRepository {

    private DbDataStore dbDataStore;

    @Inject
    public BookmarksRepositoryImpl(DbDataStore dbDataStore){
        this.dbDataStore = dbDataStore;
    }

    @Override
    public void saveRecipe(Recipe recipe) {
        dbDataStore.saveRecipe(recipe);
    }

    @Override
    public void removeRecipe(Recipe recipe) {
        dbDataStore.removeRecipe(recipe);
    }

    @Override
    public Single<List<Recipe>> getRecipes() {
        return Single.just(dbDataStore.getRecipes());
    }

    @Override
    public boolean checkRecipe(Recipe recipe) {
        if(dbDataStore.getRecipeById(recipe.getId()) == null){
            return false;
        }
        return true;
    }
}
