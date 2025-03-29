package com.contentnexus.user.management.service.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Map;

@Document(collection = "users")
public class UserProfile {
    @Id
    private String id;
    private String userId;
    private String email;
    private String phone;
    private String firstName;
    private String lastName;
    private String username;
    private String role;

    // User Preferences (key-value pairs)
    private Map<String, String> preferences;

    // Subscription Details
    private String subscriptionPlan;
    private LocalDateTime subscriptionStartDate;
    private LocalDateTime subscriptionEndDate;

    // Metadata
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // Default constructor
    public UserProfile() {}

    // Parameterized constructor
    public UserProfile(String id, String userId, String email, String phone, String firstName, String lastName,
                       String username, String role, Map<String, String> preferences, String subscriptionPlan,
                       LocalDateTime subscriptionStartDate, LocalDateTime subscriptionEndDate,
                       LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.userId = userId;
        this.email = email;
        this.phone = phone;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.role = role;
        this.preferences = preferences;
        this.subscriptionPlan = subscriptionPlan;
        this.subscriptionStartDate = subscriptionStartDate;
        this.subscriptionEndDate = subscriptionEndDate;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Map<String, String> getPreferences() {
        return preferences;
    }

    public void setPreferences(Map<String, String> preferences) {
        this.preferences = preferences;
    }

    public String getSubscriptionPlan() {
        return subscriptionPlan;
    }

    public void setSubscriptionPlan(String subscriptionPlan) {
        this.subscriptionPlan = subscriptionPlan;
    }

    public LocalDateTime getSubscriptionStartDate() {
        return subscriptionStartDate;
    }

    public void setSubscriptionStartDate(LocalDateTime subscriptionStartDate) {
        this.subscriptionStartDate = subscriptionStartDate;
    }

    public LocalDateTime getSubscriptionEndDate() {
        return subscriptionEndDate;
    }

    public void setSubscriptionEndDate(LocalDateTime subscriptionEndDate) {
        this.subscriptionEndDate = subscriptionEndDate;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    // toString method
    @Override
    public String toString() {
        return "UserProfile{" +
                "id='" + id + '\'' +
                ", userId='" + userId + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", username='" + username + '\'' +
                ", role='" + role + '\'' +
                ", preferences=" + preferences +
                ", subscriptionPlan='" + subscriptionPlan + '\'' +
                ", subscriptionStartDate=" + subscriptionStartDate +
                ", subscriptionEndDate=" + subscriptionEndDate +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
