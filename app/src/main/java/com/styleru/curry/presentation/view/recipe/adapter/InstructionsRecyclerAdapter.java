package com.styleru.curry.presentation.view.recipe.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.styleru.curry.R;
import com.styleru.curry.presentation.view.recipe.models.Step;

import java.util.ArrayList;
import java.util.List;

public class InstructionsRecyclerAdapter extends RecyclerView.Adapter<InstructionsRecyclerAdapter.InstructionsViewHolder> {

    private List<Step> instructionsList;

    public InstructionsRecyclerAdapter(ArrayList<Step> instructionsList){
        this.instructionsList = new ArrayList<>();
        this.instructionsList.addAll(instructionsList);
    }

    @NonNull
    @Override
    public InstructionsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.instructions_item, viewGroup, false);
        return new InstructionsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InstructionsViewHolder instructionsViewHolder, int i) {
        instructionsViewHolder.instruction.setText(instructionsList.get(i).getStep());
        instructionsViewHolder.number.setText(String.valueOf(instructionsList.get(i).getNumber()));
    }

    @Override
    public int getItemCount() {
        return instructionsList.size();
    }

    protected class InstructionsViewHolder extends RecyclerView.ViewHolder{

        private TextView instruction;
        private TextView number;

        public InstructionsViewHolder(@NonNull View itemView) {
            super(itemView);

            instruction = itemView.findViewById(R.id.step_instruction);
            number = itemView.findViewById(R.id.step_number);
        }
    }
}
