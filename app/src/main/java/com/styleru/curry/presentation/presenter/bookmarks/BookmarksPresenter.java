package com.styleru.curry.presentation.presenter.bookmarks;

import com.styleru.curry.data.models.recipe.Recipe;
import com.styleru.curry.domain.bookmarks.get.GetBookmarksInteractor;
import com.styleru.curry.presentation.view.bookmarks.BookmarksView;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class BookmarksPresenter {

    private BookmarksView view;
    private GetBookmarksInteractor interactor;

    @Inject
    public BookmarksPresenter(GetBookmarksInteractor interactor) {
        this.interactor = interactor;
    }

    public void attachView(BookmarksView view) {
        this.view = view;
    }

    public void getBookmarks() {
        interactor.getRecipes()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(recipes -> {
                    if (recipes.isEmpty()) {
                        view.showOnEmptyList();
                    } else {
                        view.setData(recipes);
                    }
                }, throwable -> {
                        view.showError();
                });

    }
}
