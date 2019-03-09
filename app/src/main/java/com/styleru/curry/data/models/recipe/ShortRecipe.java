package com.styleru.curry.data.models.recipe;

import com.google.gson.annotations.SerializedName;
import com.styleru.curry.data.models.Ingredient;

import java.util.List;

/**
 * Объект краткой инфы о рецепте (используется в списках на начальном экране)
 */
public class ShortRecipe {

    private int id;
    private String title;
    private int readyInMinutes;
    @SerializedName("image")
    private String imageUrl;
    private int servings;

    public String getTitle() {
        return title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public int getId() {
        return id;
    }

    public int getReadyInMinutes() {
        return readyInMinutes;
    }

    public int getServings() {
        return servings;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setReadyInMinutes(int readyInMinutes) {
        this.readyInMinutes = readyInMinutes;
    }

    public void setServings(int servings) {
        this.servings = servings;
    }
}
