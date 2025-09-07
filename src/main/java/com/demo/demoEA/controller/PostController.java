package com.demo.demoEA.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.demo.demoEA.model.Post;
import com.demo.demoEA.model.User;
import com.demo.demoEA.repository.PostRepository;
import com.demo.demoEA.repository.UserRepository;
import com.demo.demoEA.repository.dto.PostRequest;
import com.demo.demoEA.repository.dto.PostResponse;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/posts")
public class PostController {
private final PostRepository postRepo;
private final UserRepository userRepo;

//constructor to initialize
public PostController(PostRepository postRepo, UserRepository userRepo) {
	this.postRepo = postRepo;
	this.userRepo = userRepo;
}

private String currentEmail() {
 return SecurityContextHolder.getContext().getAuthentication().getName();
}

private PostResponse toResponse(Post p) {
    return new PostResponse(
        p.getId(),
        p.getUser().getId(),
        p.getUser().getName()
    );
    
}
@PostMapping
public PostResponse create(@Valid @RequestBody PostRequest req) {
    User me = userRepo.findByEmail(currentEmail()).orElseThrow();
    Post p = new Post();
    p.setUser(me);
    p.setContent(req.content());
    return toResponse(postRepo.save(p));
}

@GetMapping
public Page<PostResponse> list(@RequestParam(defaultValue="0") int page,
                               @RequestParam(defaultValue="10") int size) {
    return postRepo.findAll(PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdAt")))
                   .map(this::toResponse);
}

@PutMapping("/{id}")
public ResponseEntity<PostResponse> update(@PathVariable Long id, @Valid @RequestBody PostRequest req) {
    User me = userRepo.findByEmail(currentEmail()).orElseThrow();
    var opt = postRepo.findById(id);
    if (opt.isEmpty()) return ResponseEntity.notFound().build();
    var p = opt.get();
    if (!p.getUser().getId().equals(me.getId())) return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    p.setContent(req.content());
    return ResponseEntity.ok(toResponse(postRepo.save(p)));
}

@DeleteMapping("/{id}")
public ResponseEntity<Void> delete(@PathVariable Long id) {
    User me = userRepo.findByEmail(currentEmail()).orElseThrow();
    var opt = postRepo.findById(id);
    if (opt.isEmpty()) return ResponseEntity.notFound().build();
    var p = opt.get();
    if (!p.getUser().getId().equals(me.getId())) return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    postRepo.delete(p);
    return ResponseEntity.noContent().build();
}
}

