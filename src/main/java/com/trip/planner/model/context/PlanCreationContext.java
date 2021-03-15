package com.trip.planner.model.context;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
public class PlanCreationContext {
    @NotNull
    private Integer userId;

    @NotEmpty
    @Size(max = 100)
    private String countryName;

    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate departureDate;

    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate returnDate;
}
