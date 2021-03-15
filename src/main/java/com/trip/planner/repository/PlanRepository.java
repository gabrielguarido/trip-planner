package com.trip.planner.repository;

import com.trip.planner.model.Plan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Repository class for {@link Plan}.
 *
 * @author Gabriel Oliveira
 */
public interface PlanRepository extends JpaRepository<Plan, Integer> {
    @Query(value = "SELECT * FROM plan INNER JOIN user_plan ON plan.plan_id = user_plan.plan_id and user_plan.user_id = :userId", nativeQuery = true)
    List<Plan> findAllByUserId(Integer userId);
}
