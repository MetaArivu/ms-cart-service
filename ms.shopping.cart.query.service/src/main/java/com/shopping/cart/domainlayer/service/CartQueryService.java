package com.shopping.cart.domainlayer.service;

import org.apache.kafka.clients.consumer.ConsumerRecord;

import com.shopping.cart.adapter.entities.ShoppingCart;

import reactor.core.publisher.Flux;

public interface CartQueryService {

	public void consumeShoppingCartEvent(ConsumerRecord<String, String> event);
	
	public Flux<ShoppingCart> findByCustomerId(String customerId);
}
