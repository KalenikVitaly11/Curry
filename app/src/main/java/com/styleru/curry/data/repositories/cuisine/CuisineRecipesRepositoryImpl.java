package com.styleru.curry.data.repositories.cuisine;

import com.styleru.curry.data.local.CachedCuisineRecipes;
import com.styleru.curry.data.network.dataStore.WebDataStore;
import com.styleru.curry.domain.cuisine.CuisineRecipesRepository;
import com.styleru.curry.domain.cuisine.models.CuisineRecipes;

import javax.inject.Inject;

import io.reactivex.Single;

/**
 * Interactor для получения рецептов определенной кухни (используется на главном экране)
 */
public class CuisineRecipesRepositoryImpl implements CuisineRecipesRepository {

    private WebDataStore webDataStore;
    private CachedCuisineRecipes cachedCuisineRecipes;

    @Inject
    public CuisineRecipesRepositoryImpl(WebDataStore webDataStore, CachedCuisineRecipes cachedCuisineRecipes){
        this.webDataStore = webDataStore;
        this.cachedCuisineRecipes = cachedCuisineRecipes;
    }

    /**
     * Метод для получения рецептов определенной кухни (используется на главном экране)
     * @param cuisine Кухня, рецепты которой необходимо получить
     * @return Ответ с сервера, очевидно
     */
    @Override
    public Single<CuisineRecipes> getCuisineRecipes(String cuisine) {
        CuisineRecipes cachedRecipes = CachedCuisineRecipes.getInstance().getRecipes();
        if(cachedRecipes != null)
            return Single.just(cachedRecipes);

        return webDataStore.getCuisineRecipes(cuisine);
    }

    @Override
    public void cacheCuisineRecipes(CuisineRecipes cuisineRecipes){
        cachedCuisineRecipes.addRecipes(cuisineRecipes);
    }
}
