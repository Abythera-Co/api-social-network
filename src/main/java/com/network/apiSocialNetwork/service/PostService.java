package com.network.apiSocialNetwork.service;

import com.network.apiSocialNetwork.entity.Community;
import com.network.apiSocialNetwork.repository.CommunityRepository;
import com.network.apiSocialNetwork.entity.Post;
import com.network.apiSocialNetwork.repository.PostRepository;

import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;

import java.util.List;

@Service
public class PostService {
    private final PostRepository postRepository;
    private final CommunityRepository communityRepository;
    
    public PostService(PostRepository postRepository, CommunityRepository communityRepository) {
        this.postRepository = postRepository;
        this.communityRepository = communityRepository;
    }

    public Post createPost(String userName, String content, Integer like, Long communityId) {
        Community community = communityRepository.findById(communityId).orElseThrow(() -> new RuntimeException("Community not found"));
        Post post = new Post(userName, content, like, community);
        return postRepository.save(post);
    }

    public List<Post> getAllPosts(Pageable pageable) {
        return postRepository.findAll(pageable).getContent();
    }

    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }

    public List<Post> getPostsOrderByLikes() {
        List<Post> posts = postRepository.findAll();
        posts.sort((p1, p2) -> p2.getLikes().compareTo(p1.getLikes()));
        return posts;
    }
}
