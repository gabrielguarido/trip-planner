package com.trip.planner.repository;

import com.trip.planner.model.Plan;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository class for {@link Plan}.
 *
 * @author Gabriel Oliveira
 */
public interface PlanRepository extends JpaRepository<Plan, Integer> {
}
