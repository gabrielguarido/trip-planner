package com.trip.planner.exception;

/**
 * Custom Exception class for resource not found.
 *
 * @author Gabriel Oliveira
 */
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
