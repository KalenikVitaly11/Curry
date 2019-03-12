package com.styleru.curry.presentation.view.recipe.pages;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.styleru.curry.R;
import com.styleru.curry.data.models.Ingredient;
import com.styleru.curry.presentation.view.recipe.adapter.IngredientsRecyclerAdapter;

import java.util.ArrayList;

/**
 * Фрагмент, содержащий список ингридиентов в конкретному фрагменту
 */
public class IngredientsFragment extends Fragment {

    private static final String INGREDIENTS_KEY = "ingredients_key";

    private RecyclerView recyclerView;
    private IngredientsRecyclerAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ingredients, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews(view);
        init();
    }

    private void initViews(View view) {
        recyclerView = view.findViewById(R.id.ingredients_recycler);
    }

    private void init() {
        if (getArguments() != null) {
            adapter = new IngredientsRecyclerAdapter(getArguments().getParcelableArrayList(INGREDIENTS_KEY));
            recyclerView.setAdapter(adapter);
        }
    }


    public static IngredientsFragment newInstance(ArrayList<Ingredient> ingredientList) {
        IngredientsFragment ingredientsFragment = new IngredientsFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(INGREDIENTS_KEY, ingredientList);
        ingredientsFragment.setArguments(bundle);

        return ingredientsFragment;
    }
}
