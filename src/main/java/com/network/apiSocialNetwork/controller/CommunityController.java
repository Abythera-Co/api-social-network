package com.network.apiSocialNetwork.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.network.apiSocialNetwork.entity.Community;
import com.network.apiSocialNetwork.service.CommunityService;

import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/community-controller")
public class CommunityController {
    private final CommunityService communityService;

    public CommunityController(CommunityService communityService) {
        this.communityService = communityService;
    }

    @PostMapping("/create-community")
    public ResponseEntity<Community> creatCommunity(@Valid @RequestBody String name) {
        return ResponseEntity.status(HttpStatus.CREATED).body(communityService.createCommunity(name));
    }

    @GetMapping("/list-communities")
    public ResponseEntity<List<Community>> listCommunities() {
        return ResponseEntity.ok(communityService.getAllCommunities());
    }
    
    @DeleteMapping("/delete-community-by-id")
    public ResponseEntity<Void> deleteCommunity(@RequestBody Long id) {
        communityService.deleteCommunity(id);
        return ResponseEntity.noContent().build();
    }
}
