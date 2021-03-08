package com.trip.planner.model.context;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class AuthenticationContext {
    @Email
    @NotEmpty
    @Size(max = 140)
    private String email;

    @NotEmpty
    @Size(min = 4, max = 20)
    private String password;
}
