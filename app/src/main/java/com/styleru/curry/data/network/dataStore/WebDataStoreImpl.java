package com.styleru.curry.data.network.dataStore;

import com.styleru.curry.data.models.recipe.Recipe;
import com.styleru.curry.data.models.recipe.RecipeResponse;
import com.styleru.curry.data.network.api.CurryWebHelper;
import com.styleru.curry.domain.cuisine.models.CuisineRecipes;

import javax.inject.Inject;

import io.reactivex.Single;

/**
 * Хранилище данных с сервера
 */
public class WebDataStoreImpl implements WebDataStore {

    private CurryWebHelper curryWebHelper;

    @Inject
    public WebDataStoreImpl(CurryWebHelper curryWebHelper){
        this.curryWebHelper = curryWebHelper;
    }

    /**
     * Метод для получения рецептов определенной кухни (используется на главном экране)
     * @param cuisine Кухня, рецепты которой необходимо получить
     */
    @Override
    public Single<CuisineRecipes> getCuisineRecipes(String cuisine) {
        return curryWebHelper
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
        return curryWebHelper
                .getApi()
                .getRecipeById(CurryWebHelper.API_KEY, id);
    }

    /**
     * Метод для получения рецептов по заданным фильтрам
     * @param query Запрос пользователя
     * @param cuisine Кухня, рецепты которой необходимо получить
     * @param diet Диета (vegan или vegetarian)
     * @param type Тип блюда (main course, side dish, dessert, appetizer, salad, bread, breakfast, soup, beverage, sauce, or drink)
     * @return Ответ сервера
     */
    @Override
    public Single<RecipeResponse> getRecipesComplex(String query, String cuisine, String diet, String type, int offset) {
        return curryWebHelper
                .getApi()
                .getRecipesComplex(CurryWebHelper.API_KEY, query, cuisine, diet, type, offset);
    }
}
