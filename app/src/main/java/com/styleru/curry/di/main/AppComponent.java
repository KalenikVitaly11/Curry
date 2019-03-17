package com.styleru.curry.di.main;

import com.styleru.curry.di.bookmarks.BookmarksComponent;
import com.styleru.curry.di.bookmarks.BookmarksModule;
import com.styleru.curry.di.cuisine.CuisineComponent;
import com.styleru.curry.di.cuisine.CuisineModule;
import com.styleru.curry.di.recipe.RecipeComponent;
import com.styleru.curry.di.recipe.RecipeModule;
import com.styleru.curry.di.search.SearchComponent;
import com.styleru.curry.di.search.SearchModule;
import com.styleru.curry.presentation.view.main.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

@Component(modules = {RepositoryModule.class, DataStoreModule.class})
@Singleton
public interface AppComponent {

    void inject(MainActivity mainActivity);

    BookmarksComponent bookmarksComponent(BookmarksModule bookmarksModule);

    CuisineComponent cuisineComponent(CuisineModule cuisineModule);

    RecipeComponent recipeComponent(RecipeModule recipeModule);

    SearchComponent searchComponent(SearchModule searchModule);
}
