package com.styleru.curry.data.models.typeConverters;

import android.arch.persistence.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.styleru.curry.data.models.Ingredient;

import java.util.ArrayList;
import java.util.List;

public class IngredientConverter {

    @TypeConverter
    public String fromIngredient(ArrayList<Ingredient> ingredientList){
        return  new Gson().toJson(ingredientList);
    }

    @TypeConverter
    public ArrayList<Ingredient> toIngredient(String json){
        return new Gson().fromJson(json, new TypeToken<ArrayList<Ingredient>>(){}.getType());
    }
}
