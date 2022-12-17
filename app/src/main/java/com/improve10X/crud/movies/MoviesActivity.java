package com.improve10X.crud.movies;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.improve10X.crud.R;
import com.improve10X.crud.base.BaseActivity;

public class MoviesActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);
        getSupportActionBar().setTitle("Movies");
    }
}