package com.trip.planner.controller;

import com.trip.planner.model.User;
import com.trip.planner.model.context.AuthenticationContext;
import com.trip.planner.resource.AuthenticationResource;
import com.trip.planner.service.AuthenticationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Expose the API endpoints for login and authentication resources.
 *
 * @author Gabriel Oliveira
 */
@RestController
@Api(value = "Authentication REST API")
@RequestMapping(value = "auth", produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthenticationController implements AuthenticationResource {
    @Autowired
    private AuthenticationService authenticationService;

    @ApiOperation(value = "Authenticate user")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Invalid credentials"),
            @ApiResponse(code = 500, message = "Something Unexpected Happened")
    })
    public ResponseEntity<User> login(@RequestBody @Valid AuthenticationContext authenticationContext) {
        return ResponseEntity.ok(authenticationService.authenticate(authenticationContext));
    }
}
