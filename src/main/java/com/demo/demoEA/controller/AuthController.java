package com.demo.demoEA.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.demoEA.repository.dto.RegisterRequest;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
private final UserRepository userRepo;
private final PasswordEncoder encoder;
private final AuthenticationManager authManager;
private final JwtUtil jwt;

@PostMapping("/register")
public ResponseEntity<?> register(@Valid @RequestBody RegisterRequest req) {
 if (userRepo.findByEmail(req.email()).isPresent())
   return ResponseEntity.status(HttpStatus.CONFLICT).body("Email ya registrado");
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
 return ResponseEntity.ok(new AuthResponse(jwt.generateToken(req.email())));
}
}

