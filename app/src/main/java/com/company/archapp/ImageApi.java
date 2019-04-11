package com.company.archapp;

import com.company.archapp.img.ImageSearch;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ImageApi {
//создаём GET-запрос по необходимой ссылке
    @GET("/")
    Call<ImageSearch> getAPIImg(
            @Query("q") String city,
            @Query("iax") String iax,
            @Query("ia") String ia,
            @Query("format") String format
    );
}
