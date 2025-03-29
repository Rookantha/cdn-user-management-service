package com.contentnexus.user.management.service.controller;


import com.contentnexus.user.management.service.model.UserProfile;
import com.contentnexus.user.management.service.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/me")
    public ResponseEntity<?> getUserProfile(@AuthenticationPrincipal Jwt jwt) {
        String userId = jwt.getClaim("sub");
        String email = jwt.getClaim("email");

        UserProfile userProfile = userService.getCurrentUserProfile(userId);
        if (userProfile == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", "User not found"));
        }

        return ResponseEntity.ok(userProfile);
    }

    // Save  user profile
    @PostMapping("/save")
    public UserProfile saveUser(@AuthenticationPrincipal Jwt jwt, @RequestBody UserProfile user) {
        String userId = jwt.getClaim("sub");  // Get IAM User ID
        user.setUserId(userId);
        return userService.saveUser(user);
    }
    @PutMapping("/{userId}")
    public ResponseEntity<UserProfile> updateUser(@AuthenticationPrincipal Jwt jwt,
                                                  @PathVariable String userId,
                                                  @RequestBody UserProfile userProfile) {
        // Ensure the authenticated user is updating their own profile
        String authenticatedUserId = jwt.getClaim("sub");
        if (!authenticatedUserId.equals(userId)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body(null); // or return an error message
        }

        UserProfile updatedUser = userService.updateUser(userId, userProfile);
        return ResponseEntity.ok(updatedUser);
    }

}
