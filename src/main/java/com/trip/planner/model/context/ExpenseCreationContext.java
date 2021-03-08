package com.trip.planner.model.context;

import com.trip.planner.enumerator.Currency;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class ExpenseCreationContext {
    @NotNull
    private Integer planId;

    @NotEmpty
    private String description;

    @NotNull
    private Double amount;

    @NotNull
    private Currency currency;
}
