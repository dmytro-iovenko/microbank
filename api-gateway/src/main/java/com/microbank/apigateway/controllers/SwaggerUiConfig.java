package com.microbank.apigateway.controllers;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springdoc.core.properties.AbstractSwaggerUiConfigProperties.SwaggerUrl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.discovery.ReactiveDiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SwaggerUiConfig {
    @Autowired
    private ReactiveDiscoveryClient discoveryClient;

    @Value("${spring.application.name}")
    private String applicationName;

    @GetMapping(path = "/swagger-config.json")
    public Map<String, Object> swaggerConfig() {
        List<SwaggerUrl> urls = new LinkedList<>();
        List<String> services = new ArrayList<>();
        discoveryClient.getServices()
            .filter(serviceName -> !serviceName.equals(applicationName))
            .collectList().subscribe(services::addAll);
        services.forEach(serviceName -> 
                        urls.add(new SwaggerUrl(serviceName, MessageFormat.format("/{0}/v3/api-docs", serviceName), serviceName)));
        return Map.of("urls", urls);
    }
}
