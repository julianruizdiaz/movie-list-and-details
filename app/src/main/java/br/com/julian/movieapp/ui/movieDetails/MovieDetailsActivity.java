package br.com.julian.movieapp.ui.movieDetails;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import br.com.julian.movieapp.R;
import br.com.julian.movieapp.databinding.ActivityMovieDetailsBinding;
import br.com.julian.movieapp.models.MovieModel;

public class MovieDetailsActivity extends AppCompatActivity {
    private static final String MOVIE_MODEL_BUNDLE_KEY = "movie_model";

    private MovieDetailsViewModel mMovieDetailsViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMovieDetailsBinding binding = ActivityMovieDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.setLifecycleOwner(this);

        mMovieDetailsViewModel = new ViewModelProvider(this).get(MovieDetailsViewModel.class);
        binding.setViewModel(mMovieDetailsViewModel);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            MovieModel movieModel = extras.getParcelable(MOVIE_MODEL_BUNDLE_KEY);
            mMovieDetailsViewModel.init(movieModel);
        } else {
            //TODO show error loading details
        }

        configureActionBar();
    }

    private void configureActionBar() {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setSubtitle(R.string.text_details);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public static Intent getIntent(Context context, MovieModel movieModel) {
        return new Intent(context, MovieDetailsActivity.class).putExtra(MOVIE_MODEL_BUNDLE_KEY, movieModel);
    }
}