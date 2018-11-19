package com.example.mostafa.apptask.Rest;

import com.example.mostafa.apptask.classes.city;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CityResponse {
    @SerializedName("cnt")
    private int cnt;
    @SerializedName("list")
    private List<city> cities;

    public CityResponse(int cnt,List<city> cities)
    {
        this.cnt=cnt;
        this.cities=cities;
    }
    public int getCount()
    {
        return cnt;
    }
    public List<city> getCities()
    {
        return cities;
    }
}
