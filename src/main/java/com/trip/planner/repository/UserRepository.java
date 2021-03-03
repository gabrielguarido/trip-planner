package com.trip.planner.repository;

import com.trip.planner.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Repository class for {@link User}.
 *
 * @author Gabriel Oliveira
 */
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmailAndPassword(String email, String password);
}
