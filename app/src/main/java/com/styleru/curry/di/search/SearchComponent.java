package com.styleru.curry.di.search;

import com.styleru.curry.di.main.FragmentScope;
import com.styleru.curry.presentation.view.search.SearchFragment;

import dagger.Subcomponent;

@Subcomponent(modules = {SearchModule.class})
@FragmentScope
public interface SearchComponent {

    void inject(SearchFragment searchFragment);
}
