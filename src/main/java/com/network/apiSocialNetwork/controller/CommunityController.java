package com.network.apiSocialNetwork.controller;

import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;

import com.network.apiSocialNetwork.entity.Community;
import com.network.apiSocialNetwork.service.CommunityService;
import com.network.apiSocialNetwork.dto.CommunityRequestDTO;
import com.network.apiSocialNetwork.dto.CommunityResponseDTO;

import jakarta.validation.Valid;

import java.util.List;
import java.util.ArrayList;

@RestController
@RequestMapping("/community-controller")
public class CommunityController {
    private final CommunityService communityService;

    public CommunityController(CommunityService communityService) {
        this.communityService = communityService;
    }

    @PostMapping("/create-community")
    public ResponseEntity<Community> creatCommunity(@Valid @RequestBody CommunityRequestDTO communityRequestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(communityService.createCommunity(communityRequestDTO.getName()));
    }

    @PostMapping("/get-community-by-name/{name}")
    public ResponseEntity<CommunityResponseDTO> getCommunityByName(@PathVariable String name) {
        List<Community> communities = communityService.getAllCommunities(Pageable.unpaged());
        for (Community community : communities) {
            if (community.getName().equalsIgnoreCase(name)) {
                return ResponseEntity.ok(new CommunityResponseDTO(community.getId(), community.getName()));
            }
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/list-communities")
    public ResponseEntity<List<CommunityResponseDTO>> listCommunities(@PageableDefault(size=10) Pageable pageable) {
        List<Community> communities = communityService.getAllCommunities(pageable);
        List<CommunityResponseDTO> response = new ArrayList<>();
        for (Community community : communities) {
            response.add(new CommunityResponseDTO(
                community.getId(),
                community.getName()
            ));
        }
        return ResponseEntity.ok(response);
    }
    
    @DeleteMapping("/delete-community-by-id")
    public ResponseEntity<Void> deleteCommunity(@RequestBody Long id) {
        communityService.deleteCommunity(id);
        return ResponseEntity.noContent().build();
    }
}
