package com.styleru.curry;

import android.app.Application;
import android.arch.persistence.room.Room;

import com.styleru.curry.data.db.RecipeDatabase;
import com.styleru.curry.di.cuisine.CuisineComponent;
import com.styleru.curry.di.cuisine.DaggerCuisineComponent;
import com.styleru.curry.di.recipe.DaggerRecipeComponent;
import com.styleru.curry.di.recipe.RecipeComponent;
import com.styleru.curry.di.search.DaggerSearchComponent;
import com.styleru.curry.di.search.SearchComponent;

public class CurryApplication extends Application {

    private static CuisineComponent cuisineComponent;
    private static RecipeComponent recipeComponent;
    private static SearchComponent searchComponent;

    private static String RECIPES_DB_NAME = "recipe_database";
    private static RecipeDatabase recipeDatabase;

    public static CuisineComponent getCuisineComponent(){
        return cuisineComponent;
    }

    public static RecipeComponent getRecipeComponent(){
        return recipeComponent;
    }

    public static SearchComponent getSearchComponent(){
        return searchComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        cuisineComponent = buildCuisineComponent();
        recipeComponent = buildRecipeComponent();
        searchComponent = buildSearchComponent();
        recipeDatabase = buildRecipeDatabase();
    }

    private RecipeDatabase buildRecipeDatabase(){
        return Room.databaseBuilder(getApplicationContext(), RecipeDatabase.class, RECIPES_DB_NAME)
                .allowMainThreadQueries()
                .build();
    }

    private CuisineComponent buildCuisineComponent(){
        return DaggerCuisineComponent
                .builder()
                .build();
    }

    private RecipeComponent buildRecipeComponent(){
        return DaggerRecipeComponent
                .builder()
                .build();
    }
    private SearchComponent buildSearchComponent(){
        return DaggerSearchComponent
                .builder()
                .build();
    }



}
