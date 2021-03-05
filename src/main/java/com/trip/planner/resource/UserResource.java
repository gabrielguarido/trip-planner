package com.trip.planner.resource;

import com.trip.planner.model.User;
import com.trip.planner.model.context.UserCreationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

/**
 * Define all the {@link User} resources that will be exposed by the Controller classes.
 *
 * @author Gabriel Oliveira
 */
public interface UserResource {
    @PostMapping
    ResponseEntity<User> register(@RequestBody @Valid UserCreationContext userCreationContext);
}
