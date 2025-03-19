package com.contentnexus.user.management.service.cosumer;

import com.contentnexus.user.management.service.model.UserEvent;
import com.contentnexus.user.management.service.model.UserProfile;
import com.contentnexus.user.management.service.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class UserEventConsumerService {
    private final UserRepository userRepository;
    private static final Logger logger = LoggerFactory.getLogger(UserEventConsumerService.class);

    public UserEventConsumerService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @KafkaListener(topics = "user-topic", groupId = "user-sync-group")
    public void consumeUserEvent(UserEvent userEvent) {
        logger.info("🔥 Consumed User Event from Kafka: {}", userEvent);

        // Check if user already exists in MongoDB
        if (userRepository.findByUserId(userEvent.getUserId()).isPresent()) {
            logger.warn("⚠️ User already exists in MongoDB with ID: {}", userEvent.getUserId());
            return;
        }

        // Create and save UserProfile
        UserProfile userProfile = new UserProfile();
        userProfile.setUserId(userEvent.getUserId()); // IAM ID
        userProfile.setUsername(userEvent.getUsername());
        userProfile.setEmail(userEvent.getEmail());
        userProfile.setFirstName(userEvent.getFirstName());
        userProfile.setLastName(userEvent.getLastName());
        userProfile.setRole(userEvent.getRole());

        userRepository.save(userProfile);
        logger.info("✅ UserProfile saved in MongoDB: {}", userProfile);
    }
}
