package com.company.archapp.image;

import com.company.archapp.image.img.ImageDownloader;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Interface for retrofit
 */
interface ImageApi {
    @GET("photos/")
    Call<ImageDownloader> getAPIImg(
            @Query("query") String name,
            @Query("client_id") String client_id
    );
}
