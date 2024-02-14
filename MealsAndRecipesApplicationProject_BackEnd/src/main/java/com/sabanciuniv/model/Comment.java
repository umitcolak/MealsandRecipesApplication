package com.sabanciuniv.model;

import java.sql.Date;
import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Comment {
	@Id
	private String commentId;
	
	private String userId;
	private String username;
	private String commentText;
	private LocalDate commentDate;
	
	
//Constructors	
	public Comment() {
		
	}
	
	public Comment(String commentId, String userId, String commentText, LocalDate commentDate) {
		super();
		this.commentId = commentId;
		this.userId = userId;
		this.commentText = commentText;
		this.commentDate = commentDate;
	}
	
	public Comment(String commentText) {
		super();
		this.commentText = commentText;
	}

//Getters&Setters


	
	
//Overrides

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}

	public String getCommentId() {
		return commentId;
	}

	public void setCommentId(String commentId) {
		this.commentId = commentId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getCommentText() {
		return commentText;
	}

	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}

	public LocalDate getCommentDate() {
        return commentDate != null ? commentDate : LocalDate.now(); // Return current date if null
    }

	public void setCommentDate(LocalDate commentDate) {
		this.commentDate = commentDate;
	}



}
