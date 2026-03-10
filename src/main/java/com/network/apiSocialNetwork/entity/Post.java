package com.network.apiSocialNetwork.entity;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String userName;
    private String content;
    private Integer likes;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "community_id", nullable = false)
    private Community community;

    public Post() {
    }

    public Post(String name, String content, Integer likes, Community community) {
        this.userName = name;
        this.content = content;
        this.likes = likes;
        this.community = community;
    }

    public Long getId() { return id; }
    public String getUserName() { return userName; }
    public String getContent() { return content; }
    public Integer getLikes() { return likes; }
    public Community getCommunity() { return community; }

    public void setUserName(String userName) { this.userName = userName; }
    public void setContent(String content) { this.content = content; }
    public void setLikes(Integer likes) { this.likes = likes; }
    public void setCommunity(Community community) { this.community = community; }
}
