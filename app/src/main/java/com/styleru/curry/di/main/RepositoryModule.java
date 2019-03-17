package com.styleru.curry.di.main;

import com.styleru.curry.data.db.dataStore.DbDataStore;
import com.styleru.curry.data.network.dataStore.WebDataStore;
import com.styleru.curry.data.repositories.bookmarks.BookmarksRepositoryImpl;
import com.styleru.curry.data.repositories.cuisine.CuisineRecipesRepositoryImpl;
import com.styleru.curry.data.repositories.recipe.RecipeRepositoryImpl;
import com.styleru.curry.data.repositories.search.SearchRepositoryImpl;
import com.styleru.curry.domain.bookmarks.BookmarksRepository;
import com.styleru.curry.domain.cuisine.CuisineRecipesRepository;
import com.styleru.curry.domain.recipe.RecipeRepository;
import com.styleru.curry.domain.search.SearchRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class RepositoryModule {

    @Provides
    @Singleton
    public BookmarksRepository provideBookmarksRepository(DbDataStore dbDataStored){
        return new BookmarksRepositoryImpl(dbDataStored);
    }

    @Provides
    @Singleton
    public CuisineRecipesRepository provideCuisineRecipesRepository(WebDataStore webDataStore){
        return new CuisineRecipesRepositoryImpl(webDataStore);
    }

    @Provides
    @Singleton
    public RecipeRepository provideRecipeRepository(WebDataStore webDataStore, DbDataStore dbDataStore){
        return new RecipeRepositoryImpl(webDataStore, dbDataStore);
    }

    @Provides
    @Singleton
    public SearchRepository provideSearchRepository(WebDataStore webDataStore){
        return new SearchRepositoryImpl(webDataStore);
    }

}
