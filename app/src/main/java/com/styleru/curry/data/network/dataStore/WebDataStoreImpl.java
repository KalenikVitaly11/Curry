package com.styleru.curry.data.network.dataStore;

import com.styleru.curry.data.models.recipe.Recipe;
import com.styleru.curry.data.network.api.CurryWebHelper;
import com.styleru.curry.domain.cuisine.models.CuisineRecipes;

import io.reactivex.Single;

/**
 * Хранилище данных с сервера
 */
public class WebDataStoreImpl implements WebDataStore {

    /**
     * Метод для получения рецептов определенной кухни (используется на главном экране)
     * @param cuisine Кухня, рецепты которой необходимо получить
     */
    @Override
    public Single<CuisineRecipes> getCuisineRecipes(String cuisine) {
        return new CurryWebHelper()
                .getApi()
                .getRecipes(CurryWebHelper.API_KEY, cuisine)
                .map(recipeResponse -> new CuisineRecipes(recipeResponse.getRecipeList(), cuisine));
    }

    /**
     * Метод для получения подробной инфы о рецепте по id
     * @param id id рецепта
     */
    @Override
    public Single<Recipe> getRecipeById(int id) {
        return new CurryWebHelper()
                .getApi()
                .getRecipeById(CurryWebHelper.API_KEY, id);
    }
}
