package com.styleru.curry.presentation.presenter.recipe;

import android.annotation.SuppressLint;
import android.util.Log;

import com.styleru.curry.CurryApplication;
import com.styleru.curry.data.models.recipe.Recipe;
import com.styleru.curry.domain.bookmarks.add.AddOrRemoveBookmarkInteractor;
import com.styleru.curry.domain.recipe.RecipeInteractor;
import com.styleru.curry.presentation.view.recipe.RecipeView;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class RecipePresenter {

    private AddOrRemoveBookmarkInteractor addOrRemoveBookmarkInteractor;
    private RecipeInteractor recipeInteractor;
    private RecipeView view;

    private Recipe recipe;

    @Inject
    public RecipePresenter(RecipeInteractor recipeInteractor, AddOrRemoveBookmarkInteractor addOrRemoveBookmarkInteractor) {
        this.recipeInteractor = recipeInteractor;
        this.addOrRemoveBookmarkInteractor = addOrRemoveBookmarkInteractor;
    }

    public void attachView(RecipeView view) {
        this.view = view;
    }

    @SuppressLint("CheckResult")
    public void getRecipeById(int id) {
        recipeInteractor.getRecipeById(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        recipe -> {
                            // Если инфа из БД, то показываем закрашенную иконку в FAB
                            if(recipe.isfRomDb())
                                view.showFilledIcon();

                            view.setDataToViews(recipe);
                            view.initViewPager(recipe);
                            view.showViews();
                            // Если установить лисенер раньше, то по какой то причине BottomSheet открывается, так что вызов метода здесь
                            view.initListeners();

                            // Сохраняем сущность, чтобы потом можно было сохранить с БД
                            this.recipe = recipe;
                        }, throwable -> {
                            Log.d("myLogs", throwable.getMessage());
                            view.showError();
                        });
    }

    public void floatingButtonCLicked(){
        if(addOrRemoveBookmarkInteractor.saveRecipe(recipe)){
            view.showFilledIcon();
        } else {
            view.showEmptyIcon();
        }
    }
}
