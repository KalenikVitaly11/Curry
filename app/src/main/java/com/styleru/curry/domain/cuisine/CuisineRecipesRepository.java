package com.styleru.curry.domain.cuisine;

import com.styleru.curry.domain.cuisine.models.CuisineRecipes;
import io.reactivex.Single;

public interface CuisineRecipesRepository {

    /**
     * Метод для получения рецептов определенной кухни (используется на главном экране)
     * @return Ответ с сервера или кэш из БД в случае ошибки
     */
    Single<CuisineRecipes> getCuisineRecipes(String cuisine);
}
