package com.example.noteyourday;

import com.example.noteyourday.models.MyDayEvent;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface YelpDayApi {
    @GET("events")
    Call<MyDayEvent>getEvents (
            @Query("location") String location,
            @Query("term") String term
    );


}
