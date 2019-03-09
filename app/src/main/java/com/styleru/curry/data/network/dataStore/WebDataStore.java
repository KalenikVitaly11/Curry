package com.styleru.curry.data.network.dataStore;

import com.styleru.curry.domain.cuisine.models.CuisineRecipes;

import io.reactivex.Single;


/**
 * Хранилище данных с сервера
 */
public interface WebDataStore {

    /**
     * Метод для получения рецептов определенной кухни (используется на главном экране)
     * @param cuisine Кухня, рецепты которой необходимо получить
     * @return Ответ с сервера, очевидно
     */
    Single<CuisineRecipes> getCuisineRecipes(String cuisine);
}
