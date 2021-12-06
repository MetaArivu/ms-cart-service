package com.shopping.cart.adapter.service;

import static com.shopping.cart.APPConstant.KAFKA_TOPIC_CART_AGGR_EVENT;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.shopping.cart.adapter.entity.ShoppingCart;
import com.shopping.cart.adapter.repo.ShoppingCartRepo;
import com.shopping.cart.domainlayer.service.CartQueryService;

@Service
public class CartQueryServiceImpl implements CartQueryService {

	private static final Logger log = (Logger) LoggerFactory.getLogger(CartQueryServiceImpl.class);

	@Override
	@KafkaListener(topics = { KAFKA_TOPIC_CART_AGGR_EVENT })
	public void consumeShoppingCartEvent(ConsumerRecord<String, String> event) {
		log.info("Shopping Cart Event Received key={}, offset={}, partition={}", event.key(), event.offset(), event.partition());
		log.info("Shopping Cart Message={}", event.value());
		ShoppingCart shoppingCart = ShoppingCart.parse(event.value());
		ShoppingCartRepo.instance().add(shoppingCart);
		log.info("ShoppingCart="+this.findByCustomerId(shoppingCart.getCustomerId()));
	}
	
	@Override
	public ShoppingCart findByCustomerId(String customerId) {
		return ShoppingCartRepo.instance().get(customerId);
	}

}
