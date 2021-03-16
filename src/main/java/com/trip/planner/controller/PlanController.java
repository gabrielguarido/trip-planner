package com.trip.planner.controller;

import com.trip.planner.model.Plan;
import com.trip.planner.model.context.PlanCreationContext;
import com.trip.planner.resource.PlanResource;
import com.trip.planner.service.PlanService;
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
import java.util.List;

/**
 * Expose the API endpoints for {@link Plan} resources.
 *
 * @author Gabriel Oliveira
 */
@RestController
@Api(value = "Plan REST API")
@RequestMapping(value = "plan", produces = MediaType.APPLICATION_JSON_VALUE)
public class PlanController implements PlanResource {
    @Autowired
    private PlanService planService;

    @ApiOperation(value = "Find all plans by userId")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "User ID not found"),
            @ApiResponse(code = 500, message = "Something Unexpected Happened")
    })
    public ResponseEntity<List<Plan>> findAllByUserId(@PathVariable Integer userId) {
        return ResponseEntity.ok(planService.findAllByUserId(userId));
    }

    @ApiOperation(value = "Find plan by ID")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Plan ID not found"),
            @ApiResponse(code = 500, message = "Something Unexpected Happened")
    })
    public ResponseEntity<Plan> findById(@PathVariable Integer planId) {
        return ResponseEntity.ok(planService.findById(planId));
    }

    @ApiOperation(value = "Create new plan")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Invalid payload"),
            @ApiResponse(code = 500, message = "Something Unexpected Happened")
    })
    public ResponseEntity<Plan> create(@RequestBody @Valid PlanCreationContext planCreationContext) {
        return ResponseEntity.ok(planService.create(planCreationContext));
    }

    @ApiOperation(value = "Delete plan")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Invalid planId"),
            @ApiResponse(code = 500, message = "Something Unexpected Happened")
    })
    public void delete(@PathVariable Integer planId) {
        planService.delete(planId);
    }
}
