package com.styleru.curry.di.recipe;

import com.styleru.curry.data.network.dataStore.WebDataStore;
import com.styleru.curry.data.network.dataStore.WebDataStoreImpl;
import com.styleru.curry.data.repositories.recipe.RecipeRepositoryImpl;
import com.styleru.curry.domain.recipe.RecipeInteractor;
import com.styleru.curry.domain.recipe.RecipeInteractorImpl;
import com.styleru.curry.domain.recipe.RecipeRepository;

import dagger.Module;
import dagger.Provides;

@Module
public class RecipeModule {

    @Provides
    public RecipeInteractor provideRecipeInteractor(RecipeRepository recipeRepository){
        return new RecipeInteractorImpl(recipeRepository);
    }

    @Provides
    public RecipeRepository provideRecipeRepository(WebDataStore webDataStore){
        return new RecipeRepositoryImpl(webDataStore);
    }

    @Provides
    public WebDataStore provideWebDataStore(){
        return new WebDataStoreImpl();
    }

}
