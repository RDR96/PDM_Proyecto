package com.example.carlos.myapplication.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.carlos.myapplication.R;

/**
 * Created by Deryan Cruz on 6/23/2018.
 */

public class PerfilFragment extends Fragment{
    private View view;

    public PerfilFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view =inflater.inflate(R.layout.container, container, false);
        return view;
    }
}