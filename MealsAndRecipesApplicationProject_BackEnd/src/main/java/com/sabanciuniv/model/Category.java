package com.sabanciuniv.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.sabanciuniv.model.Meal;

@Document
public class Category {
	@Id private String categoryId;
	
	private String categoryName;
	private List<Meal> meals = new ArrayList<>(); 
	
	
	
//Constructors
	public Category() {
		
	}

	public Category(String categoryId, String categoryName, List<Meal> meals) {
		super();
		this.categoryId = categoryId;
		this.categoryName = categoryName;
		this.meals = meals;
	}
	
	
	public Category(String categoryName) {
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

	public List<Meal> getMeals() {
		return meals;
	}

	public void setMeals(List<Meal> meals) {
		this.meals = meals;
	}
	
	
//Overrides
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}

	
}