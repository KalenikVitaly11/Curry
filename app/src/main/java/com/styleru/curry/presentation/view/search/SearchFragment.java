package com.styleru.curry.presentation.view.search;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.chip.Chip;
import android.support.design.chip.ChipGroup;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.styleru.curry.CurryApplication;
import com.styleru.curry.R;
import com.styleru.curry.data.models.recipe.RecipeResponse;
import com.styleru.curry.presentation.presenter.search.SearchPresenter;
import com.styleru.curry.presentation.view.search.adapter.SearchRecyclerOnClick;
import com.styleru.curry.presentation.view.search.adapter.SearchRecyclerViewAdapter;

import javax.inject.Inject;

import androidx.navigation.Navigation;

import static com.styleru.curry.presentation.view.recipe.RecipeFragment.ID_KEY;

public class SearchFragment extends Fragment implements SearchView, SearchRecyclerOnClick {

    @Inject
    protected SearchPresenter presenter;
    private EditText searchEditText;

    private RecyclerView recyclerView;
    private SearchRecyclerViewAdapter adapter;

    private Chip dietChip;
    private Chip cuisineChip;
    private ChipGroup dietChipGroup;
    private ChipGroup cuisineChipGroup;
    private TextView dietTitle;
    private TextView cuisineTitle;

    private String dietFilter = "";
    private String cuisineFilter = "";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews(view);
        init();
    }

    @Override
    public void setData(RecipeResponse recipeResponse) {
        adapter.updateData(recipeResponse.getRecipeList());
    }

    @Override
    public void showError() {
        Toast.makeText(getContext(), getString(R.string.error), Toast.LENGTH_LONG).show();
    }

    @Override
    public void recyclerOnClick(int id) {
        Bundle bundle = new Bundle();
        bundle.putInt(ID_KEY, id);
        Navigation.findNavController(getActivity(), R.id.nav_host_fragment).navigate(R.id.action_searchFragment_to_recipeFragment, bundle);
    }

    private void initViews(View view) {
        recyclerView = view.findViewById(R.id.search_recycler);
        dietChipGroup = view.findViewById(R.id.diet_chip_group);
        cuisineChipGroup = view.findViewById(R.id.cuisine_chip_group);
        dietTitle = view.findViewById(R.id.search_diet_title);
        cuisineTitle = view.findViewById(R.id.search_cuisine_title);
        dietChip = view.findViewById(R.id.diet_chip);
        cuisineChip = view.findViewById(R.id.cuisine_chip);

        searchEditText = view.findViewById(R.id.search_edittext);
        searchEditText.setOnEditorActionListener((editText, actionId, event) -> {
            int dietId = dietChipGroup.getCheckedChipId();
            int cuisineId = cuisineChipGroup.getCheckedChipId();

            if (dietId != -1) {
                dietFilter = ((Chip) view.findViewById(dietId)).getText().toString();
                dietChip.setVisibility(View.VISIBLE);
                dietChipGroup.clearCheck();
            }

            if (cuisineId != -1) {
                cuisineFilter = ((Chip) view.findViewById(cuisineChipGroup.getCheckedChipId())).getText().toString();
                cuisineChip.setVisibility(View.VISIBLE);
                cuisineChipGroup.clearCheck();
            }

            search();
            return false;
        });

        dietChip.setOnCloseIconClickListener(chip -> {
            dietChip.setText("");
            dietFilter = "";
            dietChip.setVisibility(View.GONE);
            if (cuisineChip.getVisibility() == View.GONE) {
                showFilters();
            }
        });

        cuisineChip.setOnCloseIconClickListener(chip -> {
            cuisineChip.setText("");
            cuisineFilter = "";
            cuisineChip.setVisibility(View.GONE);
            if (dietChip.getVisibility() == View.GONE) {
                showFilters();
            }
        });
    }

    private void init() {
        CurryApplication.getSearchComponent().inject(this);
        initRecycler();
        setSearchFocus();
        presenter.attachView(this);
    }

    private void initRecycler() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        adapter = new SearchRecyclerViewAdapter(this::recyclerOnClick);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(adapter);
    }

    private void setSearchFocus() {
        searchEditText.requestFocus();
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(searchEditText, InputMethodManager.SHOW_IMPLICIT);
    }

    private void showFilters() {
        dietChipGroup.setVisibility(View.VISIBLE);
        cuisineChipGroup.setVisibility(View.VISIBLE);
        dietTitle.setVisibility(View.VISIBLE);
        cuisineTitle.setVisibility(View.VISIBLE);

        recyclerView.setVisibility(View.GONE);

        adapter.clear();
        searchEditText.setText("");
    }

    private void hideFilters() {
        dietChip.setText(dietFilter);
        cuisineChip.setText(cuisineFilter);
        dietChipGroup.setVisibility(View.GONE);
        cuisineChipGroup.setVisibility(View.GONE);
        dietTitle.setVisibility(View.GONE);
        cuisineTitle.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
    }

    private void search() {
        presenter.searchRecipes(searchEditText.getText().toString(), cuisineFilter, dietFilter, "");
        hideFilters();
    }
}
