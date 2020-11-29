package com.example.idillika.data;

import com.example.idillika.App;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DataManager {

    public void getStoreItems(RequestListener requestListener) {

        App.getNetworkStoreApi().getJSONApi().getData(
                "22",
                "f3e82db3d0b2bcce07eae17dd9cb46d3"
        ).
                enqueue(new Callback<List<Store>>() {
                    @Override
                    public void onResponse(Call<List<Store>> call, Response<List<Store>> response) {
                        if (response.body() != null) {
                            requestListener.OnSuccess((ArrayList<Store>) response.body());
                        } else {
                            requestListener.OnError("DATA is EMPTY");
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Store>> call, Throwable t) {
                        requestListener.OnError("" + t.getLocalizedMessage());
                    }
                });
    }
}
