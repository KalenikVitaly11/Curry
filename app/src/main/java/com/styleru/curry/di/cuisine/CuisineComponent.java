package com.styleru.curry.di.cuisine;

import com.styleru.curry.presentation.view.cuisine.CuisineFragment;

import dagger.Component;

@Component(modules = {CuisineModule.class})
public interface CuisineComponent {

    void inject(CuisineFragment cuisineFragment);
}
