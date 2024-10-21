package org.group.koipondbackend.dto;

import java.time.LocalDateTime;

public class FeedbackDTO {

    private Long id;
    private Long customerId;
    private String content;
    private int rating;
    private LocalDateTime createdAt;

    // Getters and setters
    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getCustomerId() { return customerId; }
    public void setCustomerId(Long customerId) { this.customerId = customerId; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public int getRating() { return rating; }
    public void setRating(int rating) { this.rating = rating; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}