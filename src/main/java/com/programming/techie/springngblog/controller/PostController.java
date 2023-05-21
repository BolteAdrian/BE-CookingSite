package com.programming.techie.springngblog.controller;

import com.programming.techie.springngblog.dto.PostDto;
import com.programming.techie.springngblog.security.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts/")
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping
    public ResponseEntity createPost(@RequestBody PostDto postDto) {
        postService.createPost(postDto);
        return new ResponseEntity("The post was created successfully",HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deletePost(@PathVariable @RequestBody Long id) {
        postService.deletePost(id);
        return new ResponseEntity<>("The post was deleted successfully",HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<PostDto>> showAllPosts() {
        return new ResponseEntity<>(postService.showAllPosts(), HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<PostDto>> showAllPostsPaginated(
            @RequestParam(required = false) String searchTerm,
            @RequestParam(defaultValue = "0") int pageNo,
            @RequestParam(defaultValue = "10") int pageSize) {

        return new ResponseEntity<>(postService.showAllPostsPaginated(searchTerm, pageNo, pageSize), HttpStatus.OK);
    }

    @GetMapping("/category")
    public ResponseEntity<List<PostDto>> showAllPostsByCategory(
            @RequestParam(required = false) String searchTerm,
            @RequestParam(defaultValue = "0") int pageNo,
            @RequestParam(defaultValue = "10") int pageSize) {

        return new ResponseEntity<>(postService.showAllPostsByCategory(searchTerm, pageNo, pageSize), HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<PostDto> getSinglePost(@PathVariable @RequestBody Long id) {
        return new ResponseEntity<>(postService.readSinglePost(id), HttpStatus.OK);
    }

    @PutMapping ("/{id}")
    public ResponseEntity<PostDto> editPost(@PathVariable @RequestBody Long id, @RequestBody PostDto postDto) {
        postService.editPost(id,postDto);
        return new ResponseEntity("The post was updated successfully",HttpStatus.OK);
    }
}
