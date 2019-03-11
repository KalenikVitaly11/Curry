package com.styleru.curry.data.models.recipe;

import com.google.gson.annotations.SerializedName;
import com.styleru.curry.data.models.Ingredient;

import java.util.ArrayList;
import java.util.List;

public class Recipe {

    private int id;
    private String title;
    private boolean vegetarian;
    private boolean vegan;
    private int servings;
    private String sourceUrl;
    @SerializedName("extendedIngredients")
    private ArrayList<Ingredient> ingredientsList;
    private int readyInMinutes;
    @SerializedName("image")
    private String imageUrl;
    private String instructions;

    public int getId() {
        return id;
    }

    public int getServings() {
        return servings;
    }

    public int getReadyInMinutes() {
        return readyInMinutes;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public ArrayList<Ingredient> getIngredientsList() {
        return ingredientsList;
    }

    public String getInstructions() {
        return instructions;
    }

    public String getSourceUrl() {
        return sourceUrl;
    }

    public boolean isVegetarian() {
        return vegetarian;
    }

    public boolean isVegan() {
        return vegan;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setServings(int servings) {
        this.servings = servings;
    }

    public void setReadyInMinutes(int readyInMinutes) {
        this.readyInMinutes = readyInMinutes;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setIngredientsList(ArrayList<Ingredient> ingredientsList) {
        this.ingredientsList = ingredientsList;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public void setSourceUrl(String sourceUrl) {
        this.sourceUrl = sourceUrl;
    }

    public void setVegan(boolean vegan) {
        this.vegan = vegan;
    }

    public void setVegetarian(boolean vegetarian) {
        this.vegetarian = vegetarian;
    }
}
