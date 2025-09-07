package com.demo.demoEA.model;

import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Post {
@Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
@ManyToOne (optional=false) private User user; // every post has an author
@Column(nullable=false, columnDefinition="TEXT") private String content;
@CreationTimestamp private Timestamp createdAt;

//empty constructor for method POST
	public Post () {}

//parameters without id constructor 
	public Post(User user, String content, Timestamp createdAt) {
		super();
		this.user = user;
		this.content = content;
		this.createdAt = createdAt;
}

// getters/setters
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public Long getId() {
		return id;
	}
	
//toString without user bc 'user' has already a toString
	public String toString() {
		return "Post [id=" + id + ", content=" + content + ", createdAt=" + createdAt + "]";
	}
	
}//classPost

