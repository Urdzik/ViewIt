package com.company.archapp.image;


import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Class for retrofit
 */
class NetworkService {
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

    static NetworkService getInstance() {
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
