package com.sabanciuniv.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sabanciuniv.model.Meal;
import com.sabanciuniv.model.User;
import com.sabanciuniv.repo.MealRepository;
import com.sabanciuniv.repo.UserRepository;

@Service
public class UserService {
	private final UserRepository userRepository;
	private final MealRepository mealRepository;

	
	@Autowired
	public UserService(UserRepository userRepository, MealRepository mealRepository) {
		this.userRepository = userRepository;
		this.mealRepository = mealRepository;
	}
	
	 public List<User> getAllUsers() {
	        return userRepository.findAll();
	 }
	 
	 
	public List<Meal> getFavoriteMeals(String userId) {
	    User user = userRepository.findById(userId)
	                    .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
	    return user.getFavorites();
	}
	
	
	public User addFavoriteMeal(String userId, String mealId) {
		User user= userRepository.findById(userId).orElseThrow(()-> new RuntimeException("User not found with id: " + userId));
		Meal meal = mealRepository.findById(mealId).orElseThrow(() -> new RuntimeException("Meal not found with id: " + mealId));
		List<Meal> favorites = user.getFavorites();
		if(user.getFavorites()== null) {
			favorites = new ArrayList<>();
            user.setFavorites(favorites);
		}
		favorites.add(meal);
        return userRepository.save(user);
	
	}
	public User removeFavoriteMeal(String userId, String mealId) {
	    User user = userRepository.findById(userId)
	                    .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));

	    user.getFavorites().removeIf(m -> m.getMealId().equals(mealId));
	    return userRepository.save(user);
	}
	
	public User registerUser(User user) {
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new IllegalStateException("Username already exists");
        }

        try {
            return userRepository.save(user);
        } catch (Exception e) {
            throw new RuntimeException("Unable to register user: " + e.getMessage());
        }
    }
	
	public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }
	
	public User getUserById(String userId) {
	    return userRepository.findById(userId)
	        .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
	}
	
	public boolean authenticateUser(String username, String password) {
        return userRepository.existsByUsernameAndPassword(username, password);
    }
	
	
	
}
