package com.baeldung.client.web.controller;

import com.baeldung.client.web.model.FooModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Controller
public class FooClientController {

    private static final String SSO_RESOURCE_SERVER = "sso-resource-server";
    private static final String PATH = "/api/foos/";
    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private WebClient webClient;

    @GetMapping("/foos")
    public String getFoos(Model model) {
        String resourceServerHost = "SSO-resource-server";// this.discoveryClient.getInstances(SSO_RESOURCE_SERVER).get(0).getHost();
        String fooApiUrl = String.format("http://%s%s",resourceServerHost, PATH);
        List<FooModel> foos = this.webClient.get()
            .uri(fooApiUrl)
            .retrieve()
            .bodyToMono(new ParameterizedTypeReference<List<FooModel>>() {
            })
            .block();
        model.addAttribute("foos", foos);
        return "foos";
    }

}
