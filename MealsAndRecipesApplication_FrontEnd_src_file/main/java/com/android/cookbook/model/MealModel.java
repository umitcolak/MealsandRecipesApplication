package com.android.cookbook.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

public class MealModel  {
    private String mealId;


    private String mealName;
    private String recipe;
    private List<CommentModel> comments = new ArrayList<>();


    //Constructors
    public MealModel() {

    }

    public MealModel(String mealId, String mealName, String recipe, List<CommentModel> comments) {
        super();
        this.mealId = mealId;

        this.mealName = mealName;
        this.recipe = recipe;
        this.comments = comments;
    }


    public MealModel(String mealName, String recipe) {
        super();
        this.mealName = mealName;
        this.recipe = recipe;
    }


    //Getters&Setters
    public String getMealId() {
        return mealId;
    }

    public void setMealId(String id) {
        this.mealId = id; // Use 'this' to refer to the class field
    }


    public String getMealName() {
        return mealName;
    }

    public void setMealName(String mealName) {
        this.mealName = mealName;
    }

    public String getRecipe() {
        return recipe;
    }

    public void setRecipe(String recipe) {
        this.recipe = recipe;
    }

    public List<CommentModel> getComments() {
        return comments;
    }

    public void setComments(List<CommentModel> comments) {
        this.comments = comments;
    }


    //Overrides
    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return super.toString();
    }

    public static List<MealModel> createSampleMealList() {
        List<MealModel> mealList = new ArrayList<>();

        for (int i = 1; i <= 10; i++) {
            MealModel meal = new MealModel();
            meal.setMealId(String.valueOf(i));
            meal.setMealName("Meal " + i);
            meal.setRecipe("Recipe for Meal " + i);

            // Örnek olarak her meal'e birkaç yorum ekle
            for (int j = 1; j <= 2; j++) {
                CommentModel comment = new CommentModel();
                comment.setCommentId("C" + i + j);
                comment.setUserId("U" + j);
                comment.setUsername("User " + j);
                comment.setCommentText("Comment for Meal " + i);
                //comment.setCommentDate(java.time.LocalDate.now());
                meal.getComments().add(comment);
            }

            mealList.add(meal);
        }

        return mealList;
    }
}
