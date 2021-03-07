package com.trip.planner.service;

import com.trip.planner.exception.PayloadValidationException;
import com.trip.planner.exception.ResourceNotFoundException;
import com.trip.planner.model.Expense;
import com.trip.planner.model.Plan;
import com.trip.planner.model.context.ExpenseCreationContext;
import com.trip.planner.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.trip.planner.util.ContextUtil.buildExpense;

/**
 * Service class for {@link Expense}s with business rules.
 *
 * @author Gabriel Oliveira
 */
@Service
public class ExpenseService {
    private static final String INVALID_AMOUNT_MESSAGE = "The given amount: %s is invalid. Please provide a positive amount";
    private static final String INVALID_ID_MESSAGE = "The given expenseId: %s was not found, please provide a valid ID";

    @Autowired
    private ExpenseRepository expenseRepository;

    @Autowired
    private PlanService planService;

    public Expense create(ExpenseCreationContext expenseCreationContext) {
        if (invalidAmount(expenseCreationContext.getAmount())) {
            String errorMessage = String.format(INVALID_AMOUNT_MESSAGE, expenseCreationContext.getAmount());
            throw new PayloadValidationException(errorMessage);
        }

        // Validate given planId
        Plan plan = planService.verifyIfExists(expenseCreationContext.getPlanId());

        // Persist entity
        Expense savedExpense = expenseRepository.save(buildExpense(expenseCreationContext));

        // Relate saved expense with plan
        planService.saveExpense(plan, savedExpense);

        return savedExpense;
    }

    public void delete(Integer expenseId) {
        expenseRepository.delete(verifyIfExists(expenseId));
    }

    public Expense verifyIfExists(Integer expenseId) {
        String errorMessage = String.format(INVALID_ID_MESSAGE, expenseId);
        return expenseRepository.findById(expenseId).orElseThrow(() -> new ResourceNotFoundException(errorMessage));
    }

    private boolean invalidAmount(double amount) {
        return amount <= 0;
    }
}
