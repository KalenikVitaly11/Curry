package com.styleru.curry.data.network.dataStore;

import com.styleru.curry.data.models.recipe.Recipe;
import com.styleru.curry.data.models.recipe.RecipeResponse;
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

    /**
     * Метод для получения рецептов по заданным фильтрам
     * @param query Запрос пользователя
     * @param cuisine Кухня, рецепты которой необходимо получить
     * @param diet Диета (vegan или vegetarian)
     * @param type Тип блюда (main course, side dish, dessert, appetizer, salad, bread, breakfast, soup, beverage, sauce, or drink)
     * @return Ответ сервера
     */
    Single<RecipeResponse> getRecipesComplex(String query, String cuisine, String diet, String type);

}
