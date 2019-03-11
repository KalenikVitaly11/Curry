package com.styleru.curry.domain.recipe;

import com.styleru.curry.data.models.recipe.Recipe;

import javax.inject.Inject;

import io.reactivex.Single;

public class RecipeInteractorImpl implements RecipeInteractor {

    private RecipeRepository recipeRepository;

    @Inject
    public RecipeInteractorImpl(RecipeRepository recipeRepository){
        this.recipeRepository = recipeRepository;
    }

    @Override
    public Single<Recipe> getRecipeById(int id) {
        return recipeRepository.getRecipeById(id);
    }
}
