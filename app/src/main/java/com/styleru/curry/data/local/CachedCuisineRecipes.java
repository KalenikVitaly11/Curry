package com.styleru.curry.data.local;

import com.styleru.curry.domain.cuisine.models.CuisineRecipes;

import java.util.ArrayList;
import java.util.List;

public class CachedCuisineRecipes {
    private static final CachedCuisineRecipes ourInstance = new CachedCuisineRecipes();

    private List<CuisineRecipes> recipes = new ArrayList<>();

    private boolean ifFull = false;
    private int currentIndex = 0;

    public static CachedCuisineRecipes getInstance() {
        return ourInstance;
    }

    public void addRecipes(CuisineRecipes cuisineRecipes) {
        if (!ifFull)
            recipes.add(cuisineRecipes);

        if (recipes.size() >= 3) {
            ifFull = true;
        }
    }

    public CuisineRecipes getRecipes() {
        if (recipes.size() > (currentIndex++) % 3) {
            currentIndex %= 3;
            return recipes.get(currentIndex);
        }
        return null;
    }
}
