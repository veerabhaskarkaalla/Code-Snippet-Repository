package com.snippets.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "snippets")
public class Snippet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String title;
    
    private String description;
    
    @Column(columnDefinition = "TEXT", nullable = false)
    private String code;
    
    @Column(nullable = false)
    private String language;
    
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
    // Temporary: User relationship optional ga chesi test cheyali
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user; // Remove nullable=false temporary ga
    
    // Constructors
    public Snippet() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }
    
    public Snippet(String title, String description, String code, String language) {
        this();
        this.title = title;
        this.description = description;
        this.code = code;
        this.language = language;
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getTitle() { return title; }
    public void setTitle(String title) { 
        this.title = title; 
        this.updatedAt = LocalDateTime.now();
    }
    
    public String getDescription() { return description; }
    public void setDescription(String description) { 
        this.description = description; 
        this.updatedAt = LocalDateTime.now();
    }
    
    public String getCode() { return code; }
    public void setCode(String code) { 
        this.code = code; 
        this.updatedAt = LocalDateTime.now();
    }
    
    public String getLanguage() { return language; }
    public void setLanguage(String language) { 
        this.language = language; 
        this.updatedAt = LocalDateTime.now();
    }
    
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
    
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
}