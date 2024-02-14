package com.android.cookbook.remote;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.android.cookbook.model.CategoryModel;
import com.android.cookbook.model.MealModel;

import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoriesRepository {

    private final MutableLiveData<Response<List<CategoryModel>>> allCategories;
    private final MutableLiveData<Response<MealModel>> mealModel;
    private final MutableLiveData<Response<String>> addToFavoriteResponse;
    private final MutableLiveData<Response<String>> removeFromFavoritesResponse;

    public CategoriesRepository() {
        allCategories = new MutableLiveData<>();
        mealModel = new MutableLiveData<>();
        addToFavoriteResponse = new MutableLiveData<>();
        removeFromFavoritesResponse = new MutableLiveData<>();
    }

    public MutableLiveData<Response<List<CategoryModel>>> getCategories() {
        Call<List<CategoryModel>> call = RetrofitClient.getInstance().getapi().getCategories();
        call.enqueue(new Callback<List<CategoryModel>>() {
            @Override
            public void onResponse(Call<List<CategoryModel>> call, Response<List<CategoryModel>> response) {
                if (response.code() == 200) {
                    assert response.body() != null;
                    allCategories.setValue(Response.success(response.body()));
                } else {
                    allCategories.setValue(Response.error(response.code(), response.errorBody()));
                }
            }

            @Override
            public void onFailure(Call<List<CategoryModel>> call, Throwable t) {
                Log.e("sss", "error while calling" + t.getMessage());
                //allCategories.postValue(Response.error(344,  ResponseBody.create(MediaType.parse(""), "")));
            }
        });
        return allCategories;
    }


    public MutableLiveData<Response<MealModel>> getMealById(String id) {
        Call<MealModel> call = RetrofitClient.getInstance().getapi().getMealById(id);
        call.enqueue(new Callback<MealModel>() {
            @Override
            public void onResponse(Call<MealModel> call, Response<MealModel> response) {
                if (response.code() == 200) {
                    assert response.body() != null;
                    mealModel.setValue(Response.success(response.body()));
                } else {
                    mealModel.setValue(Response.error(response.code(), response.errorBody()));
                }
            }

            @Override
            public void onFailure(Call<MealModel> call, Throwable t) {
                Log.e("sss", "error while calling" + t.getMessage());
                //allCategories.postValue(Response.error(344,  ResponseBody.create(MediaType.parse(""), "")));
            }
        });
        return mealModel;
    }

    public MutableLiveData<Response<String>> addMealToFavorites(String userId, String mealId) {
        Call<String> call = RetrofitClient.getInstance().getapi().addMealToFavorites(userId, mealId);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.code() == 200) {
                    assert response.body() != null;
                    addToFavoriteResponse.setValue(Response.success(response.body()));
                } else {
                    addToFavoriteResponse.setValue(Response.error(response.code(), response.errorBody()));
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.e("sss", "error while calling" + t.getMessage());
                //allCategories.postValue(Response.error(344,  ResponseBody.create(MediaType.parse(""), "")));
            }
        });
        return addToFavoriteResponse;
    }

    public MutableLiveData<Response<String>> removeFromFavorites(String userId, String mealId) {
        Call<String> call = RetrofitClient.getInstance().getapi().removeMealFromFavorites(userId, mealId);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.code() == 200) {
                    addToFavoriteResponse.setValue(Response.success(response.body()));
                } else {
                    addToFavoriteResponse.setValue(Response.error(response.code(), response.errorBody()));
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.e("sss", "error while calling" + t.getMessage());
                //allCategories.postValue(Response.error(344,  ResponseBody.create(MediaType.parse(""), "")));
            }
        });
        return addToFavoriteResponse;
    }
}
