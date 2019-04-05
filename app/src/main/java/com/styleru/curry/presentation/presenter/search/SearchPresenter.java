package com.styleru.curry.presentation.presenter.search;

import android.arch.paging.PositionalDataSource;
import android.support.annotation.NonNull;
import android.util.Log;

import com.styleru.curry.data.models.recipe.ShortRecipe;
import com.styleru.curry.domain.search.SearchInteractor;
import com.styleru.curry.presentation.view.search.SearchView;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class SearchPresenter {

    private SearchInteractor searchInteractor;
    private SearchView view;

    private String dietFilter = "";
    private String cuisineFilter = "";
    private String query = "";

    private boolean ifFilterMode = true;

    @Inject
    public SearchPresenter(SearchInteractor searchInteractor) {
        this.searchInteractor = searchInteractor;
    }

    public void attachView(SearchView view) {
        this.view = view;
    }

    public void setDietFilter(String dietFilter) {
        this.dietFilter = dietFilter;
    }

    public void setCuisineFilter(String cuisineFilter) {
        this.cuisineFilter = cuisineFilter;
    }

    public void setQuery(String query) {
        this.query = query;
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

    public class SearchDataSource extends PositionalDataSource<ShortRecipe> {

        @Override
        public void loadInitial(@NonNull LoadInitialParams params, @NonNull LoadInitialCallback<ShortRecipe> callback) {
            view.searchMode(dietFilter, cuisineFilter);
            ifFilterMode = false;

            searchInteractor.getRecipesComplex(query, cuisineFilter, dietFilter, "", params.requestedStartPosition)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                            recipeResponse -> {
                                SearchPresenter.this.query = query;
                                callback.onResult(recipeResponse.getRecipeList(), params.requestedStartPosition);
                                view.stopShimmerAnimation();
                            },
                            throwable -> {
                                view.showError();
                                Log.d("myLogs", throwable.getMessage());
                            }
                    );
        }

        @Override
        public void loadRange(@NonNull LoadRangeParams params, @NonNull LoadRangeCallback<ShortRecipe> callback) {
            searchInteractor.getRecipesComplex(query, cuisineFilter, dietFilter, "", params.startPosition)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                            recipeResponse -> {
                                callback.onResult(recipeResponse.getRecipeList());
                            },
                            throwable -> {
                                Log.d("myLogs", throwable.getMessage());
                            }
                    );
        }
    }

}
