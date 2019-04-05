package com.styleru.curry.domain.search;

import com.styleru.curry.data.models.recipe.RecipeResponse;

import io.reactivex.Single;

public interface SearchInteractor {

    /**
     * Метод для получения рецептов по заданным фильтрам
     * @param query Запрос пользователя
     * @param cuisine Кухня, рецепты которой необходимо получить
     * @param diet Диета (vegan или vegetarian)
     * @param type Тип блюда (main course, side dish, dessert, appetizer, salad, bread, breakfast, soup, beverage, sauce, or drink)
     * @return Ответ сервера
     */
    public Single<RecipeResponse> getRecipesComplex(String query, String cuisine, String diet, String type, int offset);
}
