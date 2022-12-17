package com.improve10X.crud.movies;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.improve10X.crud.R;
import com.improve10X.crud.base.BaseActivity;

import java.util.ArrayList;

public class MoviesActivity extends BaseActivity {
    private ArrayList<Movie> movies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);
        getSupportActionBar().setTitle("Movies");
        setupView();
    }

    private void setupView() {
        movies = new ArrayList<>();

        Movie movie1 = new Movie();
        movie1.id = "637c391d23685e03e8b8f4e1";
        movie1.description = "2010 TELUGU MOVIES";
        movie1.name = "GAGANAM";
        movie1.series = "8";
        movie1.imageUrl = "https://m.media-amazon.com/images/M/MV5BMmEyZDBlNjQtNzFiNy00ZDFlLWIwYTAtNzgwOTNmNjgxNTk3XkEyXkFqcGdeQXVyMzU0NzkwMDg@._V1_.jpg";
        movies.add(movie1);

        Movie movie2 = new Movie();
        movie2.id = "637c391d23685e03e8b8f4e1";
        movie2.description = "2010 TELUGU MOVIES";
        movie2.name = "GAGANAM";
        movie2.series = "8";
        movie2.imageUrl = "https://m.media-amazon.com/images/M/MV5BMmEyZDBlNjQtNzFiNy00ZDFlLWIwYTAtNzgwOTNmNjgxNTk3XkEyXkFqcGdeQXVyMzU0NzkwMDg@._V1_.jpg";
        movies.add(movie2);
    }
}