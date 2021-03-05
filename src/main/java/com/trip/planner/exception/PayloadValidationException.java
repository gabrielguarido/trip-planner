package com.trip.planner.exception;

/**
 * Custom Exception class for payload validation errors.
 *
 * @author Gabriel Oliveira
 */
public class PayloadValidationException extends RuntimeException {
    public PayloadValidationException(String message) {
        super(message);
    }
}
