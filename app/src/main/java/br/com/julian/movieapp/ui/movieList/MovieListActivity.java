package br.com.julian.movieapp.ui.movieList;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import br.com.julian.movieapp.databinding.ActivityMovieListBinding;
import br.com.julian.movieapp.ui.movieDetails.MovieDetailsActivity;

public class MovieListActivity extends AppCompatActivity {

    private MovieListViewModel mMovieListViewModel;
    private RecyclerView mRecyclerViewMovieList;
    private ActivityMovieListBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMovieListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.setLifecycleOwner(this);

        mMovieListViewModel = new ViewModelProvider(this).get(MovieListViewModel.class);

        configureRecyclerView(binding);
        addObservers();
    }

    private void addObservers() {
        mMovieListViewModel.getApiResponse().observe(this, movieModelResponseModel -> {});

        mMovieListViewModel.getMovieList().observe(this, movieList -> {
            if (movieList != null && movieList.size() > 0) {
                MovieListAdapter movieListAdapter = new MovieListAdapter(movieList, mMovieListViewModel);
                mRecyclerViewMovieList.setAdapter(movieListAdapter);
                showRecyclerView(true);
            } else {
                showRecyclerView(false);
            }
        });

        mMovieListViewModel.getListName().observe(this, listName -> {
            if (getSupportActionBar() != null) {
                if (listName != null) {
                    getSupportActionBar().setSubtitle(listName);
                }
            }
        });

        mMovieListViewModel.getViewMovieDetails().observe(this, movieModel -> {
            Intent intent = MovieDetailsActivity.getIntent(this, movieModel);
            startActivity(intent);
        });
    }

    private void showRecyclerView(Boolean show) {
        if (show) {
            binding.recyclerViewMovieList.setVisibility(View.VISIBLE);
            binding.emptyListView.setVisibility(View.GONE);
        } else {
            binding.recyclerViewMovieList.setVisibility(View.GONE);
            binding.emptyListView.setVisibility(View.VISIBLE);
        }
    }

    private void configureRecyclerView(ActivityMovieListBinding binding) {
        mRecyclerViewMovieList = binding.recyclerViewMovieList;
        mRecyclerViewMovieList.setLayoutManager(new LinearLayoutManager(this));
    }
}