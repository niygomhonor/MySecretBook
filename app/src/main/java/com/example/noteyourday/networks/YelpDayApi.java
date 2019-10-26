package com.example.noteyourday.networks;

import com.example.noteyourday.models.MyDayEvent;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface YelpDayApi {
    @GET("events")
    Call<MyDayEvent>getEvents (
            @Query("location") String location

    );


}
