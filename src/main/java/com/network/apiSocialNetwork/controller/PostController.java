package com.network.apiSocialNetwork.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// import com.network.apiSocialNetwork.entity.Community;
// import com.network.apiSocialNetwork.service.CommunityService;
import com.network.apiSocialNetwork.dto.PostRequestDTO;
import com.network.apiSocialNetwork.dto.PostResponseDTO;
import com.network.apiSocialNetwork.entity.Post;
import com.network.apiSocialNetwork.service.PostService;
import org.springframework.data.domain.Pageable;

import jakarta.validation.Valid;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/post-controller")
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/create-post")
    public ResponseEntity<Post> createPost(@Valid @RequestBody PostRequestDTO request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
            postService.createPost(
            request.getUserName(), 
            request.getContent(), 
            request.getLikes(),
            request.getCommunityId()
        ));
    }

    @GetMapping("/list-posts")
    public ResponseEntity<List<PostResponseDTO>> listPosts(Pageable pageable) {
        List<Post> posts = postService.getAllPosts(pageable);
        List<PostResponseDTO> response = new ArrayList<>();
        for (Post post : posts) {
            response.add (new PostResponseDTO(
                post.getId(),
                post.getUserName(),
                post.getContent(),
                post.getLikes(),
                post.getCommunity().getId()
            ));
        }
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete-post-by-id")
    public ResponseEntity<Void> deletePost(@RequestBody Long id) {
        postService.deletePost(id);
        return ResponseEntity.noContent().build();
    }
}
