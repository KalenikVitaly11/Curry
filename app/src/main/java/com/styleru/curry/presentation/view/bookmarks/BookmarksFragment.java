package com.styleru.curry.presentation.view.bookmarks;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.styleru.curry.CurryApplication;
import com.styleru.curry.R;
import com.styleru.curry.data.models.recipe.Recipe;
import com.styleru.curry.presentation.presenter.bookmarks.BookmarksPresenter;
import com.styleru.curry.presentation.view.bookmarks.adapter.BookmarksItemDecorator;
import com.styleru.curry.presentation.view.bookmarks.adapter.BookmarksRecyclerViewAdapter;
import com.styleru.curry.presentation.view.bookmarks.adapter.RecyclerOnClick;

import org.w3c.dom.Text;

import java.util.List;

import javax.inject.Inject;

import androidx.navigation.Navigation;

public class BookmarksFragment extends Fragment implements BookmarksView, RecyclerOnClick {

    private RecyclerView recyclerView;
    private BookmarksRecyclerViewAdapter adapter;
    private TextView noFavorites;
    public static final String ID_KEY = "recipeId";

    @Inject
    protected BookmarksPresenter presenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_bookmarks, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews(view);
        init();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        CurryApplication.clearBookmarksComponent();
    }

    private void initViews(View view){
        recyclerView = view.findViewById(R.id.bookmarks_recycler);
        noFavorites = view.findViewById(R.id.no_favorites);
    }

    private void init(){
        CurryApplication.addBookmarksComponent().inject(this);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        recyclerView.addItemDecoration(new BookmarksItemDecorator(2, 20));

        presenter.attachView(this);
        presenter.getBookmarks();
    }

    @Override
    public void setData(List<Recipe> recipeList) {
        adapter = new BookmarksRecyclerViewAdapter(recipeList, this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void showOnEmptyList() {
        noFavorites.setVisibility(View.VISIBLE);
    }

    @Override
    public void recyclerOnClick(int id) {
        Bundle bundle = new Bundle();
        bundle.putInt(ID_KEY, id);
        Navigation.findNavController(getActivity(), R.id.nav_host_fragment).navigate(R.id.action_bookmarksFragment_to_recipeFragment, bundle);
    }
}
