package com.styleru.curry.di.bookmarks;

import com.styleru.curry.di.main.FragmentScope;
import com.styleru.curry.domain.bookmarks.BookmarksRepository;
import com.styleru.curry.domain.bookmarks.get.GetBookmarksInteractor;
import com.styleru.curry.domain.bookmarks.get.GetBookmarksInteractorImpl;

import dagger.Module;
import dagger.Provides;

@Module
public class BookmarksModule {

    @Provides
    @FragmentScope
    public GetBookmarksInteractor provideAddBookmarkInteractor(BookmarksRepository bookmarksRepository){
        return new GetBookmarksInteractorImpl(bookmarksRepository);
    }

//    @Provides
//    public BookmarksRepository provideBookmarksRepository(DbDataStore dbDataStore){
//        return new BookmarksRepositoryImpl(dbDataStore);
//    }

//    @Provides
//    public DbDataStore provideDbDataStore(){
//        return new DbDataStoreImpl();
//    }
}
