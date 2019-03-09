package com.styleru.curry.domain.cuisine;

import com.styleru.curry.domain.cuisine.models.CuisineRecipes;

import io.reactivex.Single;

public interface CuisineRecipesInteractor {

    /**
     * Метод для получения рецептов определенной кухни (используется на главном экране)
     * @return Ответ с сервера, очевидно
     */
    Single<CuisineRecipes> getCuisineRecipes();
}
