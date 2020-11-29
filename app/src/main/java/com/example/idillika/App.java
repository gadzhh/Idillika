package com.example.idillika;

import com.example.idillika.data.StoreApi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class App {

    private static final String BASE_URL = "https://idillika.com/api/";
    private static App networkStoreApi;
    private Retrofit retrofit;

    private App() {

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static App getNetworkStoreApi() {

        if (networkStoreApi == null) {
            networkStoreApi = new App();
        }
        return networkStoreApi;
    }

    public StoreApi getJSONApi() {
        return retrofit.create(StoreApi.class);
    }
}
