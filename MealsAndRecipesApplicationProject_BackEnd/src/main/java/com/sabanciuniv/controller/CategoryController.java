package com.sabanciuniv.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sabanciuniv.model.Category;
import com.sabanciuniv.model.Meal;
import com.sabanciuniv.service.CategoryService;
import com.sabanciuniv.service.MealService;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
	private final CategoryService categoryService;
	private final MealService mealService;
		
	@Autowired
	public CategoryController(CategoryService categoryService, MealService mealService) {
		this.categoryService = categoryService;
		this.mealService = mealService;
	}
////////////////////////////////////// GET REQUESTS	/////////////////////////////////////////////
	
//http://localhost:8080/api/categories
	@GetMapping
	public ResponseEntity<List<Category>> getAllCategories() {
		List<Category> categories= categoryService.getAllCategories();
		return ResponseEntity.ok(categories);
	}
	
//http://localhost:8080/api/categories/{categoryId}/meals
	@GetMapping("/{categoryId}/meals")
	public ResponseEntity<?> getMealsByCategory(@PathVariable String categoryId) {
	    try {
	        Category category = categoryService.getCategoryById(categoryId);
	        return ResponseEntity.ok(category.getMeals());
	    } catch (RuntimeException e) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
	    }
	}
	
	
////////////////////////////////////// POST REQUESTS	/////////////////////////////////////////////	

// http://localhost:8080/api/categories
	@PostMapping
	public ResponseEntity<Category> addCategory(@RequestBody Category category) {
	    Category newCategory = categoryService.addCategory(category);
	    return new ResponseEntity<>(newCategory, HttpStatus.CREATED);
	}

//http://localhost:8080/api/categories/{categoryId}
	@DeleteMapping("/{categoryId}")
    public ResponseEntity<?> deleteCategory(@PathVariable String categoryId) {
        try {
            categoryService.deleteCategory(categoryId);
            return ResponseEntity.ok().build(); // You can also return a custom message if desired
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
	
//http://localhost:8080/api/categories/{categoryId}/meals
	@PostMapping("/{categoryId}/meals")
	public ResponseEntity<?> addMealToCategory(@PathVariable String categoryId, @RequestBody Meal meal) {
	    try {
	        Meal addedMeal = categoryService.addMealToCategory(categoryId, meal);
	        return ResponseEntity.status(HttpStatus.CREATED).body(addedMeal);
	    } catch (RuntimeException e) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Category not found with id: " + categoryId);
	    }
	}
	
//http://localhost:8080/api/categories/{categoryId}/meals/{mealId}	
	@DeleteMapping("/{categoryId}/meals/{mealId}")
	public ResponseEntity<?> deleteMealFromCategory(@PathVariable String categoryId, @PathVariable String mealId) {
	    try {
	        categoryService.deleteMealFromCategory(categoryId, mealId);
	        return ResponseEntity.ok().build();
	    } catch (RuntimeException e) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
	    }
	}

}
