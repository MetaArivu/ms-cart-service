package com.shopping.cart.adapter.service;

import static com.shopping.cart.APPConstant.KAFKA_TOPIC_CART_AGGR_EVENT;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.shopping.cart.adapter.entities.ShoppingCart;
import com.shopping.cart.adapter.repository.ShoppingCartRepository;
import com.shopping.cart.domainlayer.service.CartQueryService;

import reactor.core.publisher.Flux;

@Service
public class CartQueryServiceImpl implements CartQueryService {

	private static final Logger log = (Logger) LoggerFactory.getLogger(CartQueryServiceImpl.class);

	@Autowired
	private ShoppingCartRepository repo;
	
	@Override
	@KafkaListener(topics = { KAFKA_TOPIC_CART_AGGR_EVENT })
	public void consumeShoppingCartEvent(ConsumerRecord<String, String> event) {
		log.info("Shopping Cart Event Received key={}, offset={}, partition={}", event.key(), event.offset(), event.partition());
		ShoppingCart shoppingCart = ShoppingCart.parse(event.value());
		log.info("Shopping Cart Message={}", shoppingCart.toJson());
		
		repo.findByCustomerIdAndActive(shoppingCart.getCustomerId(), true)
			.toIterable()
			.forEach(cart->{
				cart.setActive(false);
				repo.save(cart).block();
			});
		
		repo.save(shoppingCart).block();
		
	}
	
	@Override
	public Flux<ShoppingCart> findByCustomerId(String customerId) {
		return repo.findByCustomerIdAndActive(customerId, true);
	}

}
