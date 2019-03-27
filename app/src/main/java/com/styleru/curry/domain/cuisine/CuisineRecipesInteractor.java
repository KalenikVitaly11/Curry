package com.styleru.curry.domain.cuisine;

import com.styleru.curry.domain.cuisine.models.CuisineRecipes;

import io.reactivex.Single;

public interface CuisineRecipesInteractor {

    /**
     * Метод для получения рецептов определенной кухни (используется на главном экране)
     */
    Single<CuisineRecipes> getCuisineRecipes();

    /**
     * Метод для сохранения рецептов в сиглтон
     * @param cuisineRecipes Объект с рецептами
     */
    void cacheCuisineRecipes(CuisineRecipes cuisineRecipes);
}
