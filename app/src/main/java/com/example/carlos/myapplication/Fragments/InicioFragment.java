package com.example.carlos.myapplication.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.carlos.myapplication.R;

/**
 * Created by Deryan Cruz on 6/23/2018.
 */

public class InicioFragment extends Fragment{
    private View view;

    public InicioFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view =inflater.inflate(R.layout.recycler_view, container, false);
        return view;
    }
}
