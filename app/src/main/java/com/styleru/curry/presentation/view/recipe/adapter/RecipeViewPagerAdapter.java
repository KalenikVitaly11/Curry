package com.styleru.curry.presentation.view.recipe.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.styleru.curry.data.models.recipe.Recipe;
import com.styleru.curry.presentation.view.recipe.pages.IngredientsFragment;
import com.styleru.curry.presentation.view.recipe.pages.InstructionsFragment;

public class RecipeViewPagerAdapter extends FragmentPagerAdapter {

    private static final int PAGE_COUNT = 2;
    private String ingredientsTitle;
    private String instructionsTitle;

    private Fragment firstFragment;
    private Fragment secondFragment;

    public RecipeViewPagerAdapter(FragmentManager fm, Fragment firstFragment, Fragment secondFragment, String ingredientsTitle, String instructionsTitle) {
        super(fm);
        this.ingredientsTitle = ingredientsTitle;
        this.instructionsTitle = instructionsTitle;

        this.firstFragment = firstFragment;
        this.secondFragment = secondFragment;
    }

    @Override
    public Fragment getItem(int i) {
        switch (i) {
            case 0:
                return secondFragment;
            case 1:
                return firstFragment;
        }
        return null;
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return instructionsTitle;
            case 1:
                return ingredientsTitle;
        }
        return "";
    }
}
