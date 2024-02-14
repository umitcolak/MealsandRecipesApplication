package com.android.cookbook.remote;

import com.android.cookbook.model.CategoryModel;
import com.android.cookbook.model.MealModel;
import com.android.cookbook.model.RegisterRequestModel;
import com.android.cookbook.model.SignInRequestModel;
import com.android.cookbook.model.UserModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface Api {

    @POST("users/register")
    Call<String> register(@Body RegisterRequestModel model);

    @POST("users/login")
    Call<UserModel> login(@Body SignInRequestModel model);

    @GET("users/{userId}/favorites")
    Call<List<MealModel>> getUserFavorites(@Path("userId") String userId);

    @POST("users/{userId}/favorites/add/{mealId}")
    Call<String> addMealToFavorites(@Path("userId") String userId, @Path("mealId") String mealId);

    @DELETE("users/{userId}/favorites/remove/{mealId}")
    Call<String> removeMealFromFavorites(@Path("userId") String userId, @Path("mealId") String mealId);

    @GET("categories")
    Call<List<CategoryModel>> getCategories();

    @GET("categories/{categoryId}/meals")
    Call<List<MealModel>> getMealsByCategoryId(@Path("categoryId") String categoryId);

    @GET("meals")
    Call<List<MealModel>> getAllMeals();

    @GET("meals/{mealId}")
    Call<MealModel> getMealById(@Path("mealId") String mealId);
}