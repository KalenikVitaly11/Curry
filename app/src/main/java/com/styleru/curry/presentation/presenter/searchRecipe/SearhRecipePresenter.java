package com.styleru.curry.presentation.presenter.searchRecipe;


import android.annotation.SuppressLint;

import com.styleru.curry.domain.cuisine.CuisineRecipesInteractor;
import com.styleru.curry.presentation.view.searchRecipe.SearchRecipeView;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class SearhRecipePresenter {

    private CuisineRecipesInteractor cuisineRecipesInteractor;
    private SearchRecipeView view;

    @Inject
    public SearhRecipePresenter(CuisineRecipesInteractor cuisineRecipesInteractor) {
        this.cuisineRecipesInteractor = cuisineRecipesInteractor;
    }

    public void attachView(SearchRecipeView view){
        this.view = view;
    }

    /**
     * Метод для получения рецептов определенной кухни (используется на главном экране)
     */
    @SuppressLint("CheckResult")
    public void getCuisineRecipes() {
        cuisineRecipesInteractor.getCuisineRecipes()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        cuisineRecipes -> {
                            // Меняем первую букву на заглавную и добавляем слово "кухня"
                            String newCuisine = cuisineRecipes.getCuisine().substring(0,1).toUpperCase() + cuisineRecipes.getCuisine().substring(1) + " cuisine";
                            cuisineRecipes.setCuisine(newCuisine);

                            view.setData(cuisineRecipes);
                        }, throwable -> {
                            view.showError();
                        });
    }
}
