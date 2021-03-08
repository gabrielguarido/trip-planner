package com.trip.planner.service;

import com.trip.planner.exception.ResourceNotFoundException;
import com.trip.planner.model.User;
import com.trip.planner.model.context.AuthenticationContext;
import com.trip.planner.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service class for authentication with business rules.
 *
 * @author Gabriel Oliveira
 */
@Service
public class AuthenticationService {
    private static final String INVALID_CREDENTIALS_MESSAGE = "Invalid credentials. Please provide a valid e-mail address and password";

    @Autowired
    private UserRepository userRepository;

    public User authenticate(AuthenticationContext authenticationContext) {
        return userRepository.findByEmailAndPassword(authenticationContext.getEmail(), authenticationContext.getPassword())
                .orElseThrow(() -> new ResourceNotFoundException(INVALID_CREDENTIALS_MESSAGE));
    }
}
