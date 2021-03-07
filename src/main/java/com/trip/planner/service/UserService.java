package com.trip.planner.service;

import com.trip.planner.exception.PayloadValidationException;
import com.trip.planner.exception.ResourceNotFoundException;
import com.trip.planner.model.Plan;
import com.trip.planner.model.User;
import com.trip.planner.model.context.UserCreationContext;
import com.trip.planner.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.trip.planner.util.ContextUtil.buildUser;

/**
 * Service class for {@link User}s with business rules.
 *
 * @author Gabriel Oliveira
 */
@Service
public class UserService {
    private static final String INVALID_EMAIL_MESSAGE = "The given e-mail: %s is already registered, please use another one";
    private static final String INVALID_ID_MESSAGE = "The given userId: %s was not found, please provide a valid ID";

    @Autowired
    private UserRepository userRepository;

    public User register(UserCreationContext userCreationContext) {
        if (emailIsTaken(userCreationContext.getEmail())) {
            String errorMessage = String.format(INVALID_EMAIL_MESSAGE, userCreationContext.getEmail());
            throw new PayloadValidationException(errorMessage);
        }

        return userRepository.save(buildUser(userCreationContext));
    }

    public void savePlan(User user, Plan plan) {
        user.getPlanSet().add(plan);

        userRepository.save(user);
    }

    public User verifyIfExists(Integer userId) {
        String errorMessage = String.format(INVALID_ID_MESSAGE, userId);
        return userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException(errorMessage));
    }

    private boolean emailIsTaken(String email) {
        return userRepository.findByEmail(email).isPresent();
    }
}
