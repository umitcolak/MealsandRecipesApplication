package com.android.cookbook.model;

import com.google.gson.annotations.SerializedName;

public class RegisterRequestModel {
    @SerializedName("userName")
    final String userName;
    @SerializedName("password")
    final String password;

    public RegisterRequestModel(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }
}