package com.android.cookbook.screens;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.android.cookbook.model.CategoryModel;
import com.android.cookbook.model.MealModel;
import com.android.cookbook.remote.AuthRepository;
import com.android.cookbook.remote.CategoriesRepository;

import java.util.List;

import retrofit2.Response;

public class MainViewModel extends ViewModel {
    private CategoryModel selectedCategory;

    private CategoriesRepository categoriesRepository;
    private AuthRepository authRepository;

    public MainViewModel() {
        categoriesRepository = new CategoriesRepository();
        authRepository = new AuthRepository();
    }

    public CategoryModel getSelectedCategory() {
        return selectedCategory;
    }

    public void setSelectedCategory(CategoryModel category) {
        this.selectedCategory = category;
    }

    public MutableLiveData<Response<List<CategoryModel>>> getCategories() {
        return categoriesRepository.getCategories();
    }

    public MutableLiveData<Response<List<MealModel>>> getUserFavorites(String userId) {
        return authRepository.getUserFavorites(userId);
    }

    public MutableLiveData<Response<MealModel>> getMealById(String id) {
        return categoriesRepository.getMealById(id);
    }

    public MutableLiveData<Response<String>> addMealToFavorites(String userId, String mealId) {
        return categoriesRepository.addMealToFavorites(userId, mealId);
    }

    public MutableLiveData<Response<String>> removeMealFromFavorites(String userId, String mealId) {
        return categoriesRepository.removeFromFavorites(userId, mealId);
    }
}
