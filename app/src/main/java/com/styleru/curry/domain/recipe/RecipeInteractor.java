package com.styleru.curry.domain.recipe;

import com.styleru.curry.data.models.recipe.Recipe;

import io.reactivex.Single;

public interface RecipeInteractor {

    /**
     * Метод для получения подробной инфы о рецепте по id
     * @param id id рецепта
     */
    Single<Recipe> getRecipeById(int id);
}
