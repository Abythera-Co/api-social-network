package com.network.apiSocialNetwork.dto;

public class CommunityResponseDTO {
    private Long id;
    private String name;

    public CommunityResponseDTO() {
    }

    public CommunityResponseDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }
    public Long getId() { return id; }
    public String getName() { return name; }
}
