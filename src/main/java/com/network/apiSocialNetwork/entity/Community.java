package com.network.apiSocialNetwork.entity;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.List;

@Entity
@Table(name = "community")
public class Community {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @JsonManagedReference
    @OneToMany(mappedBy = "community", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Post> posts;

    public Community() {
    }

    public Community(String name) {
        this.name = name;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public List<Post> getPosts() { return posts; }

    public void setName(String name) { this.name = name; }
    public void setPosts(Post post) {
        this.posts.add(post);
        post.setCommunity(this);
    }
}
