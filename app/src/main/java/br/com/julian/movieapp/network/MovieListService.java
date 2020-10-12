package br.com.julian.movieapp.network;

import br.com.julian.movieapp.models.MovieModel;
import br.com.julian.movieapp.models.ResponseModel;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieListService {
    //FIXME change list id location
    @GET("list/7061507")
    Call<ResponseModel<MovieModel>> getList(
            @Query("api_key") String apiKey,
            @Query("language") String language
    );
}
