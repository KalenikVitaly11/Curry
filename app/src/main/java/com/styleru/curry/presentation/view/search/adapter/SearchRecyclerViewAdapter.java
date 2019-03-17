package com.styleru.curry.presentation.view.search.adapter;

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

public class SearchRecyclerViewAdapter extends RecyclerView.Adapter<SearchRecyclerViewAdapter.SearchViewHolder>{

    private SearchRecyclerOnClick onClick;
    private List<ShortRecipe> data;
    private static final String IMAGE_BASE_URL = "https://spoonacular.com/recipeImages/";

    public SearchRecyclerViewAdapter(SearchRecyclerOnClick onClick){
        data = new ArrayList<>();
        this.onClick = onClick;
    }

    public void updateData(List<ShortRecipe> newData) {
        data.clear();
        data.addAll(newData);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public SearchViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.search_item, viewGroup, false);
        return new SearchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchViewHolder searchViewHolder, int i) {
        searchViewHolder.title.setText(data.get(i).getTitle());
        searchViewHolder.time.setText(searchViewHolder.itemView.getContext().getResources().getString(R.string.time, data.get(i).getReadyInMinutes()));

        Picasso.get()
                .load(IMAGE_BASE_URL + data.get(i).getImageUrl())
                .into(searchViewHolder.imageView);

        searchViewHolder.itemView.setOnClickListener(view -> {
            this.onClick.recyclerOnClick(data.get(i).getId());
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void clear(){
        data.clear();
        notifyDataSetChanged();
    }

    protected class SearchViewHolder extends RecyclerView.ViewHolder{

        private TextView title;
        private TextView time;
        private ImageView imageView;

        public SearchViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.search_recipe_title);
            time = itemView.findViewById(R.id.search_recipe_time);
            imageView = itemView.findViewById(R.id.search_recipe_image);
        }
    }
}
