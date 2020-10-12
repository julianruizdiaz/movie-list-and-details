package br.com.julian.movieapp.ui.movieDetails;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import br.com.julian.movieapp.models.MovieModel;

public class MovieDetailsViewModel extends ViewModel {

    private MutableLiveData<MovieModel> mMovieModel = new MutableLiveData<>();

    public LiveData<MovieModel> getMovieModel() {
        return mMovieModel;
    }

    public void init(MovieModel movieModel) {
        mMovieModel.postValue(movieModel);
    }
}
