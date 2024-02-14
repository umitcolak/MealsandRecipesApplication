package com.sabanciuniv.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import com.sabanciuniv.model.Category;
import com.sabanciuniv.model.Meal;
import com.sabanciuniv.repo.CategoryRepository;
import com.sabanciuniv.repo.MealRepository;


@Service
public class CategoryService {
	private final CategoryRepository categoryRepository;
	private final MealRepository mealRepository;
	
	@Autowired
	public CategoryService(CategoryRepository categoryRepository, MealRepository mealRepository) {
		this.categoryRepository = categoryRepository;
		this.mealRepository = mealRepository;
	}
	
	public Category getCategoryByName(String categoryName) {
		return categoryRepository.findByCategoryName(categoryName);
	}
	
	public List<Category> getAllCategories() {
		return categoryRepository.findAll();
	}
	
	public Category getCategoryById(String categoryId) {
		Optional<Category> category= categoryRepository.findById(categoryId);
		if(category.isPresent()) {
			return category.get();
		}
		else {
			throw new RuntimeException("Category not found with id: " + categoryId);
		}
		
	}
	
	public Category addCategory(Category category) {
	    if (categoryRepository.existsByCategoryName(category.getCategoryName())) {
	        throw new RuntimeException("Category already exists with name: " + category.getCategoryName());
	    }
	    return categoryRepository.save(category);
	}
	
	
	public void deleteCategory(String categoryId) {
        if (!categoryRepository.existsById(categoryId)) {
            throw new RuntimeException("Category not found with id: " + categoryId);
        }
        categoryRepository.deleteById(categoryId);
    }
	
	public Meal addMealToCategory(String categoryId, Meal meal) {
        Category category = categoryRepository.findById(categoryId)
            .orElseThrow(() -> new RuntimeException("Category not found with id: " + categoryId));

        // Assume MealRepository's save method returns the saved Meal entity
        Meal savedMeal = mealRepository.save(meal);

        // Adding the saved meal to the category
        category.getMeals().add(savedMeal);
        categoryRepository.save(category); // Saving the updated category

        return savedMeal;
    }
	
	public void deleteMealFromCategory(String categoryId, String mealId) {
	    // Fetch the category
	    Category category = categoryRepository.findById(categoryId)
	        .orElseThrow(() -> new RuntimeException("Category not found with id: " + categoryId));

	    // Remove the meal from the category's meals list
	    category.setMeals(category.getMeals().stream()
	        .filter(meal -> !meal.getMealId().equals(mealId))
	        .collect(Collectors.toList()));

	    // Save the updated category
	    categoryRepository.save(category);

	    // Do not delete the meal from the meal repository
	}
	
}

