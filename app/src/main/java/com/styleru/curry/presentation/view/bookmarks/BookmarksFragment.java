package com.styleru.curry.presentation.view.bookmarks;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.styleru.curry.R;

import androidx.navigation.Navigation;


public class BookmarksFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        Navigation.findNavController(getActivity(), R.id.nav_host_fragment).navigate(R.id.action_bookmarksFragment_to_recipeFragment);
        return inflater.inflate(R.layout.fragment_bookmarks, container, false);
    }
}
