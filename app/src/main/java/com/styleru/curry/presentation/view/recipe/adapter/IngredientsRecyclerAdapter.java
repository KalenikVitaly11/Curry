package com.styleru.curry.presentation.view.recipe.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.styleru.curry.R;
import com.styleru.curry.data.models.Ingredient;

import java.util.ArrayList;
import java.util.List;

public class IngredientsRecyclerAdapter  extends RecyclerView.Adapter<IngredientsRecyclerAdapter.IngredientsViewHolder>{

    private List<Ingredient> ingredients;

    public IngredientsRecyclerAdapter(ArrayList<Ingredient> ingredients){
        this.ingredients = new ArrayList<>();
        this.ingredients.addAll(ingredients);
    }

    @NonNull
    @Override
    public IngredientsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.ingredients_item, viewGroup, false);
        return new IngredientsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull IngredientsViewHolder ingredientsViewHolder, int i) {
        ingredientsViewHolder.name.setText(ingredients.get(i).getName());
        String unit = ingredients.get(i).getUnitShort().toLowerCase();

        // Если единицы измерения пуста, то устанавливаем значение "штуки"
        if(unit.equals("")){
            unit = "pcs";
        }
        ingredientsViewHolder.unit.setText(unit);

        // Если значение целое, то убираем дробную часть, так красивее
        float amount = ingredients.get(i).getAmount();
        if(amount % 1 == 0) {
            ingredientsViewHolder.amount.setText(String.valueOf(Math.round(amount)));
        } else {
            ingredientsViewHolder.amount.setText(String.valueOf(amount));
        }
    }

    @Override
    public int getItemCount() {
        return ingredients.size();
    }

    protected class IngredientsViewHolder extends RecyclerView.ViewHolder{

        private TextView name;
        private TextView unit;
        private TextView amount;

        public IngredientsViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.ingredient_name);
            unit = itemView.findViewById(R.id.ingredient_unit);
            amount = itemView.findViewById(R.id.ingredient_amount);
        }
    }
}
