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
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.styleru.curry.CurryApplication;
import com.styleru.curry.R;
import com.styleru.curry.data.models.recipe.Recipe;
import com.styleru.curry.presentation.presenter.recipe.RecipePresenter;
import com.styleru.curry.presentation.view.cuisine.CuisineFragment;
import com.styleru.curry.presentation.view.recipe.adapter.RecipeViewPagerAdapter;

import javax.inject.Inject;

public class RecipeFragment extends Fragment implements RecipeView {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private RecipeViewPagerAdapter viewPagerAdapter;

    private TextView recipeTitle;
    private ImageView recipeImage;

    private BottomSheetBehavior bottomSheetBehavior;
    private ConstraintLayout bottomSheet;

    @Inject
    protected RecipePresenter presenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_about_recipe, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews(view);
        init();
    }

    private void initViews(View view) {
        viewPager = view.findViewById(R.id.recipe_pager);
        tabLayout = view.findViewById(R.id.recipe_tab_layout);
        bottomSheet = view.findViewById(R.id.bottom_sheet);
        recipeTitle = view.findViewById(R.id.recipe_title);
        recipeImage = view.findViewById(R.id.recipe_image);

        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet);


        tabLayout.setupWithViewPager(viewPager);
    }

    private void init() {
        CurryApplication.getRecipeComponent()
                .inject(this);

        presenter.attachView(this);
        if (getArguments() != null) {
            presenter.getRecipeById(getArguments().getInt(CuisineFragment.ID_KEY));
        }
    }

    @Override
    public void passData(Recipe recipe) {
        recipeTitle.setText(recipe.getTitle());
        Picasso.get()
                .load(recipe.getImageUrl())
                .into(recipeImage);

        viewPagerAdapter = new RecipeViewPagerAdapter(getChildFragmentManager(), recipe,
                getContext().getResources().getString(R.string.ingredients), getContext().getResources().getString(R.string.instructions));
        viewPager.setAdapter(viewPagerAdapter);

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

    @Override
    public void showError() {
        Toast.makeText(getContext(), R.string.error, Toast.LENGTH_LONG).show();
    }
}
