package com.company.archapp;


import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkService {
    private static NetworkService instance;
    private static final Object lock = new Object();
    private static final String BASE_URL = "https://api.unsplash.com/search/";
    private Retrofit retrofit;

    private NetworkService() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static NetworkService getInstance() {
        if (instance == null)
            synchronized (lock) {
                if (instance == null)
                    instance = new NetworkService();
            }
        return instance;
    }

    public ImageApi getImgApi() {
        return retrofit.create(ImageApi.class);
    }

}
