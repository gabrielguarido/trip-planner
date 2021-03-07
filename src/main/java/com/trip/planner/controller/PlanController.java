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
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

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

    @ApiOperation(value = "Create new plan")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Invalid payload"),
            @ApiResponse(code = 500, message = "Something Unexpected Happened")
    })
    public ResponseEntity<Plan> create(@RequestBody @Valid PlanCreationContext planCreationContext) {
        return new ResponseEntity<>(planService.create(planCreationContext), HttpStatus.OK);
    }
}
