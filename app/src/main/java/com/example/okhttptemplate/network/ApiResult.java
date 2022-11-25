package com.example.okhttptemplate.network;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ApiResult<T> {
    @SerializedName("success")
    @Expose
    public boolean success;

    @SerializedName("msg")
    @Expose
    public String msg;

    @SerializedName("value")
    @Expose
    public T value;

    public Throwable error;

    @SerializedName("token")
    @Expose
    public String token;
}
