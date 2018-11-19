package com.example.mostafa.apptask.classes;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class city {
    public String name ;
    public String id;
    public sys sys;
    @SerializedName("main")
    public MainTemp temp;
    @SerializedName("weather")
    public List<weather> weatherList;

    @SerializedName("dt_txt")
    public String date;
}
