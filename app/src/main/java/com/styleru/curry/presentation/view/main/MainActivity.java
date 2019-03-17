package com.styleru.curry.presentation.view.main;

import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.styleru.curry.CurryApplication;
import com.styleru.curry.R;

import java.io.IOException;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CurryApplication.getAppComponent().inject(this);

        initViews();
        init();
    }

    private void initViews(){
        bottomNavigationView = findViewById(R.id.bottom_nav);
    }

    private void init(){
        NavigationUI.setupWithNavController(bottomNavigationView, Navigation.findNavController(this, R.id.nav_host_fragment));
    }

    @Override
    public void onBackPressed() {
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment)
                .getChildFragmentManager().getFragments().get(0);


        if(!(fragment instanceof FragmentOnBackPressedListener) || !((FragmentOnBackPressedListener) fragment).onBackPressed() ){
            super.onBackPressed();
        }
    }
}
