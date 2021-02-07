package com.baeldung.gateway;

import org.slf4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import static org.slf4j.LoggerFactory.getLogger;


@SpringBootApplication
@EnableDiscoveryClient
//@lombok.extern.slf4j.Slf4j
public class GatewayServiceApplication {

    private static final Logger LOG = getLogger(GatewayServiceApplication.class);

    public static void main(String[] args) {
        final String[] expectedVars = {};
        for (String v : expectedVars) {
            String value = System.getenv(v);
            if (value == null) {
                LOG.error("error: {} environment variable not set", v);
                System.exit(1);
            }
        }
        SpringApplication.run(GatewayServiceApplication.class, args);
    }
}
