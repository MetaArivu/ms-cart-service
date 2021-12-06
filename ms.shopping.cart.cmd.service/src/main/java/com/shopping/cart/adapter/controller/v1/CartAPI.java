package com.shopping.cart.adapter.controller.v1;

import static com.shopping.cart.APPConstant.V1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shopping.cart.command.AddItemCommand;
import com.shopping.cart.command.CheckOutCommand;
import com.shopping.cart.command.ClearCartCommand;
import com.shopping.cart.command.RemoveItemCommand;
import com.shopping.cart.domainlayer.service.CartCommandService;
import com.shopping.cart.dto.Response;
import com.shopping.cart.exception.ServiceException;

@RestController
@RequestMapping(V1)
public class CartAPI {

	@Autowired
	private CartCommandService cartService;

	@PostMapping("/add")
	public ResponseEntity<Response<Boolean>> addItem(@RequestBody AddItemCommand addItem) throws ServiceException {
		Boolean flag = cartService.addItem(addItem);
		return new ResponseEntity<Response<Boolean>>(new Response<Boolean>(flag, "Item Added To Cart Successfully!"),
				HttpStatus.OK);
	}

	@PostMapping("/remove")
	public ResponseEntity<Response<Boolean>> addItem(@RequestBody RemoveItemCommand removeItem)
			throws ServiceException {
		Boolean flag = cartService.removeItem(removeItem);
		return new ResponseEntity<Response<Boolean>>(
				new Response<Boolean>(flag, "Item Removed From Cart Successfully!"), HttpStatus.OK);
	}

	@PostMapping("/checkout")
	public ResponseEntity<Response<Boolean>> checkOut(@RequestBody CheckOutCommand checkOut) throws ServiceException {
		Boolean flag = cartService.checkOut(checkOut);
		return new ResponseEntity<Response<Boolean>>(new Response<Boolean>(flag, "Cart Checkout Successfully!"),
				HttpStatus.OK);
	}

	@PostMapping("/clear")
	public ResponseEntity<Response<Boolean>> addItem(@RequestBody ClearCartCommand clearCart) throws ServiceException {
		Boolean flag = cartService.clearCart(clearCart);
		return new ResponseEntity<Response<Boolean>>(new Response<Boolean>(flag, "Cart Cleared Successfully!"),
				HttpStatus.OK);

	}

}
