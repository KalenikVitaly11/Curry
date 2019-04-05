package com.styleru.curry.data.repositories.search;

import com.styleru.curry.data.models.recipe.RecipeResponse;
import com.styleru.curry.data.network.dataStore.WebDataStore;
import com.styleru.curry.domain.search.SearchRepository;

import javax.inject.Inject;

import io.reactivex.Single;

public class SearchRepositoryImpl implements SearchRepository {

    private WebDataStore webDataStore;

    @Inject
    public SearchRepositoryImpl(WebDataStore webDataStore){
        this.webDataStore = webDataStore;
    }

    @Override
    public Single<RecipeResponse> getRecipesComplex(String query, String cuisine, String diet, String type, int offset) {
        return webDataStore.getRecipesComplex(query, cuisine, diet, type, offset);
    }
}
