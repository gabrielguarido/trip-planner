package com.trip.planner.repository;

import com.trip.planner.model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Repository class for {@link Expense}.
 *
 * @author Gabriel Oliveira
 */
public interface ExpenseRepository extends JpaRepository<Expense, Integer> {
    @Query(value = "SELECT * FROM expense INNER JOIN plan_expense ON expense.expense_id = plan_expense.expense_id and plan_expense.plan_id = :planId", nativeQuery = true)
    List<Expense> findAllByPlanId(Integer planId);
}
