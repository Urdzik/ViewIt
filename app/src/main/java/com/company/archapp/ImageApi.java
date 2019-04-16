package com.company.archapp;

import com.company.archapp.img.ImageDownloader;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ImageApi {
    @GET("photos/")
        Call<ImageDownloader> getAPIImg(
                @Query("query") String name,
                @Query("client_id") String client_id
        );
}
