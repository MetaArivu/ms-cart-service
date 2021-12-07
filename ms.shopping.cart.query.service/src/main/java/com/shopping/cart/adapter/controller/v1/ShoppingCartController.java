package com.shopping.cart.adapter.controller.v1;

import static com.shopping.cart.APPConstant.V1;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shopping.cart.adapter.entities.ShoppingCart;
import com.shopping.cart.domainlayer.service.CartQueryService;
import com.shopping.cart.dto.Response;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping(V1)
@CrossOrigin("*")
public class ShoppingCartController {

	@Autowired
	private CartQueryService shoppingCartService;

	@GetMapping(value = "/{id}")
	public Mono<ResponseEntity<Response<List<ShoppingCart>>>> productDetailsById(@PathVariable("id") String id) {

		return shoppingCartService.findByCustomerId(id).collectList()
				.map(list -> new ResponseEntity<Response<List<ShoppingCart>>>(
						new Response<List<ShoppingCart>>(true, "Record retrieved successully", list), HttpStatus.OK))
				.defaultIfEmpty(new ResponseEntity<Response<List<ShoppingCart>>>(
						new Response<List<ShoppingCart>>(false, "Record not found"), HttpStatus.NOT_FOUND));

	}
	 
}
