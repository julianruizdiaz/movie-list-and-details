package br.com.julian.movieapp.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import org.jetbrains.annotations.NotNull;

import br.com.julian.movieapp.BuildConfig;
import br.com.julian.movieapp.models.MovieModel;
import br.com.julian.movieapp.models.ResponseModel;
import br.com.julian.movieapp.network.MovieAPI;
import br.com.julian.movieapp.network.MovieListService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieRepository {

    private MovieListService getMovieListService() {
        return MovieAPI.getInstance().create(MovieListService.class);
    }

    public LiveData<ResponseModel<MovieModel>> apiGetMovieList() {
        MutableLiveData<ResponseModel<MovieModel>> responseData = new MutableLiveData<>();

        Call<ResponseModel<MovieModel>> call = getMovieListService().getList(BuildConfig.API_KEY, "pt-BR");

        call.enqueue(new Callback<ResponseModel<MovieModel>>() {
            @Override
            public void onResponse(@NotNull Call<ResponseModel<MovieModel>> call, @NotNull Response<ResponseModel<MovieModel>> response) {
                responseData.postValue(response.body());
            }

            @Override
            public void onFailure(@NotNull Call<ResponseModel<MovieModel>> call, @NotNull Throwable t) {
                responseData.postValue(null);
            }
        });

        return responseData;
    }
}
