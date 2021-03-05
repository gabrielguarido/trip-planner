package com.trip.planner.service;

import com.trip.planner.exception.PayloadValidationException;
import com.trip.planner.model.User;
import com.trip.planner.model.context.UserCreationContext;
import com.trip.planner.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.trip.planner.util.UserUtil.buildUser;

/**
 * Service class for {@link User}s with business rules.
 *
 * @author Gabriel Oliveira
 */
@Service
public class UserService {
    private static final String INVALID_EMAIL = "The given e-mail: %s is already registered, please use another one";

    @Autowired
    private UserRepository userRepository;

    public User register(UserCreationContext userCreationContext) {
        if (emailIsTaken(userCreationContext.getEmail())) {
            String errorMessage = String.format(INVALID_EMAIL, userCreationContext.getEmail());
            throw new PayloadValidationException(errorMessage);
        }

        return userRepository.save(buildUser(userCreationContext));
    }

    private boolean emailIsTaken(String email) {
        return userRepository.findByEmail(email).isPresent();
    }
}
