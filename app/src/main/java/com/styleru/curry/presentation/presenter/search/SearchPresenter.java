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

    private String dietFilter = "";
    private String cuisineFilter = "";

    private boolean ifFilterMode = true;

    @Inject
    public SearchPresenter(SearchInteractor searchInteractor) {
        this.searchInteractor = searchInteractor;
    }

    public void attachView(SearchView view) {
        this.view = view;
    }

    public void searchRecipes(String query, String type) {
        view.searchMode(dietFilter, cuisineFilter);
        ifFilterMode = false;

        searchInteractor.getRecipesComplex(query, cuisineFilter, dietFilter, type)
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

    public void setDietFilter(String dietFilter) {
        this.dietFilter = dietFilter;
    }

    public void setCuisineFilter(String cuisineFilter) {
        this.cuisineFilter = cuisineFilter;
    }

    /**
     * Обрабатываем нажатие кнопки "назад" с помощью коллбека
     * @return true если обработали
     *         false если не обработали
     */
    public boolean onBackPressed(){
        // Если в режиме поиска, то переходим в режим фильтров
        if (!ifFilterMode) {
            view.filterMode();
            ifFilterMode = true;
            return true;
        }
        return false;
    }
}
