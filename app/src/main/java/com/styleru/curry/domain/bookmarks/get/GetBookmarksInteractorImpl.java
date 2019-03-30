package com.styleru.curry.domain.bookmarks.get;

import com.styleru.curry.data.models.recipe.Recipe;
import com.styleru.curry.domain.bookmarks.BookmarksRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;

public class GetBookmarksInteractorImpl implements GetBookmarksInteractor {

    private BookmarksRepository repository;

    @Inject
    public GetBookmarksInteractorImpl(BookmarksRepository repository) {
        this.repository = repository;
    }

    @Override
    public Single<List<Recipe>> getRecipes() {
        return repository.getRecipes();
    }
}
