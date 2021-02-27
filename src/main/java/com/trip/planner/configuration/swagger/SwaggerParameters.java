package com.trip.planner.configuration.swagger;

import springfox.documentation.service.Contact;

/**
 * Parameters used to configure Swagger 2.
 *
 * @author Gabriel Oliveira
 */
public class SwaggerParameters {

    protected static final String TITLE = "TRIP PLANNER";

    protected static final String DESCRIPTION = "Plan your trips with this app";

    protected static final String API_VERSION = "0.0.1-SNAPSHOT";

    protected static final Contact CONTACT = new Contact("Gabriel Guarido",
            "https://www.linkedin.com/in/gabriel-guarido-oliveira/",
            "gabrielguarido.oliveira@gmail.com");
}
