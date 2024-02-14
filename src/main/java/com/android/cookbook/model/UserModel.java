package com.android.cookbook.model;

import java.util.ArrayList;
import java.util.List;

public class UserModel {
    private String userId;

    private String username;
    private String password;
    private List<MealModel> favorites = new ArrayList<>();

    //Constructors
    public UserModel() {

    }

    public UserModel(String userId, String username, String password, List<MealModel> favorites) {
        super();
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.favorites = favorites;
    }

    public UserModel(String username, String password) {
        super();
        this.username = username;
        this.password = password;
    }


//Getters&Setters

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<MealModel> getFavorites() {
        return favorites;
    }

    public void setFavorites(List<MealModel> favorites) {
        this.favorites = favorites;
    }

    //Overrides
    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return super.toString();
    }
}




