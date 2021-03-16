package com.trip.planner.configuration.property;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

/**
 * API constant properties and security details.
 *
 * @author Gabriel Oliveira
 */
@Service
@ConfigurationProperties("api")
public class ApiProperty {
    private static final String ALLOWED_ORIGIN = "https://trip-planner-interface.herokuapp.com/";

    public String getAllowedOrigin() {
        return ALLOWED_ORIGIN;
    }
}
