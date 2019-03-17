package com.styleru.curry.presentation.view.recipe.pages;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.styleru.curry.R;
import com.styleru.curry.data.models.recipe.Recipe;
import com.styleru.curry.presentation.view.recipe.adapter.InstructionsItemDecoration;
import com.styleru.curry.presentation.view.recipe.adapter.InstructionsRecyclerAdapter;
import com.styleru.curry.presentation.view.recipe.models.Step;

import java.util.ArrayList;

/**
 * Фрагмент, содержащий инструкцию к конкретному рецепту
 */
public class InstructionsFragment extends Fragment {

    private static final String INSTRUCTIONS_KEY = "instructions_key";

    private RecyclerView instructionsRecycler;
    private InstructionsRecyclerAdapter adapter;
    private TextView recipeError;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_instructions, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews(view);
        init();
    }

    private void initViews(View view){
        instructionsRecycler = view.findViewById(R.id.instructions_recycler);
//        recipeError = view.findViewById(R.id.recipe_error);
    }

    private void init(){
        setTextFromArguments();
    }

    private void setTextFromArguments(){
        if(getArguments() != null){
            ArrayList instructions = getArguments().getParcelableArrayList(INSTRUCTIONS_KEY);

            if(instructions == null){
//                recipeError.setVisibility(View.VISIBLE);
            } else {
                adapter = new InstructionsRecyclerAdapter(instructions);
                instructionsRecycler.addItemDecoration(new InstructionsItemDecoration(40));
                instructionsRecycler.setAdapter(adapter);
            }

        }
    }

    public static InstructionsFragment newInstance(ArrayList<Step> steps){
        InstructionsFragment instructionsFragment = new InstructionsFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(INSTRUCTIONS_KEY, steps);

        instructionsFragment.setArguments(bundle);
        return instructionsFragment;
    }
}
