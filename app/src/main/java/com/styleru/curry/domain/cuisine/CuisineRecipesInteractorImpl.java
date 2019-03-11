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
    private static final List<String> cuisines = new ArrayList<>(Arrays.asList("african", "chinese", "japanese", "korean", "indian", "british",
            "french", "italian", "mexican", "spanish", "jewish", "american", "greek", "german"));

    @Inject
    public CuisineRecipesInteractorImpl(CuisineRecipesRepository recipesRepository) {
        this.recipesRepository = recipesRepository;
    }

    /**
     * Метод для получения рецептов определенной кухни (используется на главном экране)
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
