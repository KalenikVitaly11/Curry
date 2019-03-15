package com.styleru.curry.di.search;

import com.styleru.curry.presentation.view.search.SearchFragment;

import dagger.Component;

@Component(modules = {SearchModule.class})
public interface SearchComponent {

    void inject(SearchFragment searchFragment);
}
