package com.trip.planner.repository;

import com.trip.planner.model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository class for {@link Expense}.
 *
 * @author Gabriel Oliveira
 */
public interface ExpenseRepository extends JpaRepository<Expense, Integer> {
}
