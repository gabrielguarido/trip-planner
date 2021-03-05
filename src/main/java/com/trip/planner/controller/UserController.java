package com.trip.planner.controller;

import com.trip.planner.model.User;
import com.trip.planner.model.context.UserCreationContext;
import com.trip.planner.resource.UserResource;
import com.trip.planner.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Expose the API endpoints for {@link User} resources.
 *
 * @author Gabriel Oliveira
 */
@RestController
@Api(value = "Users REST API")
@RequestMapping(value = "user", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController implements UserResource {
    @Autowired
    private UserService userService;

    @ApiOperation(value = "Register new user")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Invalid payload"),
            @ApiResponse(code = 500, message = "Something Unexpected Happened")
    })
    public ResponseEntity<User> register(@RequestBody @Valid UserCreationContext userCreationContext) {
        return new ResponseEntity<>(userService.register(userCreationContext), HttpStatus.OK);
    }
}
