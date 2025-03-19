package com.contentnexus.user.management.service.repository;

import com.contentnexus.user.management.service.model.UserProfile;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<UserProfile, String> {
    Optional<UserProfile> findByUserId(String userId);  // Find user by IAM ID
}
