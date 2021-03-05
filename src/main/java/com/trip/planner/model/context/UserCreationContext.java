package com.trip.planner.model.context;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class UserCreationContext {
    @NotEmpty
    @Size(max = 40)
    private String firstName;

    @NotEmpty
    @Size(max = 40)
    private String lastName;

    @Email
    @NotEmpty
    @Size(max = 140)
    private String email;

    @NotEmpty
    private String password;
}
