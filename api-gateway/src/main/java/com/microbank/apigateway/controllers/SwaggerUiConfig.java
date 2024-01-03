package com.microbank.apigateway.controllers;

import java.net.URI;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springdoc.core.properties.AbstractSwaggerUiConfigProperties.SwaggerUrl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
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

        List<ServiceInstance> instances = new ArrayList<>();
        discoveryClient.getInstances(applicationName).collectList().subscribe(instances::addAll);
        if (instances.size() < 1) return null;
        URI apiGatewayUri = instances.get(0).getUri();

        List<String> services = new ArrayList<>();
        discoveryClient.getServices()
            .filter(serviceName -> !serviceName.equals(applicationName))
            .collectList().subscribe(services::addAll);

        services.forEach(serviceName -> 
                        urls.add(new SwaggerUrl(serviceName, serviceName, MessageFormat.format("{0}/{1}/v3/api-docs", apiGatewayUri, serviceName))));
        return Map.of("urls", urls);
    }
}
