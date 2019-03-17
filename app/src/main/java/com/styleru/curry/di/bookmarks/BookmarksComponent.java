package com.styleru.curry.di.bookmarks;

import com.styleru.curry.di.main.FragmentScope;
import com.styleru.curry.presentation.view.bookmarks.BookmarksFragment;

import dagger.Subcomponent;

@Subcomponent(modules = {BookmarksModule.class})
@FragmentScope
public interface BookmarksComponent {

    void inject(BookmarksFragment fragment);
}
