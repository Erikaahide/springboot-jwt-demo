package com.demo.demoEA.model;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.sql.Timestamp;

@Entity 
@Table(name="users")
public class User {
@Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
@Column(nullable=false, length=80) private String name;
@Column(nullable = false, unique = true, length = 120) private String email;
@Column(nullable=false, length=255) private String password; // BCrypt
@CreationTimestamp private Timestamp createdAt;

//empty constructor for method POST
	public User () {}
	
//parameters without id constructor 

public User(String name, String email, String password, Timestamp createdAt) {
	super();
	this.name = name;
	this.email = email;
	this.password = password;
	this.createdAt = createdAt;
}

//getters/setters
public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public String getEmail() {
	return email;
}

public void setEmail(String email) {
	this.email = email;
}

public String getPassword() {
	return password;
}

public void setPassword(String password) {
	this.password = password;
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

//toString without password to not show it
@Override
public String toString() {
	return "User [id=" + id + ", name=" + name + ", email=" + email + "]";
}


}//classUser

