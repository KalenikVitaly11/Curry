package com.styleru.curry.di.search;

import com.styleru.curry.di.main.FragmentScope;
import com.styleru.curry.domain.search.SearchInteractor;
import com.styleru.curry.domain.search.SearchInteractorImpl;
import com.styleru.curry.domain.search.SearchRepository;

import dagger.Module;
import dagger.Provides;

@Module
public class SearchModule {

    @Provides
    @FragmentScope
    public SearchInteractor provideSearchInteractor(SearchRepository searchRepository){
        return new SearchInteractorImpl(searchRepository);
    }
}
