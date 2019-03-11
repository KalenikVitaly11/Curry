package com.styleru.curry.data.network.dataStore;

import com.styleru.curry.data.models.recipe.Recipe;
import com.styleru.curry.domain.cuisine.models.CuisineRecipes;

import io.reactivex.Single;


/**
 * Хранилище данных с сервера
 */
public interface WebDataStore {

    /**
     * Метод для получения рецептов определенной кухни (используется на главном экране)
     * @param cuisine Кухня, рецепты которой необходимо получить
     */
    Single<CuisineRecipes> getCuisineRecipes(String cuisine);

    /**
     * Метод для получения подробной инфы о рецепте по id
     * @param id id рецепта
     */
    Single<Recipe> getRecipeById(int id);

}
