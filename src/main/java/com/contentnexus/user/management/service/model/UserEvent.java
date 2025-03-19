package com.contentnexus.user.management.service.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserEvent {
    private String userId;
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private String role;
}
