package com.android.cookbook.remote;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.android.cookbook.model.CategoryModel;
import com.android.cookbook.model.MealModel;
import com.android.cookbook.model.RegisterRequestModel;
import com.android.cookbook.model.SignInRequestModel;
import com.android.cookbook.model.UserModel;

import java.util.List;

import okhttp3.MediaType;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AuthRepository {
    private final MutableLiveData<Response<UserModel>> loginResult;
    private final MutableLiveData<Response<String>> registerResult;
    private final MutableLiveData<Response<List<MealModel>>> favoritesResult;

    public AuthRepository() {
        this.loginResult = new MutableLiveData<>();
        this.registerResult = new MutableLiveData<>();
        this.favoritesResult = new MutableLiveData<>();
    }

    public MutableLiveData<Response<UserModel>> signIn(String username, String password) {
        SignInRequestModel model = new SignInRequestModel(username, password);
        Call<UserModel> call = RetrofitClient.getInstance().getapi().login(model);
        call.enqueue(new Callback<UserModel>() {
            @Override
            public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                if (response.isSuccessful()) {
                    loginResult.setValue(Response.success(response.body()));
                } else {
                    loginResult.setValue(Response.error(response.code(), response.errorBody()));
                }
            }

            @Override
            public void onFailure(Call<UserModel> call, Throwable t) {
                loginResult.setValue(Response.error(23, ResponseBody.create(MediaType.get(""), "")));

            }
        });
        return loginResult;
    }

    public MutableLiveData<Response<String>> register(String username, String password) {
        RegisterRequestModel model = new RegisterRequestModel(username, password);
        Call<String> call = RetrofitClient.getInstance().getapi().register(model);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful()) {
                    registerResult.setValue(Response.success(response.body()));
                } else {
                    registerResult.setValue(Response.error(response.code(), response.errorBody()));
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                //loginResult.setValue(Response.error();

            }
        });
        return registerResult;
    }

    public MutableLiveData<Response<List<MealModel>>> getUserFavorites(String userId) {
        Call<List<MealModel>> call = RetrofitClient.getInstance().getapi().getUserFavorites(userId);
        call.enqueue(new Callback<List<MealModel>>() {
            @Override
            public void onResponse(Call<List<MealModel>> call, Response<List<MealModel>> response) {
                if (response.isSuccessful()) {
                    favoritesResult.setValue(Response.success(response.body()));
                } else {
                    favoritesResult.setValue(Response.error(response.code(), response.errorBody()));
                }
            }

            @Override
            public void onFailure(Call<List<MealModel>> call, Throwable t) {
                loginResult.setValue(Response.error(23, ResponseBody.create(MediaType.get(""), "")));

            }
        });
        return favoritesResult;
    }

}
