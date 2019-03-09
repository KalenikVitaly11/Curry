package com.styleru.curry.di.cuisine;

import com.styleru.curry.presentation.view.searchRecipe.SearchRecipeFragment;

import dagger.Component;

@Component(modules = {CuisineModule.class})
public interface CuisineComponent {

    void inject(SearchRecipeFragment searchRecipeFragment);
}
