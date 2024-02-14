package com.sabanciuniv.controller;



import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
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
import com.sabanciuniv.model.User;
import com.sabanciuniv.repo.CategoryRepository;
import com.sabanciuniv.repo.MealRepository;
import com.sabanciuniv.repo.UserRepository;
import com.sabanciuniv.service.UserService;

import jakarta.annotation.PostConstruct;

@RestController
@RequestMapping("/api/users")
public class UserController {
	private final UserService userService;
	 	
	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	
////////////////////////////////////// 	GET REQUESTS	/////////////////////////////////////////////

	
//http://localhost:8080/api/users/
	@GetMapping()
	  public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers().stream()
            .map(user -> new User(user.getUserId(), user.getUsername(), null, user.getFavorites()))
            .collect(Collectors.toList());
        return ResponseEntity.ok(users);
    }	
   
//http://localhost:8080/api/users/{userId}
		@GetMapping("/{userId}")
		public ResponseEntity<User> getUserById(@PathVariable String userId){
			return ResponseEntity.ok(userService.getUserById(userId));
		}
		
//http://localhost:8080/api/users/{userId}/favorites
		@GetMapping("/{userId}/favorites")
		public ResponseEntity<List<Meal>> getUserFavorites(@PathVariable String userId) {
		    List<Meal> favorites = userService.getFavoriteMeals(userId);
		    return ResponseEntity.ok(favorites);
		}
		
		

//////////////////////////////////////	POST REQUESTS	/////////////////////////////////////////////


//http://localhost:8080/api/users/register	
	@PostMapping("/register")
	public ResponseEntity<?> registerUser(@RequestBody User user) {
        try {
            User savedUser = userService.registerUser(user);
            return new ResponseEntity<>("User " + savedUser.getUsername() + " registered successfully", HttpStatus.CREATED);
        } catch (IllegalStateException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

	
//http://localhost:8080/api/users/login
	@PostMapping("/login")
	public ResponseEntity<?> loginUser(@RequestBody User user) {
        try {
            boolean isAuthenticated = userService.authenticateUser(user.getUsername(), user.getPassword());
            if (isAuthenticated) {
                User authenticatedUser = userService.getUserByUsername(user.getUsername());
                return ResponseEntity.ok(authenticatedUser);
            } else {

                return ResponseEntity.status(401).body("Invalid username or password");
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Internal Server Error: " + e.getMessage());
        }
    }



//http://localhost:8080/api/users/{userId}/favorites/add/{mealId}
	@PostMapping("/{userId}/favorites/add/{mealId}")
	public ResponseEntity<?> addMealToFavorites(@PathVariable String userId, @PathVariable String mealId) {
        try {
            userService.addFavoriteMeal(userId, mealId);
            return new ResponseEntity<>("Meal added to favorites successfully", HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
	
	
///////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
//http://localhost:8080/api/users/{userId}/favorites/remove/{mealId}
	@DeleteMapping("/{userId}/favorites/remove/{mealId}")
	public ResponseEntity<?> removeMealFromFavorites(@PathVariable String userId, @PathVariable String mealId) {
        try {
            userService.removeFavoriteMeal(userId, mealId);
            return new ResponseEntity<>("Meal removed from favorites successfully", HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }


	
	
	
	
	
	
	

}
