package com.styleru.curry.data.network.api;

import com.styleru.curry.data.models.recipe.Recipe;
import com.styleru.curry.data.models.recipe.RecipeResponse;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface CurryApi {

    /**
     * Метод для получения рецептов определенной кухни (используется на главном экране)
     * @param key Ключ API
     * @param cuisine Кухня, рецепты которой необходимо получить
     * @return Ответ сервера
     */
    @GET("/recipes/search")
    Single<RecipeResponse> getRecipes(@Header("X-RapidAPI-Key") String key, @Query("cuisine") String cuisine);

    /**
     * Метод для получения подробной инфы о рецепте по id
     * @param key Ключ API
     * @param id id рецепта
     * @return Ответ сервера
     */
    @GET("/recipes/{id}/information")
    Single<Recipe> getRecipeById(@Header("X-RapidAPI-Key") String key, @Path("id") int id);


    /**
     * Метод для получения рецептов по заданным фильтрам
     * @param key Ключ API
     * @param query Запрос пользователя
     * @param cuisine Кухня, рецепты которой необходимо получить
     * @param diet Диета (vegan или vegetarian)
     * @param type Тип блюда (main course, side dish, dessert, appetizer, salad, bread, breakfast, soup, beverage, sauce, or drink)
     * @return Ответ сервера
     */
    @GET("/recipes/search")
    Single<RecipeResponse> getRecipesComplex(@Header("X-RapidAPI-Key") String key, @Query("query") String query,
                               @Query("cuisine") String cuisine, @Query("diet") String diet, @Query("type") String type, @Query("offset") int offset);
}
