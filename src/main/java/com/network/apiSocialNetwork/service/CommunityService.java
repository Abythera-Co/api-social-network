package com.network.apiSocialNetwork.service;

import com.network.apiSocialNetwork.entity.Community;
import com.network.apiSocialNetwork.entity.Post;
import com.network.apiSocialNetwork.repository.CommunityRepository;
import org.springframework.stereotype.Service;

import com.network.apiSocialNetwork.exception.ExistingCommunityException;

import java.util.List;

@Service
public class CommunityService {
    private final CommunityRepository communityRepository;

    public CommunityService(CommunityRepository communityRepository) {
        this.communityRepository = communityRepository;
    }

    public List<Community> getAllCommunities() {
        return communityRepository.findAll();
    }

    public Community getCommunityById(Long id) {
        return communityRepository.findById(id).orElse(null);
    }

    public Community createCommunity(String name) {
        List<Community> communities = getAllCommunities();
        for (Community community : communities) {
            if (community.getName().equalsIgnoreCase(name)) {
                throw new ExistingCommunityException("Community with the same name already exists");
            }
        }
        Community community = new Community(name);
        return communityRepository.save(community);
    }

    public void deleteCommunity(Long id) {
        communityRepository.deleteById(id);
    }

    public void addPostToCommunity(Long communityId, Post post) {
        Community community = communityRepository.findById(communityId).orElseThrow(() -> new RuntimeException("Community not found"));
        community.setPosts(post);
        communityRepository.save(community);
    }

    public List<Post> getPostsByCommunityId(Long communityId) {
        Community community = communityRepository.findById(communityId).orElseThrow(() -> new RuntimeException("Community not found"));
        return community.getPosts();
    }
}
