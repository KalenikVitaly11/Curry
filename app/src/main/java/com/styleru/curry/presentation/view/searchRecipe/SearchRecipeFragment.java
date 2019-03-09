package com.styleru.curry.presentation.view.searchRecipe;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.styleru.curry.CurryApplication;
import com.styleru.curry.R;
import com.styleru.curry.data.models.recipe.ShortRecipe;
import com.styleru.curry.data.network.api.CurryWebHelper;
import com.styleru.curry.data.network.dataStore.WebDataStoreImpl;
import com.styleru.curry.data.repositories.cuisine.CuisineRepositoryImpl;
import com.styleru.curry.domain.cuisine.CuisineRecipesInteractorImpl;
import com.styleru.curry.domain.cuisine.models.CuisineRecipes;
import com.styleru.curry.presentation.presenter.searchRecipe.SearhRecipePresenter;
import com.styleru.curry.presentation.view.main.MainActivity;
import com.styleru.curry.presentation.view.searchRecipe.adapter.CuisineRecyclerOnClick;
import com.styleru.curry.presentation.view.searchRecipe.adapter.CuisineRecyclerViewAdapter;

import javax.inject.Inject;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class SearchRecipeFragment extends Fragment  implements CuisineRecyclerOnClick, SearchRecipeView {

    public static final String ID_KEY = "recipeId";

    private RecyclerView cuisineRecyclerViewOne;
    private RecyclerView cuisineRecyclerViewTwo;
    private RecyclerView cuisineRecyclerViewThree;
    private CuisineRecyclerViewAdapter adapter;

    private TextView firstCuisineTitle;

    @Inject
    protected SearhRecipePresenter presenter;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search_recipe, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews(view);
        init();
    }

    private void initViews(View view){
        cuisineRecyclerViewOne = view.findViewById(R.id.cuisine_recipes_one);
//        cuisineRecyclerViewTwo = view.findViewById(R.id.cuisine_recipes_two);
//        cuisineRecyclerViewThree = view.findViewById(R.id.cuisine_recipes_three);
        firstCuisineTitle = view.findViewById(R.id.search_recipe_first_title);
    }

    private void init(){
        // Проводим инъекцию
        CurryApplication.getCuisineComponent()
                .inject(this);

        adapter = new CuisineRecyclerViewAdapter(this);
        cuisineRecyclerViewOne.setAdapter(adapter);
        presenter.attachView(this);

        presenter.getCuisineRecipes();
    }

    /**
     * OnClick метод для адаптера - нажимаем на элемент и идем во фрагмент о рецепте
     * @param id id нажатого элемента, который передается во фрагмент, чтобы та отобразить подробную инфу о рецепте
     */
    @Override
    public void recyclerOnClick(int id) {
        Bundle bundle = new Bundle();
        bundle.putInt(ID_KEY, id);
        Navigation.findNavController(getActivity(), R.id.nav_host_fragment).navigate(R.id.action_searchRecipeFragment_to_recipeFragment, bundle);
    }

     /**
      * Обновляем данные в адаптере
      * @param cuisineRecipes объект с рецептами, получаемый с сервера
      */
    @Override
    public void setData(CuisineRecipes cuisineRecipes) {
        firstCuisineTitle.setText(cuisineRecipes.getCuisine());
        adapter.updateData(cuisineRecipes.getShortRecipes());
    }

    /**
     * Уведомить пользователя об ошибке
     */
    @Override
    public void showError() {
        Toast.makeText(getContext(), getResources().getText(R.string.error), Toast.LENGTH_LONG).show();
    }
}