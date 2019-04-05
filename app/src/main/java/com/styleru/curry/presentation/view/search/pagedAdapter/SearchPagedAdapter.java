package com.styleru.curry.presentation.view.search.pagedAdapter;

import android.arch.paging.PagedListAdapter;
import android.support.annotation.NonNull;
import android.support.v7.recyclerview.extensions.AsyncDifferConfig;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.styleru.curry.R;
import com.styleru.curry.data.models.recipe.ShortRecipe;
import com.styleru.curry.presentation.view.search.adapter.SearchRecyclerOnClick;

public class SearchPagedAdapter extends PagedListAdapter<ShortRecipe, SearchPagedAdapter.SearchViewHolder> {


    private static final String IMAGE_BASE_URL = "https://spoonacular.com/recipeImages/";
    private SearchRecyclerOnClick onClick;

    public SearchPagedAdapter(@NonNull DiffUtil.ItemCallback<ShortRecipe> diffCallback, SearchRecyclerOnClick onClick) {
        super(diffCallback);
        this.onClick = onClick;
    }

    @NonNull
    @Override
    public SearchViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.search_item, viewGroup, false);
        return new SearchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchViewHolder searchViewHolder, int i) {
        searchViewHolder.title.setText(getItem(i).getTitle());
        searchViewHolder.time.setText(searchViewHolder.itemView.getContext().getResources().getString(R.string.time, getItem(i).getReadyInMinutes()));

        Picasso.get()
                .load(IMAGE_BASE_URL + getItem(i).getImageUrl())
                .into(searchViewHolder.imageView);

        searchViewHolder.itemView.setOnClickListener(view -> {
            this.onClick.recyclerOnClick(getItem(i).getId());
        });
    }

    public void clear(){
//        getCurrentList().clear();
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
