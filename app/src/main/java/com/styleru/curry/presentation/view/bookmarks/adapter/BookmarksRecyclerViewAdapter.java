package com.styleru.curry.presentation.view.bookmarks.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.styleru.curry.R;
import com.styleru.curry.data.models.recipe.Recipe;

import java.util.ArrayList;
import java.util.List;

public class BookmarksRecyclerViewAdapter  extends RecyclerView.Adapter<BookmarksRecyclerViewAdapter.BookmarksViewHolder>{

    private List<Recipe> data;
    private RecyclerOnClick onClick;

    private static final String IMAGE_BASE_URL = "https://spoonacular.com/recipeImages/";

    public BookmarksRecyclerViewAdapter(List<Recipe> newData, RecyclerOnClick onClick){
        this.data = new ArrayList<>();
        this.data.addAll(newData);
        this.onClick = onClick;
    }

    @NonNull
    @Override
    public BookmarksViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.bookmarks_item, viewGroup, false);
        return new BookmarksViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookmarksViewHolder bookmarksViewHolder, int i) {
        bookmarksViewHolder.title.setText(data.get(i).getTitle());
        bookmarksViewHolder.time.setText(bookmarksViewHolder.itemView.getContext().getResources().getString(R.string.time, data.get(i).getReadyInMinutes()));

        Picasso.get()
                .load(data.get(i).getImageUrl())
                .into(bookmarksViewHolder.imageView);

        bookmarksViewHolder.itemView.setOnClickListener(view -> {
            this.onClick.recyclerOnClick(data.get(i).getId());
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    protected class BookmarksViewHolder extends RecyclerView.ViewHolder{

        private TextView title;
        private TextView time;
        private ImageView imageView;

        public BookmarksViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.bookmarks_recipe_title);
            time = itemView.findViewById(R.id.bookmarks_recipe_time);
            imageView = itemView.findViewById(R.id.bookmarks_recipe_image);
        }
    }
}
