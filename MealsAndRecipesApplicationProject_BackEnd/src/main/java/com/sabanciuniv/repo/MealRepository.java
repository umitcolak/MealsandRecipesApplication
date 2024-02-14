package com.sabanciuniv.repo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.sabanciuniv.model.Meal;

@Repository
public interface MealRepository extends MongoRepository<Meal, String>{
    List<Meal> findByMealIdIn(List<String> mealIds); 
    boolean existsByMealName(String mealName);
}
