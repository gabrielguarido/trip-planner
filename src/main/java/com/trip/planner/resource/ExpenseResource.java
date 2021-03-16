package com.trip.planner.resource;

import com.trip.planner.model.Expense;
import com.trip.planner.model.context.ExpenseCreationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;

/**
 * Define all the {@link Expense} resources that will be exposed by the Controller classes.
 *
 * @author Gabriel Oliveira
 */
public interface ExpenseResource {
    @GetMapping("/plan/{planId}")
    ResponseEntity<List<Expense>> findAllByPlanId(@PathVariable Integer planId);

    @PostMapping
    ResponseEntity<Expense> create(@RequestBody @Valid ExpenseCreationContext expenseCreationContext);

    @DeleteMapping("/{expenseId}")
    void delete(@PathVariable Integer expenseId);
}
