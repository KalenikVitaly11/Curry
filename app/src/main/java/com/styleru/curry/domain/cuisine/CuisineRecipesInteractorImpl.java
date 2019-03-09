package com.styleru.curry.domain.cuisine;

import com.styleru.curry.domain.cuisine.models.CuisineRecipes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import javax.inject.Inject;

import io.reactivex.Single;

public class CuisineRecipesInteractorImpl implements CuisineRecipesInteractor {

    private CuisineRecipesRepository recipesRepository;

    // Список со всеми доступными кухнями
    private static final List<String> cuisines = new ArrayList<>(Arrays.asList("african", "chinese", "japanese", "korean", "vietnamese", "thai", "indian", "british",
            "irish", "french", "italian", "mexican", "spanish", "middle eastern", "jewish", "american", "cajun", "southern", "greek",
            "german", "nordic", "eastern european", "caribbean", "latin american"));

    @Inject
    public CuisineRecipesInteractorImpl(CuisineRecipesRepository recipesRepository) {
        this.recipesRepository = recipesRepository;
    }

    /**
     * Метод для получения рецептов определенной кухни (используется на главном экране)
     * @return Ответ с сервера, очевидно
     */
    @Override
    public Single<CuisineRecipes> getCuisineRecipes() {
        return recipesRepository.getCuisineRecipes(getCuisine());
    }

    // TODO Проверить, что они нормально удаляются
    /**
     * Метод для получения рандомной кухни из списка
     * @return Рандомная кухня
     */
    private String getCuisine() {
        Random random = new Random();
        String cuisine = cuisines.get(random.nextInt(cuisines.size()));
        cuisines.remove(cuisine);
        return cuisine;
    }
}
