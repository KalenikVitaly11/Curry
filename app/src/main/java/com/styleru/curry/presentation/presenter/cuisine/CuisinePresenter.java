package com.styleru.curry.presentation.presenter.cuisine;


import android.annotation.SuppressLint;

import com.styleru.curry.domain.cuisine.CuisineRecipesInteractor;
import com.styleru.curry.presentation.view.cuisine.CuisineView;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class CuisinePresenter {

    private CuisineRecipesInteractor cuisineRecipesInteractor;
    private CuisineView view;

    @Inject
    public CuisinePresenter(CuisineRecipesInteractor cuisineRecipesInteractor) {
        this.cuisineRecipesInteractor = cuisineRecipesInteractor;
    }

    public void attachView(CuisineView view){
        this.view = view;
    }

    /**
     * Метод для получения рецептов определенной кухни для первого списка (используется на главном экране)
     */
    @SuppressLint("CheckResult")
    public void getCuisineRecipesOne() {
        cuisineRecipesInteractor.getCuisineRecipes()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        cuisineRecipes -> {
                            // Меняем первую букву на заглавную и добавляем слово "кухня"
                            String newCuisine = cuisineRecipes.getCuisine().substring(0,1).toUpperCase() + cuisineRecipes.getCuisine().substring(1) + " cuisine";
                            cuisineRecipes.setCuisine(newCuisine);

                            view.setDataOne(cuisineRecipes);
                        }, throwable -> {
                            view.showError();
                        });
    }

    /**
     * Метод для получения рецептов определенной кухни для второго списка (используется на главном экране)
     */
    @SuppressLint("CheckResult")
    public void getCuisineRecipesTwo() {
        cuisineRecipesInteractor.getCuisineRecipes()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        cuisineRecipes -> {
                            // Меняем первую букву на заглавную и добавляем слово "кухня"
                            String newCuisine = cuisineRecipes.getCuisine().substring(0,1).toUpperCase() + cuisineRecipes.getCuisine().substring(1) + " cuisine";
                            cuisineRecipes.setCuisine(newCuisine);

                            view.setDataTwo(cuisineRecipes);
                        }, throwable -> {
                            view.showError();
                        });
    }

    /**
     * Метод для получения рецептов определенной кухни для третьего списка (используется на главном экране)
     */
    @SuppressLint("CheckResult")
    public void getCuisineRecipesThree() {
        cuisineRecipesInteractor.getCuisineRecipes()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        cuisineRecipes -> {
                            // Меняем первую букву на заглавную и добавляем слово "кухня"
                            String newCuisine = cuisineRecipes.getCuisine().substring(0,1).toUpperCase() + cuisineRecipes.getCuisine().substring(1) + " cuisine";
                            cuisineRecipes.setCuisine(newCuisine);

                            view.setDataThree(cuisineRecipes);
                        }, throwable -> {
                            view.showError();
                        });
    }
}
