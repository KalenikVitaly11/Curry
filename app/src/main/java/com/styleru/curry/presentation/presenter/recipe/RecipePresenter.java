package com.styleru.curry.presentation.presenter.recipe;

import android.annotation.SuppressLint;

import com.styleru.curry.domain.recipe.RecipeInteractor;
import com.styleru.curry.presentation.view.recipe.RecipeView;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class RecipePresenter {

    private RecipeInteractor interactor;
    private RecipeView view;

    @Inject
    public RecipePresenter(RecipeInteractor interactor) {
        this.interactor = interactor;
    }

    public void attachView(RecipeView view) {
        this.view = view;
    }

    @SuppressLint("CheckResult")
    public void getRecipeById(int id) {
        interactor.getRecipeById(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        recipe -> {
                            view.passData(recipe);
                        }, throwable -> {
                            view.showError();
                        });
    }
}
