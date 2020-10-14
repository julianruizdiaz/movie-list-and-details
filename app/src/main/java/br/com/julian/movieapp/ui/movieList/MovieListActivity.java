package br.com.julian.movieapp.ui.movieList;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import br.com.julian.movieapp.R;
import br.com.julian.movieapp.databinding.ActivityMovieListBinding;
import br.com.julian.movieapp.ui.movieDetails.MovieDetailsActivity;

public class MovieListActivity extends AppCompatActivity {

    private MovieListViewModel mMovieListViewModel;
    private RecyclerView mRecyclerViewMovieList;
    private ActivityMovieListBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityMovieListBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());
        mBinding.setLifecycleOwner(this);

        mMovieListViewModel = new ViewModelProvider(this).get(MovieListViewModel.class);

        configureRecyclerView(mBinding);
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
                    getSupportActionBar().setSubtitle(
                            this.getString(R.string.text_subtitle_list_name, listName)
                    );
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
            mBinding.recyclerViewMovieList.setVisibility(View.VISIBLE);
            mBinding.emptyListView.setVisibility(View.GONE);
        } else {
            mBinding.recyclerViewMovieList.setVisibility(View.GONE);
            mBinding.emptyListView.setVisibility(View.VISIBLE);
        }
    }

    private void configureRecyclerView(ActivityMovieListBinding binding) {
        mRecyclerViewMovieList = binding.recyclerViewMovieList;
        mRecyclerViewMovieList.setLayoutManager(new LinearLayoutManager(this));
    }
}