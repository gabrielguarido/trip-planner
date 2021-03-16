package com.trip.planner.resource;

import com.trip.planner.model.Plan;
import com.trip.planner.model.context.PlanCreationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;

/**
 * Define all the {@link Plan} resources that will be exposed by the Controller classes.
 *
 * @author Gabriel Oliveira
 */
public interface PlanResource {
    @GetMapping("/user/{userId}")
    ResponseEntity<List<Plan>> findAllByUserId(@PathVariable Integer userId);

    @GetMapping("/{planId}")
    ResponseEntity<Plan> findById(@PathVariable Integer planId);

    @PostMapping
    ResponseEntity<Plan> create(@RequestBody @Valid PlanCreationContext planCreationContext);

    @DeleteMapping("/{planId}")
    void delete(@PathVariable Integer planId);
}
