package com.styleru.curry.presentation.presenter.bookmarks;

import com.styleru.curry.domain.bookmarks.get.GetBookmarksInteractor;
import com.styleru.curry.presentation.view.bookmarks.BookmarksView;

import javax.inject.Inject;

public class BookmarksPresenter {

    private BookmarksView view;
    private GetBookmarksInteractor interactor;

    @Inject
    public BookmarksPresenter(GetBookmarksInteractor interactor){
        this.interactor = interactor;
    }

    public void attachView(BookmarksView view){
        this.view = view;
    }

    public void getBookmarks(){
        view.setData(interactor.getRecipes());
    }

}
