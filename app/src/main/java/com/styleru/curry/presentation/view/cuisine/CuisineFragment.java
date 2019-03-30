package com.styleru.curry.presentation.view.cuisine;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.styleru.curry.CurryApplication;
import com.styleru.curry.R;
import com.styleru.curry.domain.cuisine.models.CuisineRecipes;
import com.styleru.curry.presentation.presenter.cuisine.CuisinePresenter;
import com.styleru.curry.presentation.view.cuisine.adapter.CuisineItemDecoration;
import com.styleru.curry.presentation.view.cuisine.adapter.CuisineRecyclerOnClick;
import com.styleru.curry.presentation.view.cuisine.adapter.CuisineRecyclerViewAdapter;

import javax.inject.Inject;

import androidx.navigation.Navigation;

import static com.styleru.curry.presentation.view.recipe.RecipeFragment.ID_KEY;

/*
 * Фрагмент, на котором отображаются списки с рецептами определенных кухонь мира (первая вкладка нижнего меню)
 */
public class CuisineFragment extends Fragment implements CuisineRecyclerOnClick, CuisineView {

    public final static int RECYCLER_ONE = 1;
    public final static int RECYCLER_TWO = 2;
    public final static int RECYCLER_THREE = 3;

    private RecyclerView cuisineRecyclerViewOne;
    private RecyclerView cuisineRecyclerViewTwo;
    private RecyclerView cuisineRecyclerViewThree;

    private CuisineRecyclerViewAdapter adapterOne;
    private CuisineRecyclerViewAdapter adapterTwo;
    private CuisineRecyclerViewAdapter adapterThree;

    private TextView cuisineTitleOne;
    private TextView cuisineTitleTwo;
    private TextView cuisineTitleThree;
    private TextView checkInternet;

    private ShimmerFrameLayout shimmerFrameLayout1;
    private ShimmerFrameLayout shimmerFrameLayout2;
    private ShimmerFrameLayout shimmerFrameLayout3;

    @Inject
    protected CuisinePresenter presenter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_cuisine, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews(view);
        init();
        initRecyclers();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        CurryApplication.clearCuisineComponent();
    }

    private void initViews(View view) {
        cuisineRecyclerViewOne = view.findViewById(R.id.cuisine_recipes_one);
        cuisineRecyclerViewTwo = view.findViewById(R.id.cuisine_recipes_two);
        cuisineRecyclerViewThree = view.findViewById(R.id.cuisine_recipes_three);
        cuisineTitleOne = view.findViewById(R.id.search_recipe_first_title);
        cuisineTitleTwo = view.findViewById(R.id.search_recipe_second_title);
        cuisineTitleThree = view.findViewById(R.id.search_recipe_third_title);
        checkInternet = view.findViewById(R.id.cuisine_check_internet);
        shimmerFrameLayout1 = view.findViewById(R.id.cuisine_shimmer_1);
        shimmerFrameLayout2 = view.findViewById(R.id.cuisine_shimmer_2);
        shimmerFrameLayout3 = view.findViewById(R.id.cuisine_shimmer_3);

        shimmerFrameLayout1.startShimmerAnimation();
        shimmerFrameLayout2.startShimmerAnimation();
        shimmerFrameLayout3.startShimmerAnimation();
    }

    private void init() {
        CurryApplication.addCuisineComponent().inject(this);

        presenter.attachView(this);
        presenter.getCuisineRecipes(RECYCLER_ONE);
        presenter.getCuisineRecipes(RECYCLER_TWO);
        presenter.getCuisineRecipes(RECYCLER_THREE);
    }

    private void initRecyclers() {
        CuisineItemDecoration itemDecoration = new CuisineItemDecoration(20);
        adapterOne = new CuisineRecyclerViewAdapter(this);
        cuisineRecyclerViewOne.addItemDecoration(itemDecoration);
        cuisineRecyclerViewOne.setAdapter(adapterOne);

        adapterTwo = new CuisineRecyclerViewAdapter(this);
        cuisineRecyclerViewTwo.addItemDecoration(itemDecoration);
        cuisineRecyclerViewTwo.setAdapter(adapterTwo);

        adapterThree = new CuisineRecyclerViewAdapter(this);
        cuisineRecyclerViewThree.addItemDecoration(itemDecoration);
        cuisineRecyclerViewThree.setAdapter(adapterThree);
    }

    /**
     * OnClick метод для адаптера - нажимаем на элемент и идем во фрагмент о рецепте
     *
     * @param id id нажатого элемента, который передается во фрагмент, чтобы там отобразить подробную инфу о рецепте
     */
    @Override
    public void recyclerOnClick(int id) {
        Bundle bundle = new Bundle();
        bundle.putInt(ID_KEY, id);
        Navigation.findNavController(getActivity(), R.id.nav_host_fragment).navigate(R.id.action_cuisineFragment_to_recipeFragment, bundle);
    }

    /**
     * Обновляем данные в первом адаптере
     *
     * @param cuisineRecipes объект с рецептами, получаемый с сервера
     */
    @Override
    public void setDataOne(CuisineRecipes cuisineRecipes) {
        cuisineTitleOne.setVisibility(View.VISIBLE);
        cuisineTitleOne.setText(getContext().getString(R.string.cuisine, cuisineRecipes.getCuisine()));
        shimmerFrameLayout1.stopShimmerAnimation();
        shimmerFrameLayout1.setVisibility(View.INVISIBLE);

        adapterOne.updateData(cuisineRecipes.getShortRecipes());
    }

    /**
     * Обновляем данные во втором адаптере
     *
     * @param cuisineRecipes объект с рецептами, получаемый с сервера
     */
    @Override
    public void setDataTwo(CuisineRecipes cuisineRecipes) {
        cuisineTitleTwo.setVisibility(View.VISIBLE);;
        cuisineTitleTwo.setText(getContext().getString(R.string.cuisine, cuisineRecipes.getCuisine()));
        shimmerFrameLayout2.stopShimmerAnimation();
        shimmerFrameLayout2.setVisibility(View.INVISIBLE);

        adapterTwo.updateData(cuisineRecipes.getShortRecipes());
    }

    /**
     * Обновляем данные в третьем адаптере
     *
     * @param cuisineRecipes объект с рецептами, получаемый с сервера
     */
    @Override
    public void setDataThree(CuisineRecipes cuisineRecipes) {
        cuisineTitleThree.setVisibility(View.VISIBLE);;
        cuisineTitleThree.setText(getContext().getString(R.string.cuisine, cuisineRecipes.getCuisine()));
        shimmerFrameLayout3.stopShimmerAnimation();
        shimmerFrameLayout3.setVisibility(View.INVISIBLE);

        adapterThree.updateData(cuisineRecipes.getShortRecipes());
    }

    /**
     * Уведомить пользователя об ошибке
     */
    @Override
    public void showError() {
        Toast.makeText(getContext(), getResources().getText(R.string.error), Toast.LENGTH_SHORT).show();
        checkInternet.setVisibility(View.VISIBLE);

        shimmerFrameLayout1.stopShimmerAnimation();
        shimmerFrameLayout2.stopShimmerAnimation();
        shimmerFrameLayout3.stopShimmerAnimation();

        shimmerFrameLayout1.setVisibility(View.INVISIBLE);
        shimmerFrameLayout2.setVisibility(View.INVISIBLE);
        shimmerFrameLayout3.setVisibility(View.INVISIBLE);
    }
}