package com.styleru.curry.domain.search;

import com.styleru.curry.data.models.recipe.RecipeResponse;

import javax.inject.Inject;

import io.reactivex.Single;

public class SearchInteractorImpl implements SearchInteractor {

    private SearchRepository searchRepository;

    @Inject
    public SearchInteractorImpl(SearchRepository searchRepository){
        this.searchRepository = searchRepository;
    }

    @Override
    public Single<RecipeResponse> getRecipesComplex(String query, String cuisine, String diet, String type, int offset) {
        return searchRepository.getRecipesComplex(query, cuisine, diet, type, offset);
    }
}
