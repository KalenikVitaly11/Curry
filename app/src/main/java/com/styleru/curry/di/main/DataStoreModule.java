package com.styleru.curry.di.main;

import com.styleru.curry.CurryApplication;
import com.styleru.curry.data.local.dataStore.DbDataStore;
import com.styleru.curry.data.local.dataStore.DbDataStoreImpl;
import com.styleru.curry.data.network.api.CurryWebHelper;
import com.styleru.curry.data.network.dataStore.WebDataStore;
import com.styleru.curry.data.network.dataStore.WebDataStoreImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DataStoreModule {

    @Provides
    @Singleton
    public WebDataStore provideWebDataStore(CurryWebHelper curryWebHelper){
        return new WebDataStoreImpl(curryWebHelper);
    }

    @Provides
    @Singleton
    public DbDataStore provideDbDatastore(){
        return new DbDataStoreImpl(CurryApplication.getRecipeDatabase());
    }

    @Provides
    @Singleton
    public CurryWebHelper provideWebHelper(){
        return new CurryWebHelper();
    }
}
