package com.styleru.curry.di.recipe;



import com.styleru.curry.presentation.view.recipe.RecipeFragment;

import dagger.Component;

@Component(modules = {RecipeModule.class})
public interface RecipeComponent {

    void inject(RecipeFragment recipeFragment);
}
