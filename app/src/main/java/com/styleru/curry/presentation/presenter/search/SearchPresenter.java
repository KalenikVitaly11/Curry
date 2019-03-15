package com.styleru.curry.presentation.presenter.search;

import android.util.Log;

import com.styleru.curry.data.models.recipe.RecipeResponse;
import com.styleru.curry.domain.search.SearchInteractor;
import com.styleru.curry.presentation.view.search.SearchView;

import javax.inject.Inject;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class SearchPresenter {

    private SearchInteractor searchInteractor;
    private SearchView view;

    @Inject
    public SearchPresenter(SearchInteractor searchInteractor) {
        this.searchInteractor = searchInteractor;
    }

    public void attachView(SearchView view) {
        this.view = view;
    }

    public void searchRecipes(String query, String cuisine, String diet, String type) {
        searchInteractor.getRecipesComplex(query, cuisine, diet, "")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        recipeResponse -> {
                            view.setData(recipeResponse);
                        },
                        throwable -> {
                            view.showError();
                            Log.d("myLogs", throwable.getMessage());
                        }
                );
    }
}
