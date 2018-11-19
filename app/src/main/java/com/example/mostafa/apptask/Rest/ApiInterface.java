package com.example.mostafa.apptask.Rest;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("group?id=524901,703448,2643743,1851632,360630&units=metric&appid=fa468d980f8cb20b8408080cd66ea0bc")
    Call<CityResponse> getCities();
    @GET("forecast?&appid=fa468d980f8cb20b8408080cd66ea0bc")
    Call<CityResponse>getCitiesForcast(@Query("id") String id);
}
