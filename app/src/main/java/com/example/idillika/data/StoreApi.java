package com.example.idillika.data;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface StoreApi {

    @GET("catalogList.php")
    Call<List<Store>> getData(
            @Query("section") String section,
            @Query("session_id") String sessionId
    );
}
