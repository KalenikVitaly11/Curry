package com.styleru.curry.di.recipe;



import com.styleru.curry.di.main.FragmentScope;
import com.styleru.curry.presentation.view.recipe.RecipeFragment;

import dagger.Subcomponent;

@Subcomponent(modules = {RecipeModule.class})
@FragmentScope
public interface RecipeComponent {

    void inject(RecipeFragment recipeFragment);
}
