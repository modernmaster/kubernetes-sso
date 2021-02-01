package com.baeldung.resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ResourceServerApp {

    private static final Logger LOG = LoggerFactory.getLogger(ResourceServerApp.class);

    public static void main(String[] args) throws Exception {
        final String[] expectedVars = {};
        for (String v : expectedVars) {
            String value = System.getenv(v);
            if (value == null) {
                LOG.error("error: {} environment variable not set", v);
                System.exit(1);
            }
        }
        SpringApplication.run(ResourceServerApp.class, args);
    }

}
