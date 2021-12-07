package com.shopping.cart.adapter.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.shopping.cart.adapter.entities.ShoppingCart;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface ShoppingCartRepository extends ReactiveMongoRepository<ShoppingCart, String> {

	public Flux<ShoppingCart> findByCustomerIdAndActive(String customerId, boolean flag);
}
