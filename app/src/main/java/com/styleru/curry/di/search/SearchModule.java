package com.styleru.curry.di.search;

import com.styleru.curry.data.network.api.CurryWebHelper;
import com.styleru.curry.data.network.dataStore.WebDataStore;
import com.styleru.curry.data.network.dataStore.WebDataStoreImpl;
import com.styleru.curry.data.repositories.recipe.RecipeRepositoryImpl;
import com.styleru.curry.data.repositories.search.SearchRepositoryImpl;
import com.styleru.curry.domain.recipe.RecipeInteractor;
import com.styleru.curry.domain.recipe.RecipeInteractorImpl;
import com.styleru.curry.domain.recipe.RecipeRepository;
import com.styleru.curry.domain.search.SearchInteractor;
import com.styleru.curry.domain.search.SearchInteractorImpl;
import com.styleru.curry.domain.search.SearchRepository;

import dagger.Module;
import dagger.Provides;

@Module
public class SearchModule {

    @Provides
    public SearchInteractor provideSearchInteractor(SearchRepository searchRepository){
        return new SearchInteractorImpl(searchRepository);
    }

    @Provides
    public SearchRepository provideSearchRepository(WebDataStore webDataStore){
        return new SearchRepositoryImpl(webDataStore);
    }

    @Provides
    public WebDataStore provideWebDataStore(CurryWebHelper curryWebHelper){
        return new WebDataStoreImpl(curryWebHelper);
    }

    @Provides
    public CurryWebHelper provideCurryWebHelper(){
        return new CurryWebHelper();
    }
}
