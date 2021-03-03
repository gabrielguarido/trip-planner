package com.trip.planner.model.context;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class AuthenticationContext {
    @NotEmpty
    private String email;

    @NotEmpty
    private String password;
}
