package com.network.apiSocialNetwork.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class PostRequestDTO {
    
    @NotBlank(message = "User name is required")
    private String userName;
    @Size(max = 500, message = "Content must be less than 500 characters")
    private String content;
    @NotNull(message = "Likes count is required")
    private Integer likes;
    @NotNull(message = "Community ID is required")
    private Long communityId;

    public PostRequestDTO() {
    }

    public String getUserName() { return userName; }
    public String getContent() { return content; }
    public Integer getLikes() { return likes; }
    public Long getCommunityId() { return communityId; }

    public void setUserName(String userName) { this.userName = userName; }
    public void setContent(String content) { this.content = content; }
    public void setLikes(Integer likes) { this.likes = likes; }
    public void setCommunityId(Long communityId) { this.communityId = communityId; }
}
