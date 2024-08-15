package org.dnyanyog.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RouterConfig {

  @Bean
  public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
    return builder
        .routes()
        .route(
            "directory_public_route",
            r -> r.path("/api/v1/public/directory/**").uri("http://localhost:8081"))
        .route(
            "directory_service_route",
            r -> r.path("/api/directory/**").uri("http://directory-service:8081"))
        .route(
            "patient_service_route",
            r -> r.path("/patient/api/**").uri("http://patient-service:8082"))
        .route("case_service_route", r -> r.path("/case/api/**").uri("http://case-service:8083"))
        .route(
            "appointment_service_route",
            r -> r.path("/appointment/api/**").uri("http://appointment-service:8084"))
        .build();
  }
}
