package com.sabanciuniv.controller;

import java.time.LocalDate;
import java.util.NoSuchElementException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sabanciuniv.model.Comment;
import com.sabanciuniv.model.Meal;
import com.sabanciuniv.model.User;
import com.sabanciuniv.service.CommentService;
import com.sabanciuniv.service.MealService;
import com.sabanciuniv.service.UserService;

@RestController
@RequestMapping("/api/meals")
public class MealController {
	private final MealService mealService;
	private final UserService userService;
	private final CommentService commentService;

	
	public MealController(MealService mealService, UserService userService, CommentService commentService) {
		this.mealService = mealService;
		this.userService = userService;
		this.commentService= commentService;
	}
	
//////////////////////////////////////	GET REQUESTS	/////////////////////////////////////////////
	
	


//http://localhost:8080/api/meals/{mealId}
	@GetMapping("/{mealId}")
	public ResponseEntity<Meal> getMealById(@PathVariable String mealId){
		return ResponseEntity.ok(mealService.getMealById(mealId));
	}

	
//http://localhost:8080/api/meals
	@GetMapping
	public ResponseEntity<List<Meal>> getAllMeals() {
	    List<Meal> meals = mealService.getAllMeals();
	    return ResponseEntity.ok(meals);
	}
	
	

	
	
	
//////////////////////////////////////	POST REQUESTS	/////////////////////////////////////////////
	
	
//http://localhost:8080/api/meals/{mealId}/addComment	
	@PostMapping("/{mealId}/addComment")
    public ResponseEntity<?> addCommentToMeal(@PathVariable String mealId, @RequestBody Map<String, String> commentDetails) {
        try {
            Comment comment = new Comment();
            comment.setCommentText(commentDetails.get("commentText"));

            // Fetch the user by userId to get the username
            User user = userService.getUserById(commentDetails.get("userId"));
            comment.setUserId(user.getUserId());
            comment.setUsername(user.getUsername()); // Set the username
            comment.setCommentDate(LocalDate.now()); // Set the current date

            comment = commentService.addCommentToMeal(mealId, comment); // Updated service method call
            return new ResponseEntity<>(comment, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	

	
	
	

}
