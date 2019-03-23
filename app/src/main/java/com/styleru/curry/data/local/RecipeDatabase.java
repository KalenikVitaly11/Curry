package com.styleru.curry.data.local;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;

import com.styleru.curry.data.models.recipe.Recipe;
import com.styleru.curry.data.models.typeConverters.IngredientConverter;
import com.styleru.curry.data.models.typeConverters.InstructionConverter;

@Database(entities = {Recipe.class}, version = 1)
@TypeConverters({IngredientConverter.class, InstructionConverter.class})
public abstract class RecipeDatabase  extends RoomDatabase {

    public abstract RecipeDAO getRecipeDao();
}
