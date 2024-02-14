package com.android.cookbook.model;

import com.google.gson.annotations.SerializedName;

public class SignInRequestModel {
    @SerializedName("userName")
    final String userName;
    @SerializedName("password")
    final String password;

    public SignInRequestModel(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }
}