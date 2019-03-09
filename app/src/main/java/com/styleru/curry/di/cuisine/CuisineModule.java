package com.styleru.curry.di.cuisine;

import com.styleru.curry.data.network.dataStore.WebDataStore;
import com.styleru.curry.data.network.dataStore.WebDataStoreImpl;
import com.styleru.curry.data.repositories.cuisine.CuisineRepositoryImpl;
import com.styleru.curry.domain.cuisine.CuisineRecipesInteractor;
import com.styleru.curry.domain.cuisine.CuisineRecipesInteractorImpl;
import com.styleru.curry.domain.cuisine.CuisineRecipesRepository;

import dagger.Module;
import dagger.Provides;

@Module
public class CuisineModule {

    @Provides
    public CuisineRecipesInteractor provideCuisineInteractor(CuisineRecipesRepository cuisineRecipesRepository){
        return new CuisineRecipesInteractorImpl(cuisineRecipesRepository);
    }

    @Provides
    public CuisineRecipesRepository provideCuisineRecipesRepository(WebDataStore webDataStore){
        return new CuisineRepositoryImpl(webDataStore);
    }

    @Provides
    public WebDataStore provideWebDataStore(){
        return new WebDataStoreImpl();
    }
}
