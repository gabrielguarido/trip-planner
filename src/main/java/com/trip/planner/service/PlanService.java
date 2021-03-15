package com.trip.planner.service;

import com.trip.planner.exception.PayloadValidationException;
import com.trip.planner.exception.ResourceNotFoundException;
import com.trip.planner.model.Expense;
import com.trip.planner.model.Plan;
import com.trip.planner.model.User;
import com.trip.planner.model.context.PlanCreationContext;
import com.trip.planner.repository.PlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

import static com.trip.planner.util.ContextUtil.buildPlan;

/**
 * Service class for {@link Plan}s with business rules.
 *
 * @author Gabriel Oliveira
 */
@Service
public class PlanService {
    private static final String OVERLAPPING_DATES_MESSAGE = "The given departure date: %s overlaps the return date: %s. Please inform a valid date range";
    private static final String INVALID_ID_MESSAGE = "The given planId: %s was not found, please provide a valid ID";

    @Autowired
    private PlanRepository planRepository;

    @Autowired
    private UserService userService;

    public List<Plan> findAllByUserId(Integer userId) {
        userService.verifyIfExists(userId);

        return planRepository.findAllByUserId(userId);
    }

    public Plan create(PlanCreationContext planCreationContext) {
        LocalDate departureDate = planCreationContext.getDepartureDate();
        LocalDate returnDate = planCreationContext.getReturnDate();

        if (datesAreOverlapping(departureDate, returnDate)) {
            String errorMessage = String.format(OVERLAPPING_DATES_MESSAGE, departureDate, returnDate);
            throw new PayloadValidationException(errorMessage);
        }

        // Validate given userId
        User user = userService.verifyIfExists(planCreationContext.getUserId());

        // Persist entity
        Plan savedPlan = planRepository.save(buildPlan(planCreationContext));

        // Relate saved plan with logged user
        userService.savePlan(user, savedPlan);

        return savedPlan;
    }

    public void delete(Integer planId) {
        planRepository.delete(verifyIfExists(planId));
    }

    public void saveExpense(Plan plan, Expense expense) {
        plan.getExpenseSet().add(expense);

        planRepository.save(plan);
    }

    public Plan verifyIfExists(Integer planId) {
        String errorMessage = String.format(INVALID_ID_MESSAGE, planId);
        return planRepository.findById(planId).orElseThrow(() -> new ResourceNotFoundException(errorMessage));
    }

    private boolean datesAreOverlapping(LocalDate departureDate, LocalDate returnDate) {
        return departureDate.isAfter(returnDate);
    }
}
