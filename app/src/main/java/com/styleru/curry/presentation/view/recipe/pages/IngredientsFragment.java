package com.styleru.curry.presentation.view.recipe.pages;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.styleru.curry.R;
import com.styleru.curry.data.models.Ingredient;

import java.util.ArrayList;

public class IngredientsFragment extends Fragment {

    private static final String INGREDIENTS_KEY = "ingredients_key";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ingredients, container, false);
    }


    public static IngredientsFragment newInstance(ArrayList<Ingredient> ingredientList){
        IngredientsFragment ingredientsFragment = new IngredientsFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(INGREDIENTS_KEY, ingredientList);
        ingredientsFragment.setArguments(bundle);

        return ingredientsFragment;
    }
}
