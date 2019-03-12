package com.styleru.curry.presentation.view.recipe;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.styleru.curry.CurryApplication;
import com.styleru.curry.R;
import com.styleru.curry.data.models.recipe.Recipe;
import com.styleru.curry.presentation.presenter.recipe.RecipePresenter;
import com.styleru.curry.presentation.view.cuisine.CuisineFragment;
import com.styleru.curry.presentation.view.recipe.adapter.RecipeViewPagerAdapter;
import com.styleru.curry.presentation.view.recipe.pages.IngredientsFragment;
import com.styleru.curry.presentation.view.recipe.pages.InstructionsFragment;

import javax.inject.Inject;

/**
 * Фрагмент, содержащий информацию о конкретном рецепте
 */
public class RecipeFragment extends Fragment implements RecipeView {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private RecipeViewPagerAdapter viewPagerAdapter;


    private TextView recipeTitle;
    private ImageView recipeImage;
    private TextView recipeServings;
    private TextView recipeTime;
    private ProgressBar progressBar;
    private TextView checkInternet;

    private BottomSheetBehavior bottomSheetBehavior;
    private ConstraintLayout bottomSheet;

    @Inject
    protected RecipePresenter presenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_recipe, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews(view);
        init();
    }

    /**
     * Получаем инфу с сервера
     * @param recipe Ответ от сервера
     */
    @Override
    public void passData(Recipe recipe) {
        setDataToViews(recipe);
        initViewPager(recipe);
    }

    /**
     *  Показываем пользователю сообщение об ошибке
     */
    @Override
    public void showError() {
        Toast.makeText(getContext(), R.string.error, Toast.LENGTH_LONG).show();
        progressBar.setVisibility(View.GONE);
        checkInternet.setVisibility(View.VISIBLE);
    }

    private void initViews(View view) {
        viewPager = view.findViewById(R.id.recipe_pager);
        tabLayout = view.findViewById(R.id.recipe_tab_layout);
        bottomSheet = view.findViewById(R.id.bottom_sheet);
        recipeTitle = view.findViewById(R.id.recipe_title);
        recipeImage = view.findViewById(R.id.recipe_image);
        recipeServings = view.findViewById(R.id.recipe_servings);
        recipeTime = view.findViewById(R.id.recipe_time);
        progressBar = view.findViewById(R.id.recipe_progress_bar);
        checkInternet = view.findViewById(R.id.recipe_check_internet);

        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet);
    }

    private void init() {
        CurryApplication.getRecipeComponent()
                .inject(this);

        presenter.attachView(this);
        if (getArguments() != null) {
            presenter.getRecipeById(getArguments().getInt(CuisineFragment.ID_KEY));
        }

        tabLayout.setupWithViewPager(viewPager);
    }

    /**
     * Закидываем полученную с сервера инфу во вью
     * @param recipe Ответ от сервера
     */
    private void setDataToViews(Recipe recipe) {
        showViews();
        Picasso.get().load(recipe.getImageUrl()).into(recipeImage);
        recipeTitle.setText(recipe.getTitle());
        progressBar.setVisibility(View.GONE);

        // Отдельная обработка, если у нас одна порция. Меняем на единственное число
        if (recipe.getServings() == 1) {
            recipeServings.setText(getResources().getString(R.string.serving, recipe.getServings()));
        } else {
            recipeServings.setText(getResources().getString(R.string.servings, recipe.getServings()));
        }

        recipeTime.setText(getResources().getString(R.string.time, recipe.getReadyInMinutes()));
    }


    /**
     *  Показываем вью, которые были спрятаны во время загрузки
     */
    private void showViews() {
        recipeTime.setVisibility(View.VISIBLE);
        recipeServings.setVisibility(View.VISIBLE);
        recipeImage.setVisibility(View.VISIBLE);
    }

    /**
     * Инициализируем viewPager здесь, так как нужно дождаться ответа с сервера и передать его фрагментам
     * @param recipe Ответ с сервера
     */
    private void initViewPager(Recipe recipe){
        Fragment firstFragment = IngredientsFragment.newInstance(recipe.getIngredientsList());
        Fragment secondFragment;

        // Иногда сервер не дает инструкций, поэтому передаем null, чтобы там отследить и показать пользователю, что инструкций нет
        if(recipe.getInstructions().size() == 0){
            secondFragment = InstructionsFragment.newInstance(null);
        } else {
            secondFragment = InstructionsFragment.newInstance(recipe.getInstructions().get(0).getSteps());
        }

        String ingredientsTitle = getContext().getResources().getString(R.string.ingredients);
        String instructionsTitle = getContext().getResources().getString(R.string.instructions);

        viewPagerAdapter = new RecipeViewPagerAdapter(getChildFragmentManager(),firstFragment, secondFragment,
                recipe, ingredientsTitle, instructionsTitle);
        viewPager.setAdapter(viewPagerAdapter);

        // Если установить лисенер раньше, то по какой то причине BottomSheet открывается, так что вызов метода здесь
        initListeners();
    }

    /**
     * С помощью этого лисенера при нажатии на вкладки BottomSheet будет раскрываться
     */
    private void initListeners() {
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
            }
        });
    }

}
