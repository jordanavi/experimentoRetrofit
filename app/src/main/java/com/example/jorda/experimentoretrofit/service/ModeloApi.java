package com.example.jorda.experimentoretrofit.service;

import com.example.jorda.experimentoretrofit.domain.Modelo;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by gabrielgrs
 * Date: 27/08/2018
 * Time: 7:34 PM
 * Project: Retrofittesteaula
 * Alterated by jordanavilela
 */
public interface ModeloApi {

    @GET("/posts/{id}")
    Call<Modelo> getModelo(@Path("id") int postId);

}