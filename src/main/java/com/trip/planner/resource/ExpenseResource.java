package com.trip.planner.resource;

import com.trip.planner.model.Expense;
import com.trip.planner.model.context.ExpenseCreationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

/**
 * Define all the {@link Expense} resources that will be exposed by the Controller classes.
 *
 * @author Gabriel Oliveira
 */
public interface ExpenseResource {
    @PostMapping
    ResponseEntity<Expense> create(@RequestBody @Valid ExpenseCreationContext expenseCreationContext);
}
