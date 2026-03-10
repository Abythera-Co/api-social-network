package com.network.apiSocialNetwork.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class PostResponseDTO {
    @NotNull(message = "Community ID is required")
    private Long id;
    @NotBlank(message = "User name is required")
    private String userName;
    @Size(max = 500, message = "Content must be less than 500 characters")
    private String content;
    @NotNull(message = "Likes count is required")
    private Integer likes;
    @NotNull(message = "Community ID is required")
    private Long communityId;

    public PostResponseDTO() {
    }

    public PostResponseDTO(Long id, String userName, String content, Integer likes, Long communityId) {
        this.id = id;
        this.userName = userName;
        this.content = content;
        this.likes = likes;
        this.communityId = communityId;
    }

    public Long getId() { return id; }
    public String getUserName() { return userName; }
    public String getContent() { return content; }
    public Integer getLikes() { return likes; }
    public Long getCommunityId() { return communityId; }
}
