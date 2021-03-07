package com.trip.planner.service;

import com.trip.planner.exception.PayloadValidationException;
import com.trip.planner.model.Plan;
import com.trip.planner.model.User;
import com.trip.planner.model.context.PlanCreationContext;
import com.trip.planner.repository.PlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

import static com.trip.planner.util.ContextUtil.buildPlan;

/**
 * Service class for {@link Plan}s with business rules.
 *
 * @author Gabriel Oliveira
 */
@Service
public class PlanService {
    private static final String OVERLAPPING_DATES_MESSAGE = "The given departure date: %s overlaps the return date: %s. Please inform a valid date range";

    @Autowired
    private PlanRepository planRepository;

    @Autowired
    private UserService userService;

    public Plan create(PlanCreationContext planCreationContext) {
        LocalDate departureDate = planCreationContext.getDepartureDate();
        LocalDate returnDate = planCreationContext.getReturnDate();

        if (datesAreOverlapping(departureDate, returnDate)) {
            String errorMessage = String.format(OVERLAPPING_DATES_MESSAGE, departureDate, returnDate);
            throw new PayloadValidationException(errorMessage);
        }

        // Validate given userId
        User user = userService.verifyIfExists(planCreationContext.getUserId());

        // Persist entity plan
        Plan savedPlan = planRepository.save(buildPlan(planCreationContext));

        // Relate saved plan with logged user
        userService.savePlan(user, savedPlan);

        return savedPlan;
    }

    private boolean datesAreOverlapping(LocalDate departureDate, LocalDate returnDate) {
        return departureDate.isAfter(returnDate);
    }
}