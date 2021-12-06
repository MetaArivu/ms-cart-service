package com.shopping.cart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ShoppingCartQueryApplication {

	public static void main(String[] args) {
	    //System.setProperty("spring.webflux.base-path", APPConstant.CONTEXT_PATH);
		SpringApplication.run(ShoppingCartQueryApplication.class, args);
	}

}
