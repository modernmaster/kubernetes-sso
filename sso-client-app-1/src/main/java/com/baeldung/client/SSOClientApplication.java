package com.baeldung.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class SSOClientApplication {

    private static final Logger LOG = LoggerFactory.getLogger(SSOClientApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(SSOClientApplication.class, args);
    }
}
