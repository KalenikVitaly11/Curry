package com.styleru.curry;

import android.app.Application;
import android.arch.persistence.room.Room;

import com.styleru.curry.data.local.RecipeDatabase;
import com.styleru.curry.di.bookmarks.BookmarksComponent;
import com.styleru.curry.di.bookmarks.BookmarksModule;
import com.styleru.curry.di.cuisine.CuisineComponent;
import com.styleru.curry.di.cuisine.CuisineModule;
import com.styleru.curry.di.main.AppComponent;

import com.styleru.curry.di.main.DaggerAppComponent;
import com.styleru.curry.di.recipe.RecipeComponent;
import com.styleru.curry.di.recipe.RecipeModule;
import com.styleru.curry.di.search.SearchComponent;
import com.styleru.curry.di.search.SearchModule;

public class CurryApplication extends Application {

    private static CuisineComponent cuisineComponent;
    private static RecipeComponent recipeComponent;
    private static SearchComponent searchComponent;
    private static BookmarksComponent bookmarksComponent;

    private static AppComponent appComponent;


    private static String RECIPES_DB_NAME = "recipe_database";
    private static RecipeDatabase recipeDatabase;

    public static RecipeDatabase getRecipeDatabase(){
        return recipeDatabase;
    }

    public static AppComponent getAppComponent(){
        return appComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        recipeDatabase = buildRecipeDatabase();

        appComponent = buildAppComponent();
    }

    private RecipeDatabase buildRecipeDatabase(){
        return Room.databaseBuilder(getApplicationContext(), RecipeDatabase.class, RECIPES_DB_NAME)
                .allowMainThreadQueries()
                .build();
    }

    private AppComponent buildAppComponent(){
        return DaggerAppComponent
                .builder()
                .build();
    }

    public static CuisineComponent addCuisineComponent(){
        if(cuisineComponent == null){
            cuisineComponent = appComponent.cuisineComponent(new CuisineModule());
        }
        return cuisineComponent;
    }

    public static void clearCuisineComponent(){
        cuisineComponent = null;
    }

    public static RecipeComponent addRecipeComponent(){
        if(recipeComponent == null){
            recipeComponent = appComponent.recipeComponent(new RecipeModule());
        }
        return recipeComponent;
    }

    public static void clearRecipeComponent(){
        recipeComponent = null;
    }

    public static SearchComponent addSearchComponent(){
        if(searchComponent == null){
            searchComponent = appComponent.searchComponent(new SearchModule());
        }
        return searchComponent;
    }

    public static void clearSearchComponent(){
        searchComponent = null;
    }

    public static BookmarksComponent addBookmarksComponent(){
        if(bookmarksComponent == null){
            bookmarksComponent = appComponent.bookmarksComponent(new BookmarksModule());
        }
        return bookmarksComponent;
    }

    public static void clearBookmarksComponent(){
        bookmarksComponent = null;
    }


}
