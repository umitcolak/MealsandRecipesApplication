package com.sabanciuniv.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sabanciuniv.model.Comment;
import com.sabanciuniv.model.Meal;
import com.sabanciuniv.repo.CommentRepository;
import com.sabanciuniv.repo.MealRepository;

@Service
public class CommentService {
	private final CommentRepository commentRepository;
	private final MealRepository mealRepository;
	
	@Autowired
	public CommentService(CommentRepository commentRepository, MealRepository mealRepository) {
		this.commentRepository = commentRepository;
		this.mealRepository = mealRepository;
	}
	
	public Comment addComment(Comment comment) {
        comment.setCommentDate(LocalDate.now()); // Set the comment date to the current date
        return commentRepository.save(comment);
    }
	

	
	public Comment addCommentToMeal(String mealId, Comment comment) {
        comment.setCommentDate(LocalDate.now()); // Set the current date

        // Save the comment
        comment = commentRepository.save(comment);

        // Add the comment to the meal and save the meal
        Meal meal = mealRepository.findById(mealId).orElseThrow(() -> new RuntimeException("Meal not found with id: " + mealId));
        meal.getComments().add(comment);
        mealRepository.save(meal);

        return comment;
    }

	
	
}
