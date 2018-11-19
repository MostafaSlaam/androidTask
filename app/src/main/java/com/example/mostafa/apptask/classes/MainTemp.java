package com.example.mostafa.apptask.classes;

import com.google.gson.annotations.SerializedName;

public class MainTemp {
    @SerializedName("temp")
    public String temp;
    @SerializedName("temp_min")
    public String temp_min;
    @SerializedName("temp_max")
    public String temp_max;
    public MainTemp(String t1,String t2,String t3)
    {
        temp=t1;
        temp_min=t2;
        temp_max=t3;
    }
}
