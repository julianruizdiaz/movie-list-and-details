package br.com.julian.movieapp.network;

import br.com.julian.movieapp.BuildConfig;
import br.com.julian.movieapp.models.MovieModel;
import br.com.julian.movieapp.models.ResponseModel;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieListService {
    String LIST_ID = BuildConfig.LIST_ID;

    @GET("list/" + LIST_ID)
    Call<ResponseModel<MovieModel>> getList(
            @Query("api_key") String apiKey,
            @Query("language") String language
    );
}
