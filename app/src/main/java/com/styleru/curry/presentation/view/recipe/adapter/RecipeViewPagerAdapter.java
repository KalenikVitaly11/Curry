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
    private Recipe recipe;
    private String ingredientsTitle;
    private String instructionsTitle;

    public RecipeViewPagerAdapter(FragmentManager fm, Recipe recipe, String ingredientsTitle, String instructionsTitle) {
        super(fm);
        this.recipe = recipe;
        this.ingredientsTitle = ingredientsTitle;
        this.instructionsTitle = instructionsTitle;
    }

    @Override
    public Fragment getItem(int i) {
        switch (i) {
            case 0:
                return IngredientsFragment.newInstance(recipe.getIngredientsList());
            case 1:
                return InstructionsFragment.newInstance(recipe);
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
                return ingredientsTitle;
            case 1:
                return instructionsTitle;
        }
        return "";
    }
}
