package com.trip.planner.util;

import com.trip.planner.model.User;
import com.trip.planner.model.context.UserCreationContext;

/**
 * Utility class for {@link User}
 *
 * @author Gabriel Oliveira
 */
public final class UserUtil {
    private UserUtil() {
    }

    public static User buildUser(UserCreationContext userCreationContext) {
        return User.builder()
                .firstName(userCreationContext.getFirstName())
                .lastName(userCreationContext.getLastName())
                .email(userCreationContext.getEmail())
                .password(userCreationContext.getPassword())
                .build();
    }
}
