package com.contentnexus.user.management.service.service;

import com.contentnexus.user.management.service.model.UserProfile;
import com.contentnexus.user.management.service.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserService {
    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserProfile getOrCreateUserProfile(String userId, String email, String username,
                                              String firstName, String lastName, String role) {
        log.info("🔍 Checking if user with ID {} exists", userId);

        return userRepository.findByUserId(userId)
                .orElseGet(() -> {
                    log.info("⚡ Creating new user profile for ID: {}", userId);
                    UserProfile newUser = new UserProfile();
                    newUser.setUserId(userId);
                    newUser.setEmail(email);
                    newUser.setUsername(username);
                    newUser.setFirstName(firstName);
                    newUser.setLastName(lastName);
                    newUser.setRole(role);
                    newUser.setCreatedAt(LocalDateTime.now());
                    newUser.setUpdatedAt(LocalDateTime.now());

                    UserProfile savedUser = userRepository.save(newUser);
                    log.info("✅ UserProfile saved: {}", savedUser);
                    return savedUser;
                });
    }

    public UserProfile getCurrentUserProfile(String userId) {
        log.info("Fetching user profile for ID: {}", userId);
        return userRepository.findByUserId(userId)
                .orElseThrow(() -> {
                    log.warn("User with ID {} not found in MongoDB", userId);
                    return new ResponseStatusException(HttpStatus.NOT_FOUND, "User with ID " + userId + " not found in MongoDB");
                });
    }

    public Optional<UserProfile> getUserById(String userId) {
        log.info("Retrieving user by ID: {}", userId);
        return userRepository.findByUserId(userId);
    }

    public UserProfile saveUser(UserProfile user) {
        log.info("Saving user: {}", user.getUserId());

        // Ensure createdAt is set only once
        if (user.getCreatedAt() == null) {
            user.setCreatedAt(LocalDateTime.now());
        }

        user.setUpdatedAt(LocalDateTime.now());
        return userRepository.save(user);
    }


    public UserProfile updateUser(String userId, UserProfile updatedProfile) {
        return userRepository.findByUserId(userId)
                .map(existingUser -> {
                    existingUser.setEmail(updatedProfile.getEmail());
                    existingUser.setPhone(updatedProfile.getPhone());
                    existingUser.setFirstName(updatedProfile.getFirstName());
                    existingUser.setLastName(updatedProfile.getLastName());
                    existingUser.setUsername(updatedProfile.getUsername());
                    existingUser.setRole(updatedProfile.getRole());
                    existingUser.setPreferences(updatedProfile.getPreferences());
                    existingUser.setSubscriptionPlan(updatedProfile.getSubscriptionPlan());

                    if (updatedProfile.getSubscriptionStartDate() != null) {
                        existingUser.setSubscriptionStartDate(updatedProfile.getSubscriptionStartDate());
                    }
                    if (updatedProfile.getSubscriptionEndDate() != null) {
                        existingUser.setSubscriptionEndDate(updatedProfile.getSubscriptionEndDate());
                    }

                    // 🔥 Preserve createdAt if it already exists
                    if (existingUser.getCreatedAt() == null) {
                        existingUser.setCreatedAt(LocalDateTime.now());
                    }

                    existingUser.setUpdatedAt(LocalDateTime.now());

                    return userRepository.save(existingUser);
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
    }

}
