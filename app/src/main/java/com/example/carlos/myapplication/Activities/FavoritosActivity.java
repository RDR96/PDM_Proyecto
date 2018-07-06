package com.example.carlos.myapplication.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import static com.example.carlos.myapplication.Fragments.ViewPager.position;

import com.example.carlos.myapplication.R;

public class FavoritosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favoritos);
        position = 1;

    }
}
