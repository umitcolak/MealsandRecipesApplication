package com.sabanciuniv.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.sabanciuniv.model.Meal;

@Document
public class User {
	@Id private String userId;
	
	private String username;
	private String password;
	private List<Meal> favorites = new ArrayList<>();
	
//Constructors
	public User() {
			
	}

	public User(String userId, String username, String password, List<Meal> favorites) {
		super();
		this.userId = userId;
		this.username = username;
		this.password = password;
		this.favorites = favorites;
	}

	public User(String username, String password) {
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

	public List<Meal> getFavorites() {
		return favorites;
	}

	public void setFavorites(List<Meal> favorites) {
		this.favorites = favorites;
	}
	
//Overrides
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}
}
	
	
	

	