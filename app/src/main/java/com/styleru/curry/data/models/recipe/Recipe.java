package com.styleru.curry.data.models.recipe;

import com.styleru.curry.data.models.Ingredient;

import java.util.List;

public class Recipe {

    private int id;
    private boolean vegetarian;
    private boolean vegan;
    private boolean glutenFree;
    private boolean dairyFree;
    private boolean veryHealthy;
    private boolean cheap;
    private boolean veryPopular;
    private boolean sustainable;
    private int weightWatcherSmartPoints;
    private String gaps;
    private boolean lowFodmap;
    private boolean ketogenic;
    private boolean whole30;
    private int servings;
    private String sourceUrl;
    private List<Ingredient> ingredientsList;
    private String title;
    private int readyInMinutes;
    private String imageUrl;
    private String instructions;
}
