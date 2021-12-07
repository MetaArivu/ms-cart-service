package com.shopping.cart.adapter.controller.v1;

import static com.shopping.cart.APPConstant.V1;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shopping.cart.adapter.entities.ShoppingCart;
import com.shopping.cart.adapter.service.CartQueryServiceImpl;
import com.shopping.cart.domainlayer.service.CartQueryService;
import com.shopping.cart.dto.Response;
import com.shopping.cart.server.secutiry.JWTUtil;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping(V1)
@CrossOrigin("*")
public class ShoppingCartController {

	private static final Logger log = (Logger) LoggerFactory.getLogger(ShoppingCartController.class);

	@Autowired
	private CartQueryService shoppingCartService;

	@Autowired
	private JWTUtil jwtUtil;
	
	@GetMapping(value = "/")
	public Mono<ResponseEntity<Response<List<ShoppingCart>>>> cartByUserId() {
		String userId = jwtUtil.getUserIdFromToken(MDC.get("Authorization"));
		log.info("Fetch Cart By  UserId={}",userId);
		
		return shoppingCartService.findByCustomerId(userId).collectList()
				.map(list -> new ResponseEntity<Response<List<ShoppingCart>>>(
						new Response<List<ShoppingCart>>(true, "Record retrieved successully", list), HttpStatus.OK))
				.defaultIfEmpty(new ResponseEntity<Response<List<ShoppingCart>>>(
						new Response<List<ShoppingCart>>(false, "Record not found"), HttpStatus.NOT_FOUND));

	}
	 
}
