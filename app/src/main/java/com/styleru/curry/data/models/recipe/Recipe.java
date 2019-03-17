package com.styleru.curry.data.models.recipe;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverter;

import com.google.gson.annotations.SerializedName;
import com.styleru.curry.data.models.Ingredient;
import com.styleru.curry.data.models.typeConverters.IngredientConverter;
import com.styleru.curry.presentation.view.recipe.models.AnalyzedInstruction;
import com.styleru.curry.presentation.view.recipe.models.Instruction;

import java.util.ArrayList;

@Entity
public class Recipe {
    @PrimaryKey
    private int id;
    private String title;
    private boolean vegetarian;
    private boolean vegan;
    @Ignore
    private boolean fRomDb = false;
    private int servings;
    private String sourceUrl;
    @SerializedName("extendedIngredients")
    private ArrayList<Ingredient> ingredientsList;
    private int readyInMinutes;
    @SerializedName("image")
    private String imageUrl;
    private String instruction;
    @SerializedName("analyzedInstructions")
    private ArrayList<Instruction> instructions;


    public boolean isfRomDb() {
        return fRomDb;
    }

    public void setfRomDb(boolean fRomDb) {
        this.fRomDb = fRomDb;
    }

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

    public String getInstruction() {
        return instruction;
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

    public void setInstruction(String instruction) {
        this.instruction = instruction;
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

    public ArrayList<Instruction> getInstructions() {
        return instructions;
    }

    public void setInstructions(ArrayList<Instruction> instructions) {
        this.instructions = instructions;
    }
}
