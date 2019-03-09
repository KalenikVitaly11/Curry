package com.styleru.curry.data.network.api;

import com.styleru.curry.data.models.recipe.RecipeResponse;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface CurryApi {

    /**
     * Метод для получения рецептов определенной кухни (используется на главном экране)
     * @param key Ключ API
     * @param cuisine Кухня, рецепты которой необходимо получить
     * @return Ответ с сервера, очевидно
     */
    @GET("/recipes/search")
    Single<RecipeResponse> getRecipes(@Header("X-RapidAPI-Key") String key, @Query("cuisine") String cuisine);
}
