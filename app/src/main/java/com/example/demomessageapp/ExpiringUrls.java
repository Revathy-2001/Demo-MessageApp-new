package com.example.demomessageapp;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ExpiringUrls {
    @SerializedName("medium")
    @Expose
    private String medium;

    public String getMedium() {
        return medium;
    }

    public void setMedium(String medium) {
        this.medium = medium;
    }
}
