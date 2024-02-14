package com.sabanciuniv.repo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.sabanciuniv.model.Comment;

@Repository
public interface CommentRepository extends MongoRepository<Comment, String> {
	List<Comment> findByUsername(String username);
	boolean existsByCommentText(String commentText);
	
	


	
}