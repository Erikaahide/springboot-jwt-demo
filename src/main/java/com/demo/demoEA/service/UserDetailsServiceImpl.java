package com.demo.demoEA.service;

import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.demo.demoEA.model.User;
import com.demo.demoEA.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
private final UserRepository userRepo;

//constructor to initialize userRepo and userDetailService
public UserDetailsServiceImpl(UserRepository userRepo) {
  this.userRepo = userRepo;
}

@Override
public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
 User u = userRepo.findByEmail(email)
   .orElseThrow(() -> new UsernameNotFoundException("No existe: " + email));
 return org.springframework.security.core.userdetails.User
   .withUsername(u.getEmail())
   .password(u.getPassword())
   .authorities(List.of(new SimpleGrantedAuthority("ROLE_USER")))
   .build();
}
}

