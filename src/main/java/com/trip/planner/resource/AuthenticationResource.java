package com.trip.planner.resource;

import com.trip.planner.model.User;
import com.trip.planner.model.context.AuthenticationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

/**
 * Define all the resources that will be exposed by the Controller classes for login and authentication.
 *
 * @author Gabriel Oliveira
 */
public interface AuthenticationResource {
    @PostMapping
    ResponseEntity<User> login(@RequestBody @Valid AuthenticationContext authenticationContext);
}
