package com.sabanciuniv;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.client.MongoClients;
import com.sabanciuniv.model.Category;
import com.sabanciuniv.model.Comment;
import com.sabanciuniv.model.Meal;
import com.sabanciuniv.model.User;
import com.sabanciuniv.repo.CategoryRepository;
import com.sabanciuniv.repo.CommentRepository;
import com.sabanciuniv.repo.MealRepository;
import com.sabanciuniv.repo.UserRepository;

@SpringBootApplication	
public class MealsAndRecipesApplicationProjectApplication{
	
	public static void main(String[] args) {
        SpringApplication.run(MealsAndRecipesApplicationProjectApplication.class, args);
    }
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private MealRepository mealRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private CommentRepository commentRepository;
/*
	 @Override
	    public void run(String... args) {
		 // Create categories
		 // Create categories
	        Category italian = new Category("Italian");
	        Category french = new Category("French");
	        Category turkish = new Category("Turkish");

	       

	        // Create meals and add them to categories
	        Meal pizza = new Meal("Margherita Pizza", "Classic with tomato sauce, mozzarella, basil, and olive oil.");
	        Meal carbonara = new Meal("Spaghetti Carbonara", "Traditional Italian pasta dish with eggs, cheese, pancetta, and black pepper.");
	        Meal coqAuVin = new Meal("Coq au Vin", "French stew made with chicken, red wine, mushrooms, and garlic.");
	        Meal ratatouille = new Meal("Ratatouille", "Stewed vegetable dish with eggplant, zucchini, bell peppers, and tomato.");
	        Meal sucukluYumurta = new Meal("Sucuklu Yumurta", "Turkish dish with sucuk and eggs.");
	        Meal makarna = new Meal("Makarna", "Boiled pasta with a choice of sauce.");

	        
	        // Create comments and associate with meals
	        Comment comment1 = new Comment("Delicious and classic!");
	        Comment comment2 = new Comment("Rich in flavor and very comforting.");

	        
	        // Save comments to capture generated IDs
	        comment1 = commentRepository.save(comment1);
	        comment2 = commentRepository.save(comment2);
	        

	        // Add comments to meals
	        pizza.getComments().add(comment1);
	        carbonara.getComments().add(comment2);
	        coqAuVin.getComments().add(comment1);
	        ratatouille.getComments().add(comment2);
	        sucukluYumurta.getComments().add(comment1);
	        makarna.getComments().add(comment2);

	        // Save updated meals
	        // Associate meals with categories
	        italian.getMeals().add(pizza);
	        italian.getMeals().add(carbonara);
	        french.getMeals().add(coqAuVin);
	        french.getMeals().add(ratatouille);
	        turkish.getMeals().add(sucukluYumurta);
	        turkish.getMeals().add(makarna);
	        
	        // Save meals to capture generated IDs
	        pizza = mealRepository.save(pizza);
	        carbonara = mealRepository.save(carbonara);
	        coqAuVin = mealRepository.save(coqAuVin);
	        ratatouille = mealRepository.save(ratatouille);
	        sucukluYumurta = mealRepository.save(sucukluYumurta);
	        makarna = mealRepository.save(makarna);

	       

	        // Save categories to capture generated IDs
	        italian = categoryRepository.save(italian);
	        french = categoryRepository.save(french);
	        turkish = categoryRepository.save(turkish);

	        // Create users
	        User user1 = new User("alice", "password123");
	        User user2 = new User("bob", "password321");
	        
	        // Save users
	        userRepository.save(user1);
	        userRepository.save(user2);

	        
	        


	        System.out.println("Dummy data inserted successfully.");
		
	
	}
	*/
}