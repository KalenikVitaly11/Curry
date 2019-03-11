package com.styleru.curry;

import android.app.Application;

import com.styleru.curry.di.cuisine.CuisineComponent;
import com.styleru.curry.di.cuisine.DaggerCuisineComponent;
import com.styleru.curry.di.recipe.DaggerRecipeComponent;
import com.styleru.curry.di.recipe.RecipeComponent;

public class CurryApplication extends Application {

    private static CuisineComponent cuisineComponent;
    private static RecipeComponent recipeComponent;

    public static CuisineComponent getCuisineComponent(){
        return cuisineComponent;
    }

    public static RecipeComponent getRecipeComponent(){
        return recipeComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        cuisineComponent = buildCuisineComponent();
        recipeComponent = buildRecipeComponent();
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



}
