package com.styleru.curry.presentation.presenter.cuisine;


import android.annotation.SuppressLint;

import com.styleru.curry.domain.cuisine.CuisineRecipesInteractor;
import com.styleru.curry.presentation.view.cuisine.CuisineFragment;
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

    public void attachView(CuisineView view) {
        this.view = view;
    }

    /**
     * Метод для получения рецептов определенной кухни
     *
     * @param listNumber Номер списка, в которой загрузим ответ. Нужен, чтобы определить метод, который надо вызвать у view
     */
    @SuppressLint("CheckResult")
    public void getCuisineRecipes(int listNumber) {
        cuisineRecipesInteractor.getCuisineRecipes()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        cuisineRecipes -> {
                            // Кэшируем
                            cuisineRecipesInteractor.cacheCuisineRecipes(cuisineRecipes);

                            // Меняем текст заголовка
                            cuisineRecipes.setCuisine(formatTitle(cuisineRecipes.getCuisine()));

                            // Передаем во вью
                            switch (listNumber) {
                                case CuisineFragment.RECYCLER_ONE:
                                    view.setDataOne(cuisineRecipes);
                                    break;

                                case CuisineFragment.RECYCLER_TWO:
                                    view.setDataTwo(cuisineRecipes);
                                    break;

                                case CuisineFragment.RECYCLER_THREE:
                                    view.setDataThree(cuisineRecipes);
                                    break;
                            }
                        }, throwable -> {
                            view.showError();
                        });
    }

    // Меняем первую букву на заглавную и добавляем слово "кухня"
    private String formatTitle(String title) {
        title = title.substring(0, 1).toUpperCase()  + title.substring(1);
        return title;
    }
}
