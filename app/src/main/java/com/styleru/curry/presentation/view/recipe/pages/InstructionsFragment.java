package com.styleru.curry.presentation.view.recipe.pages;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.styleru.curry.R;
import com.styleru.curry.data.models.recipe.Recipe;

public class InstructionsFragment extends Fragment {

    private static final String INSTRUCTIONS_KEY = "instructions_key";
    private static final String SOURCE_KEY = "source_key";
    private static final String SERVINGS_KEY = "servings_key";

    private TextView instructions;

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
        instructions = view.findViewById(R.id.recipe_instructions);
    }

    private void init(){
        setTextFromArguments();
    }

    private void setTextFromArguments(){
        if(getArguments() != null){
            instructions.setText(getArguments().getString(INSTRUCTIONS_KEY));
        }
    }

    public static InstructionsFragment newInstance(Recipe recipe){
        InstructionsFragment instructionsFragment = new InstructionsFragment();
        Bundle bundle = new Bundle();
        bundle.putString(INSTRUCTIONS_KEY, recipe.getInstructions());
        bundle.putString(SOURCE_KEY, recipe.getSourceUrl());
        bundle.putInt(SERVINGS_KEY, recipe.getServings());

        instructionsFragment.setArguments(bundle);
        return instructionsFragment;
    }
}
