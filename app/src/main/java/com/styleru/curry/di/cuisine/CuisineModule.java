package com.styleru.curry.di.cuisine;

import com.styleru.curry.di.main.FragmentScope;
import com.styleru.curry.domain.cuisine.CuisineRecipesInteractor;
import com.styleru.curry.domain.cuisine.CuisineRecipesInteractorImpl;
import com.styleru.curry.domain.cuisine.CuisineRecipesRepository;

import dagger.Module;
import dagger.Provides;

@Module
public class CuisineModule {

    @Provides
    @FragmentScope
    public CuisineRecipesInteractor provideCuisineInteractor(CuisineRecipesRepository cuisineRecipesRepository){
        return new CuisineRecipesInteractorImpl(cuisineRecipesRepository);
    }
}
