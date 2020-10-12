package br.com.julian.movieapp.ui.movieList;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import br.com.julian.movieapp.models.MovieModel;
import br.com.julian.movieapp.models.ResponseModel;
import br.com.julian.movieapp.repository.MovieRepository;

public class MovieListViewModel extends ViewModel {

    private MediatorLiveData<ResponseModel<MovieModel>> mApiResponse = new MediatorLiveData<>();
    private MutableLiveData<List<MovieModel>> mMovieList = new MutableLiveData<>();
    private MutableLiveData<String> mListName = new MutableLiveData<>();
    private MutableLiveData<MovieModel> mViewMovieDetails = new MutableLiveData<>();

    public LiveData<List<MovieModel>> getMovieList() {
        return mMovieList;
    }

    public LiveData<String> getListName() {
        return mListName;
    }

    public LiveData<ResponseModel<MovieModel>> getApiResponse() {
        return mApiResponse;
    }

    public LiveData<MovieModel> getViewMovieDetails() {
        return mViewMovieDetails;
    }

    public MovieListViewModel() {
        MovieRepository movieRepository = new MovieRepository();

        mApiResponse.addSource(movieRepository.apiGetMovieList(), movieModelResponseModel -> {
            if (movieModelResponseModel != null) {
                mMovieList.postValue(movieModelResponseModel.getItems());
                mListName.postValue(movieModelResponseModel.getListName());
            } else {
                mMovieList.postValue(null);
            }
        });
    }

    public void onItemClick(MovieModel movieModel) {
        mViewMovieDetails.postValue(movieModel);
    }
}
