package service;

import domain.Modelo;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ModeloApi {
    @GET("/posts/{id}")
    Call<Modelo> getModelo(@Path("id") int postId);

}