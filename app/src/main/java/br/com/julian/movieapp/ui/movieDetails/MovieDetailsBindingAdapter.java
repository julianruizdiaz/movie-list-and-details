package br.com.julian.movieapp.ui.movieDetails;

import android.widget.TextView;

import androidx.databinding.BindingAdapter;

import br.com.julian.movieapp.R;
import br.com.julian.movieapp.models.MovieModel;

public class MovieDetailsBindingAdapter {
    @BindingAdapter("votesFormat")
    public static void setVotesFormat(TextView textView, MovieModel movieModel) {
        if (movieModel != null) {
            String text = textView.getContext().getString(R.string.text_movie_votes, movieModel.getVoteAverage(), movieModel.getVoteCount());
            textView.setText(text);
        }
    }
}
