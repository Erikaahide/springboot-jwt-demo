package com.demo.demoEA.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.demoEA.model.User;
import com.demo.demoEA.repository.UserRepository;
import com.demo.demoEA.repository.dto.AuthResponse;
import com.demo.demoEA.repository.dto.LoginRequest;
import com.demo.demoEA.repository.dto.RegisterRequest;
import com.demo.demoEA.security.JwtUtil;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {
private final UserRepository userRepo;
private final PasswordEncoder encoder;
private final AuthenticationManager authManager;
private final JwtUtil jwt;


public AuthController(UserRepository userRepo, PasswordEncoder encoder, AuthenticationManager authManager,
		JwtUtil jwt) {
	this.userRepo = userRepo;
	this.encoder = encoder;
	this.authManager = authManager;
	this.jwt = jwt;
}

@PostMapping("/register")
public ResponseEntity<AuthResponse> register(@Valid @RequestBody RegisterRequest req) {
  if (userRepo.findByEmail(req.email()).isPresent()) {
	  return ResponseEntity.status(HttpStatus.CONFLICT).body(new AuthResponse("Email already registred"));
  }
  User u = new User();
  u.setName(req.name());
  u.setEmail(req.email());
  u.setPassword(encoder.encode(req.password()));
  userRepo.save(u);

  String token = jwt.generateToken(u.getEmail());
  return ResponseEntity.ok(new AuthResponse(token));
}

@PostMapping("/login")
public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest req) {
  authManager.authenticate(new UsernamePasswordAuthenticationToken(req.email(), req.password()));
  String token = jwt.generateToken(req.email());
  return ResponseEntity.ok(new AuthResponse(token));
}
}

