package com.shopping.cart.service;

import org.apache.kafka.streams.kstream.KStream;
import org.springframework.cloud.stream.annotation.Input;

public interface CartEventSourceBinder {

	@Input("cart-details-input-channel")
	public KStream<String, String> cartInputStream();
	
	
}
