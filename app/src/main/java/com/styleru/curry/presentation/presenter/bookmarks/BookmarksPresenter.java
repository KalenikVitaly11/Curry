package com.styleru.curry.presentation.presenter.bookmarks;

import com.styleru.curry.data.models.recipe.Recipe;
import com.styleru.curry.domain.bookmarks.get.GetBookmarksInteractor;
import com.styleru.curry.presentation.view.bookmarks.BookmarksView;

import java.util.List;

import javax.inject.Inject;

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
        List<Recipe> list = interactor.getRecipes();

        if (list.isEmpty()) {
            view.showOnEmptyList();
        } else {
            view.setData(list);
        }
    }

}
