package com.styleru.curry;

import android.app.Application;

import com.styleru.curry.di.cuisine.CuisineComponent;
import com.styleru.curry.di.cuisine.DaggerCuisineComponent;

public class CurryApplication extends Application {

    private static CuisineComponent cuisineComponent;

    public static CuisineComponent getCuisineComponent(){
        return cuisineComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        cuisineComponent = buildComponent();
    }

    protected CuisineComponent buildComponent(){
        return DaggerCuisineComponent
                .builder()
                .build();
    }

}
