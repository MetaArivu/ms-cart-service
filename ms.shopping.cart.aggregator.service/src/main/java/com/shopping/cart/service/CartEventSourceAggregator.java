package com.shopping.cart.service;

import java.util.ArrayList;

import com.shopping.cart.event.CartEvent;
import com.shopping.cart.event.LineItems;
import com.shopping.cart.event.ShoppingCart;

public class CartEventSourceAggregator {

	public static ShoppingCart apply(ShoppingCart shoppingCart, CartEvent event) {
		
		switch (event.getEventType()) {
		case ADD_ITEM:
			return new ShoppingCart.Builder()
			   		.customerId(event.getCustomerId())
			   		.lineItems(shoppingCart.getLineItems())
			   		.addLineItem(event.getItemId(), event.getItemName(), event.getQty(), event.getPrice())
			   		.build();
		case REMOVE_ITEM:
			return new ShoppingCart.Builder()
			   		.customerId(event.getCustomerId())
			   		.lineItems(shoppingCart.getLineItems())
			   		.addLineItem(event.getItemId(), event.getItemName(), event.getQty(), event.getPrice())
			   		.build();
		case CLEAR_CART:
			return new ShoppingCart.Builder()
			   		.customerId(event.getCustomerId())
			   		.lineItems(new ArrayList<LineItems>())
			   		.build();
		case CHECKOUT:
			return new ShoppingCart.Builder()
			   		.customerId(event.getCustomerId())
			   		.lineItems(shoppingCart.getLineItems())
			   		.build();
		default:
			return new ShoppingCart.Builder().build();
		}
		 
		
	}
}
