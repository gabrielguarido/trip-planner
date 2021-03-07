package com.trip.planner.util;

import com.trip.planner.model.Expense;
import com.trip.planner.model.Plan;
import com.trip.planner.model.User;
import com.trip.planner.model.context.ExpenseCreationContext;
import com.trip.planner.model.context.PlanCreationContext;
import com.trip.planner.model.context.UserCreationContext;

/**
 * Utility class for building entities based on context classes.
 *
 * @author Gabriel Oliveira
 */
public final class ContextUtil {
    private ContextUtil() {
    }

    public static User buildUser(UserCreationContext userCreationContext) {
        return User.builder()
                .firstName(userCreationContext.getFirstName())
                .lastName(userCreationContext.getLastName())
                .email(userCreationContext.getEmail())
                .password(userCreationContext.getPassword())
                .build();
    }

    public static Plan buildPlan(PlanCreationContext planCreationContext) {
        return Plan.builder()
                .countryName(planCreationContext.getCountryName())
                .departureDate(planCreationContext.getDepartureDate())
                .returnDate(planCreationContext.getReturnDate())
                .build();
    }

    public static Expense buildExpense(ExpenseCreationContext expenseCreationContext) {
        return Expense.builder()
                .description(expenseCreationContext.getDescription())
                .amount(expenseCreationContext.getAmount())
                .currency(expenseCreationContext.getCurrency())
                .build();
    }
}
