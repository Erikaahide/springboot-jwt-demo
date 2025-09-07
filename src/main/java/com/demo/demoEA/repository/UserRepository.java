package com.demo.demoEA.repository;

import org.springframework.stereotype.Repository;

import com.demo.demoEA.model.User;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	  Optional<User> findByEmail(String email);
}//interface UserRepository
