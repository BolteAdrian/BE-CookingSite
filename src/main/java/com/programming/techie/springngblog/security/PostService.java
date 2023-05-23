package com.programming.techie.springngblog.security;

import com.programming.techie.springngblog.dto.PostDto;
import com.programming.techie.springngblog.exception.PostNotFoundException;
import com.programming.techie.springngblog.model.Post;
import com.programming.techie.springngblog.repository.PostRepository;
import com.programming.techie.springngblog.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class PostService {

    @Autowired
    private AuthService authService;
    @Autowired
    private PostRepository postRepository;

    @Transactional
    public List<PostDto> showAllPosts() {
        List<Post> posts = postRepository.findAll();
        return posts.stream().map(this::mapFromPostToDto).collect(toList());
    }

    @Transactional
    public List<PostDto> showAllPostsPaginated(String searchTerm, int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Specification<Post> spec = getSearchSpecification(searchTerm);
        Page<Post> posts = postRepository.findAll(spec, pageable);
        return posts.stream().map(this::mapFromPostToDto).collect(toList());
    }

    private Specification<Post> getSearchSpecification(String searchTerm) {
        return (root, query, criteriaBuilder) -> {
            if (searchTerm == null || searchTerm.isEmpty()) {
                return criteriaBuilder.conjunction();
            }

            String likeSearchTerm = "%" + searchTerm.toLowerCase() + "%";
            return criteriaBuilder.or(
                    criteriaBuilder.like(criteriaBuilder.lower(root.get("title")), likeSearchTerm),
                    criteriaBuilder.like(criteriaBuilder.lower(root.get("content")), likeSearchTerm),
                    criteriaBuilder.like(criteriaBuilder.lower(root.get("category")), likeSearchTerm)
            );
        };
    }

    @Transactional
    public List<PostDto> showAllPostsByCategory(String searchTerm, int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Specification<Post> spec = getSearchCategory(searchTerm);
        Page<Post> posts = postRepository.findAll(spec, pageable);
        return posts.stream().map(this::mapFromPostToDto).collect(toList());
    }

    private Specification<Post> getSearchCategory(String searchTerm) {
        return (root, query, criteriaBuilder) -> {
            if (searchTerm == null || searchTerm.isEmpty()) {
                return criteriaBuilder.conjunction();
            }

            String likeSearchTerm = "%" + searchTerm.toLowerCase() + "%";
            return criteriaBuilder.or(
                    criteriaBuilder.like(criteriaBuilder.lower(root.get("category")), likeSearchTerm)
            );
        };
    }

    @Transactional
    public void createPost(PostDto postDto) {
        Post post = mapFromDtoToPost(postDto);
        postRepository.save(post);
    }

    @Transactional
    public PostDto readSinglePost(Long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new PostNotFoundException("For id " + id));
        return mapFromPostToDto(post);
    }

    private PostDto mapFromPostToDto(Post post) {
        PostDto postDto = new PostDto();
        postDto.setId(post.getId());
        postDto.setTitle(post.getTitle());
        postDto.setCategory(post.getCategory());
        postDto.setPicture(post.getPicture());
        postDto.setIngredients(post.getIngredients());
        postDto.setMethodOfPreparation(post.getMethodOfPreparation());
        postDto.setShortDescription(post.getShortDescription());
        postDto.setContent(post.getContent());
        postDto.setUsername(post.getUsername());
        return postDto;
    }

    private Post mapFromDtoToPost(PostDto postDto) {
        Post post = new Post();
        post.setTitle(postDto.getTitle());
        post.setCategory(postDto.getCategory());
        post.setPicture(postDto.getPicture());
        post.setIngredients(postDto.getIngredients());
        post.setMethodOfPreparation(postDto.getMethodOfPreparation());
        post.setShortDescription(postDto.getShortDescription());
        post.setContent(postDto.getContent());
        User loggedInUser = authService.getCurrentUser().orElseThrow(() -> new IllegalArgumentException("User Not Found"));
        post.setCreatedOn(Instant.now());
        post.setUsername(loggedInUser.getUsername());
        post.setUpdatedOn(Instant.now());
        return post;
    }

    @Transactional
    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }


    @Transactional
    public void editPost(Long id,PostDto postDto) {
        Post post = postRepository.findById(id).orElseThrow(() -> new PostNotFoundException("For id " + id));
        post = mapFromDtoToPost(postDto);
        post.setId(id);
        postRepository.save(post);
    }

}
