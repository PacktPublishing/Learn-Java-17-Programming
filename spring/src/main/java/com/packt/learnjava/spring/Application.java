package com.packt.learnjava.spring;

import io.github.resilience4j.common.circuitbreaker.configuration.CircuitBreakerConfigCustomizer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableCaching
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public CircuitBreakerConfigCustomizer circuitBreakerCustomizer() {
		return CircuitBreakerConfigCustomizer.of("circuitBreakerDemo", builder -> builder.minimumNumberOfCalls(1)
				.permittedNumberOfCallsInHalfOpenState(15));
	}
}


