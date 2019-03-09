package com.styleru.curry.data.repositories.cuisine;

import com.styleru.curry.data.network.dataStore.WebDataStore;
import com.styleru.curry.domain.cuisine.CuisineRecipesRepository;
import com.styleru.curry.domain.cuisine.models.CuisineRecipes;

import javax.inject.Inject;

import io.reactivex.Single;

/**
 * Interactor для получения рецептов определенной кухни (используется на главном экране)
 */
public class CuisineRepositoryImpl implements CuisineRecipesRepository {

    private WebDataStore webDataStore;

    @Inject
    public CuisineRepositoryImpl(WebDataStore webDataStore){
        this.webDataStore = webDataStore;
    }

    /**
     * Метод для получения рецептов определенной кухни (используется на главном экране)
     * @param cuisine Кухня, рецепты которой необходимо получить
     * @return Ответ с сервера, очевидно
     */
    @Override
    public Single<CuisineRecipes> getCuisineRecipes(String cuisine) {
        return webDataStore.getCuisineRecipes(cuisine);
    }
}
