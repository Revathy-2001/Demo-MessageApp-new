package com.example.demomessageapp;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Avatar {
    @SerializedName("id")
    @Expose
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    @SerializedName("expiring_urls")
    @Expose
    private ExpiringUrls expiringUrls;

    public ExpiringUrls getExpiringUrls() {
        return expiringUrls;
    }

    public void setExpiringUrls(ExpiringUrls expiringUrls) {
        this.expiringUrls = expiringUrls;
    }

}
