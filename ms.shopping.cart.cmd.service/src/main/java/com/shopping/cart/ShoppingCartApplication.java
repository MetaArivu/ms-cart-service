package com.shopping.cart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.environment.EnvironmentChangeEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;

import brave.sampler.Sampler;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(info = @Info(title = "APIs", version = "1.0", description = "Shopping Cart Command Service Documentation APIs v1.0"))
@SpringBootApplication
public class ShoppingCartApplication {

	public static void main(String[] args) {
	    System.setProperty("spring.webflux.base-path", APPConstant.CONTEXT_PATH);
		SpringApplication.run(ShoppingCartApplication.class, args);
	}

	@EventListener({ EnvironmentChangeEvent.class })
	public void onRefresh() {
		System.out.println("Environment Changed..");
	}
	
	@Bean
	public Sampler defaultSampler() {
		return Sampler.ALWAYS_SAMPLE;
	}
}
