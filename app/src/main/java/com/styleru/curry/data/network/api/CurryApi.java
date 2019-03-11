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
     */
    @GET("/recipes/search")
    Single<RecipeResponse> getRecipes(@Header("X-RapidAPI-Key") String key, @Query("cuisine") String cuisine);

    /**
     * Метод для получения подробной инфы о рецепте по id
     * @param key Ключ API
     * @param id id рецепта
     */
    @GET("/recipes/{id}/information")
    Single<Recipe> getRecipeById(@Header("X-RapidAPI-Key") String key, @Path("id") int id);


}
