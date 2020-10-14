package br.com.julian.movieapp.ui;

import android.widget.TextView;

import androidx.databinding.BindingAdapter;

import java.util.Date;

import br.com.julian.movieapp.R;
import br.com.julian.movieapp.models.MovieModel;
import br.com.julian.movieapp.util.DateUtil;

public class CommonBindingAdapter {
    @BindingAdapter(
            {"dateCondition", "dateDefault", "dateString", "dateFormatFrom", "dateFormatTo"}
    )
    public static void formatDate(
            TextView textView,
            Boolean dateCondition,
            String dateDefault,
            String dateString,
            String dateFormatFrom,
            String dateFormatTo
    ) {
        String finalDate = "";
        if (dateCondition) {
            Date date = DateUtil.stringToDate(dateString, dateFormatFrom);
            if (date != null) {
                finalDate = DateUtil.formatDate(date, dateFormatTo);
            } else {
                finalDate = dateDefault;
            }
        } else {
            finalDate = dateDefault;
        }
        textView.setText(textView.getContext().getString(R.string.text_movie_release_date, finalDate));
    }

    @BindingAdapter("votesFormat")
    public static void setVotesFormat(TextView textView, MovieModel movieModel) {
        if (movieModel != null) {
            String text = textView.getContext().getString(R.string.text_movie_votes, movieModel.getVoteAverage(), movieModel.getVoteCount());
            textView.setText(text);
        }
    }

    @BindingAdapter("originalTitleFormat")
    public static void setOriginalTitleFormat(TextView textView, String originalTitle) {
        if (originalTitle != null) {
            String text = textView.getContext().getString(R.string.text_movie_original_title, originalTitle);
            textView.setText(text);
        }
    }

    @BindingAdapter("overviewFormat")
    public static void setoverviewFormat(TextView textView, String overview) {
        if (overview != null) {
            String text = textView.getContext().getString(R.string.text_movie_overview, overview);
            textView.setText(text);
        }
    }
}
