package com.demo.demoEA.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.demoEA.model.Post;
import com.demo.demoEA.model.User;
import com.demo.demoEA.repository.PostRepository;
import com.demo.demoEA.repository.UserRepository;
import com.demo.demoEA.repository.dto.PostRequest;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {
private final PostRepository postRepo;
private final UserRepository userRepo;

private String currentEmail() {
 return SecurityContextHolder.getContext().getAuthentication().getName();
}

@PostMapping
public Post create(@Valid @RequestBody PostRequest req) {
 User me = userRepo.findByEmail(currentEmail()).orElseThrow();
 Post p = new Post();
 p.setUser(me);
 p.setContent(req.content());
 return postRepo.save(p);
}

@GetMapping public Page<Post> list(@RequestParam(defaultValue="0") int page,
                                  @RequestParam(defaultValue="10") int size) {
 return postRepo.findAll(PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdAt")));
}

@PutMapping("/{id}")
public ResponseEntity<Post> update(@PathVariable Long id, @Valid @RequestBody PostRequest req) {
 User me = userRepo.findByEmail(currentEmail()).orElseThrow();
 return postRepo.findById(id).map(p -> {
   if (!p.getUser().getId().equals(me.getId())) return new ResponseEntity<>(HttpStatus.FORBIDDEN);
   p.setContent(req.content());
   return ResponseEntity.ok(postRepo.save(p));
 }).orElse(ResponseEntity.notFound().build());
}

@DeleteMapping("/{id}")
public ResponseEntity<Void> delete(@PathVariable Long id) {
 User me = userRepo.findByEmail(currentEmail()).orElseThrow();
 return postRepo.findById(id).map(p -> {
   if (!p.getUser().getId().equals(me.getId())) return new ResponseEntity<>(HttpStatus.FORBIDDEN);
   postRepo.delete(p);
   return ResponseEntity.noContent().build();
 }).orElse(ResponseEntity.notFound().build());
}
}

