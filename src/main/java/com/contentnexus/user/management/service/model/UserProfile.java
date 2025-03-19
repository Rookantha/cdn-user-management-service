package com.contentnexus.user.management.service.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Map;

@Document(collection = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
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
}
