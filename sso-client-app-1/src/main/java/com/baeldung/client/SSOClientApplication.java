package com.baeldung.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SSOClientApplication {

    private static final Logger LOG = LoggerFactory.getLogger(SSOClientApplication.class);

    public static void main(String[] args) {
        final String[] expectedVars = {"SSO-RESOURCE-SERVER_API_ADDR"};
        for (String v : expectedVars) {
            String value = System.getenv(v);
            if (value == null) {
                LOG.error("error: {} environment variable not set", v);
                System.exit(1);
            }
        }
        SpringApplication.run(SSOClientApplication.class, args);
    }
}
