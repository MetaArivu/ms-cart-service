package com.shoppping.cart.domainlayer.service;

import org.apache.kafka.clients.consumer.ConsumerRecord;

import com.shoppping.cart.adapter.entity.ShoppingCart;

public interface CartQueryService {

	public void consumeShoppingCartEvent(ConsumerRecord<String, String> event);
	
	public ShoppingCart findByCustomerId(String customerId);
}
