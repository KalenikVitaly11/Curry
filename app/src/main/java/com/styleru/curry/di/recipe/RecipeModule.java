package com.styleru.curry.di.recipe;

import com.styleru.curry.di.main.FragmentScope;
import com.styleru.curry.domain.bookmarks.BookmarksRepository;
import com.styleru.curry.domain.bookmarks.add.AddOrRemoveBookmarkInteractor;
import com.styleru.curry.domain.bookmarks.add.AddOrRemoveBookmarkInteractorImpl;
import com.styleru.curry.domain.recipe.RecipeInteractor;
import com.styleru.curry.domain.recipe.RecipeInteractorImpl;
import com.styleru.curry.domain.recipe.RecipeRepository;

import dagger.Module;
import dagger.Provides;

@Module
public class RecipeModule {

    @Provides
    @FragmentScope
    public RecipeInteractor provideRecipeInteractor(RecipeRepository recipeRepository){
        return new RecipeInteractorImpl(recipeRepository);
    }

    @Provides
    @FragmentScope
    public AddOrRemoveBookmarkInteractor provideAddBookmarkInteractor(BookmarksRepository bookmarksRepository){
        return new AddOrRemoveBookmarkInteractorImpl(bookmarksRepository);
    }
}
