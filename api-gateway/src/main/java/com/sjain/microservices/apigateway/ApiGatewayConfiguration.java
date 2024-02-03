package com.sjain.microservices.apigateway;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiGatewayConfiguration {
    @Bean
    public RouteLocator gatewayRouter(RouteLocatorBuilder builder){
        return builder.routes()
                .route(p -> p.path("/rewards-calculator-service/**")
                        .uri("lb://rewards-calculator-service"))
                .route(p -> p.path("/rewards-service/**")
                        .uri("lb://rewards-service"))
                .route(p -> p.path("/rewards-management-service/**")
                        .uri("lb://rewards-management-service"))
                .route(p -> p.path("/rewards-management-service-feign/**")
                        .uri("lb://rewards-management-service")) // Redirects to the service name ("rewards-management-service") in Eureka
                .route(p -> p.path("/rewards-management-service-new/**")
                        .filters(f -> f.rewritePath(
                                "/rewards-management-service-new/(?<segment>.*)",
                                "/rewards-management-service-feign/${segment}"))
                        .uri("lb://rewards-management-service"))
                .build();
    }
}
