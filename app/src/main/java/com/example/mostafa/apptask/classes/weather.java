package com.example.mostafa.apptask.classes;

import com.google.gson.annotations.SerializedName;

public class weather {
    public String main;
    public String description;
    @SerializedName("icon")
    public String icon;

}
