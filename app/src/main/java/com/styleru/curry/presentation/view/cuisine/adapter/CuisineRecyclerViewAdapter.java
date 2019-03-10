package com.styleru.curry.presentation.view.cuisine.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.styleru.curry.R;
import com.styleru.curry.data.models.recipe.ShortRecipe;

import java.util.ArrayList;
import java.util.List;

public class CuisineRecyclerViewAdapter extends RecyclerView.Adapter<CuisineRecyclerViewAdapter.CuisineViewHolder> {

    private CuisineRecyclerOnClick onClick;
    private List<ShortRecipe> data;

    public CuisineRecyclerViewAdapter(List<ShortRecipe> newData, CuisineRecyclerOnClick onClick) {
        data = new ArrayList<>();
        data.addAll(newData);
        this.onClick = onClick;
    }

    public void updateData(List<ShortRecipe> newData) {
        data.clear();
        data.addAll(newData);
        notifyDataSetChanged();
    }

    public CuisineRecyclerViewAdapter(CuisineRecyclerOnClick onClick) {
        data = new ArrayList<>();
        this.onClick = onClick;
    }

    @NonNull
    @Override
    public CuisineRecyclerViewAdapter.CuisineViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.search_recipe_cuisine_item, viewGroup, false);
        return new CuisineViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CuisineViewHolder cuisineViewHolder, int i) {
        cuisineViewHolder.textView.setText(data.get(i).getTitle());
        Picasso.get()
                .load("https://spoonacular.com/recipeImages/" + data.get(i).getImageUrl())
                .into(cuisineViewHolder.imageView);

        cuisineViewHolder.itemView.setOnClickListener(view -> {
            onClick.recyclerOnClick(data.get(i).getId());
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    protected class CuisineViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageView;
        private TextView textView;


        public CuisineViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.recipe_image);
            textView = itemView.findViewById(R.id.recipe_title);
        }
    }
}

