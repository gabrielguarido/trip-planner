package com.trip.planner.model.context;

import com.trip.planner.enumerator.Currency;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class ExpenseCreationContext {
    @NotNull
    private Integer planId;

    @NotEmpty
    @Size(max = 120)
    private String description;

    @NotNull
    private Double amount;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Currency currency;
}
