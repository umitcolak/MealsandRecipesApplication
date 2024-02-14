package com.sabanciuniv.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.sabanciuniv.model.Comment;

@Document
public class Meal {
	@Id
	private String id;
	

	private String mealName;
	private String recipe;
	private List<Comment> comments = new ArrayList<>();
	
	
	
//Constructors	
	public Meal() {
		
	}
	

	public Meal(String id, String mealName, String recipe, List<Comment> comments) {
		super();
		this.id = id;

		this.mealName = mealName;
		this.recipe = recipe;
		this.comments = comments;
	}


	
	public Meal(String mealName, String recipe) {
			super();
			this.mealName = mealName;
			this.recipe = recipe;
		}


//Getters&Setters
	public String getMealId() {
		return id;
	}

	public void setMealId(String id) {
	    this.id = id; // Use 'this' to refer to the class field
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

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	
	
	
//Overrides
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}

}
