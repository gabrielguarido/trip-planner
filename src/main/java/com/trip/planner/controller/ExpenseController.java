package com.trip.planner.controller;

import com.trip.planner.model.Expense;
import com.trip.planner.model.context.ExpenseCreationContext;
import com.trip.planner.resource.ExpenseResource;
import com.trip.planner.service.ExpenseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Expose the API endpoints for {@link Expense} resources.
 *
 * @author Gabriel Oliveira
 */
@RestController
@Api(value = "Expense REST API")
@RequestMapping(value = "expense", produces = MediaType.APPLICATION_JSON_VALUE)
public class ExpenseController implements ExpenseResource {
    @Autowired
    private ExpenseService expenseService;

    @ApiOperation(value = "Create new expense")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Invalid payload"),
            @ApiResponse(code = 500, message = "Something Unexpected Happened")
    })
    public ResponseEntity<Expense> create(@RequestBody @Valid ExpenseCreationContext expenseCreationContext) {
        return ResponseEntity.ok(expenseService.create(expenseCreationContext));
    }

    @ApiOperation(value = "Delete expense")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Invalid expenseId"),
            @ApiResponse(code = 500, message = "Something Unexpected Happened")
    })
    public void delete(@PathVariable Integer expenseId) {
        expenseService.delete(expenseId);
    }
}
