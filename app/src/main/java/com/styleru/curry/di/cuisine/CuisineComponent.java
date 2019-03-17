package com.styleru.curry.di.cuisine;

import com.styleru.curry.di.main.FragmentScope;
import com.styleru.curry.presentation.view.cuisine.CuisineFragment;

import dagger.Subcomponent;

@Subcomponent(modules = {CuisineModule.class})
@FragmentScope
public interface CuisineComponent {

    void inject(CuisineFragment cuisineFragment);
}
