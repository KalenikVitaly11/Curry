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

import com.facebook.shimmer.ShimmerFrameLayout;
import com.styleru.curry.CurryApplication;
import com.styleru.curry.R;
import com.styleru.curry.data.models.recipe.RecipeResponse;
import com.styleru.curry.presentation.presenter.search.SearchPresenter;
import com.styleru.curry.presentation.view.main.FragmentOnBackPressedListener;
import com.styleru.curry.presentation.view.main.MainActivity;
import com.styleru.curry.presentation.view.search.adapter.SearchItemDecorator;
import com.styleru.curry.presentation.view.search.adapter.SearchRecyclerOnClick;
import com.styleru.curry.presentation.view.search.adapter.SearchRecyclerViewAdapter;

import javax.inject.Inject;

import androidx.navigation.Navigation;

import static com.styleru.curry.presentation.view.recipe.RecipeFragment.ID_KEY;


/**
 * Фрагмент с поиском
 */
public class SearchFragment extends Fragment implements SearchView, SearchRecyclerOnClick, FragmentOnBackPressedListener {

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
    private ShimmerFrameLayout shimmerFrameLayout;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews(view);
        initListeners();
        init();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        CurryApplication.clearSearchComponent();
    }

    /**
     * Обрабатываем нажатие на элемент ресайклера
     * @param id id рецепта, на который нажали
     */
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
        shimmerFrameLayout = view.findViewById(R.id.search_shimmer);
    }

    private void initListeners() {
        // Лисенер нажатия кнопки поиска на клавиатуре
        searchEditText.setOnEditorActionListener((editText, actionId, event) -> {
            int dietId = dietChipGroup.getCheckedChipId();
            int cuisineId = cuisineChipGroup.getCheckedChipId();

            // Если есть выбранные чипсы, то ставим показываем чипсы в поиске и закидываем туда текст,
            // а чипсы-фильтры очищаем
            if (dietId != -1) {
                presenter.setDietFilter(((Chip) getView().findViewById(dietId)).getText().toString());
                dietChip.setVisibility(View.VISIBLE);
                dietChipGroup.clearCheck();
            }

            if (cuisineId != -1) {
                presenter.setCuisineFilter(((Chip) getView().findViewById(cuisineChipGroup.getCheckedChipId())).getText().toString());
                cuisineChip.setVisibility(View.VISIBLE);
                cuisineChipGroup.clearCheck();
            }

            presenter.searchRecipes(searchEditText.getText().toString(), "");
            searchEditText.clearFocus();
            return false;
        });

        // Лисенер нажатия крестика на чипсе
        dietChip.setOnCloseIconClickListener(chip -> {
            dietChip.setText("");
            presenter.setDietFilter("");
            dietChip.setVisibility(View.GONE);

            // Если обе чипсы закрыты, показываем фильтры
            if (cuisineChip.getVisibility() == View.GONE) {
                filterMode();
            }
        });

        // Лисенер нажатия крестика на чипсе
        cuisineChip.setOnCloseIconClickListener(chip -> {
            cuisineChip.setText("");
            presenter.setCuisineFilter("");
            cuisineChip.setVisibility(View.GONE);

            // Если обе чипсы закрыты, показываем фильтры
            if (dietChip.getVisibility() == View.GONE) {
                filterMode();
            }
        });
    }

    private void init() {
        CurryApplication.addSearchComponent().inject(this);

        initRecycler();
        setSearchFocus();
        presenter.attachView(this);
    }

    /**
     * Инициализируем ресайклер
     */
    private void initRecycler() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        adapter = new SearchRecyclerViewAdapter(this);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.addItemDecoration(new SearchItemDecorator(2, 20));
        recyclerView.setAdapter(adapter);
    }

    /**
     * Наводим фокус на поле ввода
     */
    private void setSearchFocus() {
        searchEditText.requestFocus();
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(searchEditText, InputMethodManager.SHOW_IMPLICIT);
    }

    @Override
    public void setData(RecipeResponse recipeResponse) {
        adapter.updateData(recipeResponse.getRecipeList());
        shimmerFrameLayout.setVisibility(View.GONE);
        shimmerFrameLayout.stopShimmerAnimation();
    }

    /**
     * Показываем ошибку
     */
    @Override
    public void showError() {
        Toast.makeText(getContext(), getString(R.string.error), Toast.LENGTH_LONG).show();
        shimmerFrameLayout.setVisibility(View.GONE);
        shimmerFrameLayout.stopShimmerAnimation();
    }

    /**
     * Показываем фильтры и очищаем ресайклер
     */
    @Override
    public void filterMode() {
        dietChipGroup.setVisibility(View.VISIBLE);
        cuisineChipGroup.setVisibility(View.VISIBLE);
        dietTitle.setVisibility(View.VISIBLE);
        cuisineTitle.setVisibility(View.VISIBLE);
        cuisineChip.setVisibility(View.GONE);
        dietChip.setVisibility(View.GONE);
        recyclerView.setVisibility(View.GONE);
        shimmerFrameLayout.setVisibility(View.GONE);
        shimmerFrameLayout.stopShimmerAnimation();

        adapter.clear();
        searchEditText.setText("");
    }

    /**
     * Прячем фильтры
     */
    @Override
    public void searchMode(String dietFilter, String cuisineFilter) {
        recyclerView.setVisibility(View.VISIBLE);
        dietChip.setText(dietFilter);
        cuisineChip.setText(cuisineFilter);
        dietChipGroup.setVisibility(View.GONE);
        cuisineChipGroup.setVisibility(View.GONE);
        dietTitle.setVisibility(View.GONE);
        cuisineTitle.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
        shimmerFrameLayout.setVisibility(View.VISIBLE);
        shimmerFrameLayout.startShimmerAnimation();
    }

    /**
     * Обрабатываем нажатие кнопки "назад" с помощью коллбека
     * @return true если обработали
     *         false если не обработали
     */
    @Override
    public boolean onBackPressed() {
        return presenter.onBackPressed();
    }
}
