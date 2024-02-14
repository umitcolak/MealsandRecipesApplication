package com.android.cookbook.model;

import com.google.gson.annotations.SerializedName;

import java.time.LocalDate;


public class BaseResponse {
    private String commentId;

    private String userId;
    private String username;
    private String commentText;
    private LocalDate commentDate;

    //Constructors
    public BaseResponse() {

    }

    public BaseResponse(String commentId, String userId, String commentText, LocalDate commentDate) {
        super();
        this.commentId = commentId;
        this.userId = userId;
        this.commentText = commentText;
        this.commentDate = commentDate;
    }

    public BaseResponse(String commentText) {
        super();
        this.commentText = commentText;
    }

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
