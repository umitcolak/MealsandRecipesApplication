package com.android.cookbook.model;


import java.util.ArrayList;
import java.util.List;

public class CategoryModel {
    private String categoryId;

    private String categoryName;
    private List<MealModel> meals = new ArrayList<>();


    //Constructors
    public CategoryModel() {

    }

    public CategoryModel(String categoryId, String categoryName, List<MealModel> mealModels) {
        super();
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.meals = mealModels;
    }


    public CategoryModel(String categoryName) {
        super();
        this.categoryName = categoryName;
    }

    //Getters&Setters
    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public List<MealModel> getMeals() {
        return meals;
    }

    public void setMeals(List<MealModel> mealModels) {
        this.meals = mealModels;
    }


    //Overrides
    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return super.toString();
    }

    public static List<CategoryModel> generateSampleCategories() {
        List<CategoryModel> categories = new ArrayList<>();

        for (int i = 1; i <= 10; i++) {
            String categoryId = "CatID" + i;
            String categoryName = "Category" + i;

            List<MealModel> mealModels = MealModel.createSampleMealList();

            CategoryModel category = new CategoryModel(categoryId, categoryName, mealModels);
            categories.add(category);
        }

        return categories;
    }

}

