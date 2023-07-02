package com.inw.gatewayserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@SpringBootApplication
public class GatewayserverApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayserverApplication.class, args);
	}

	@Bean
	public RouteLocator myRoute(RouteLocatorBuilder builder){
		return builder.routes()
				.route(p -> p.path("/inwove/accounts/**")
						.filters(f ->  f.rewritePath("/inwove/accounts/(?<segment>.*)","/${segment}")
								.addResponseHeader("X-Response-Time", new Date().toString()))
						.uri("lb://ACCOUNTS"))
				.route(p -> p.path("/inwove/cards/**")
						.filters(f-> f.rewritePath("/inwove/cards/(?<segment>.*)","/${segment}")
								.addResponseHeader("X-Responce-Time", new Date().toString()))
						.uri("lb://CARDS"))
				.route(p -> p.path("/inwove/loans/**")
						.filters(f-> f.rewritePath("/inwove/loans/(?<segment>.*)","/${segment}")
								.addResponseHeader("X-Responce-Time", new Date().toString()))
						.uri("lb://LOANS")).build();
	}

}
