package com.example.android.norvicaconstruction.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Android on 4/6/2017.
 */
public class Logindata {

    @SerializedName("data")
    @Expose
    private String data;
    @SerializedName("success")
    @Expose
    private String success;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }
}


