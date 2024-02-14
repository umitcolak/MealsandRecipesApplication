package com.sabanciuniv.service;

import java.time.LocalDate;
import java.util.NoSuchElementException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sabanciuniv.model.Comment;
import com.sabanciuniv.model.Meal;
import com.sabanciuniv.repo.CommentRepository;
import com.sabanciuniv.repo.MealRepository;

@Service
public class MealService {
	private final MealRepository mealRepository;
	
	@Autowired
	public MealService(MealRepository mealRepository){
		this.mealRepository = mealRepository;
	}
	

	public List<Meal> getAllMeals() {
	    return mealRepository.findAll();
	}
	
	
	public Meal getMealById(String mealId) {
	    return mealRepository.findById(mealId)
	        .orElseThrow(() -> new NoSuchElementException("Meal not found for ID: " + mealId));
	}

	
	public Meal addMeal(Meal meal) {
	    if (mealRepository.existsByMealName(meal.getMealName())) {
	        throw new RuntimeException("Meal already exists with name: " + meal.getMealName());
	    }
	    return mealRepository.save(meal);
	}

    public void deleteMeal(String mealId) {
        mealRepository.deleteById(mealId);
    }
    
    
	
}
