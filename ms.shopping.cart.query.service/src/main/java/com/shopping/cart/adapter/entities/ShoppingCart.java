package com.shopping.cart.adapter.entities;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@Document(value = "shopping_cart_details")
@JsonDeserialize(builder = ShoppingCart.Builder.class)
public class ShoppingCart extends BaseEntity {

	private String customerId;
	private String eventType;

	private List<LineItems> lineItems = new ArrayList<LineItems>();

	public ShoppingCart(String customerId, String eventType, List<LineItems> lineItems) {
		this.customerId = customerId;
		this.eventType = eventType;
		this.lineItems = lineItems;
	}

	@JsonPOJOBuilder(buildMethodName = "build", withPrefix = "")
	public static class Builder {
		private String customerId;
		private String eventType;
		private LinkedHashMap<String, LineItems> lineItemsMap = new LinkedHashMap<String, LineItems>();

		public Builder customerId(String _customerId) {
			this.customerId = _customerId;
			return this;
		}
		
		public Builder eventType(String eventType) {
			this.eventType = eventType;
			return this;
		}


		public Builder lineItems(List<LineItems> _lineItem) {
			_lineItem.forEach((lineItem) -> {
				lineItemsMap.put(lineItem.getItemId(), lineItem);
			});
			return this;
		}

		public Builder addLineItem(String itemId, String itemName, int qty, double price, String image) {
			int prevQty = 0;
			LineItems lineItem = lineItemsMap.get(itemId);
			if (lineItem != null && qty > 0) {
				prevQty = lineItem.getQty();
			}
			lineItemsMap.put(itemId, new LineItems(itemId, itemName, image, prevQty + qty, price));
			return this;
		}

		private List<LineItems> getLineItems() {
			return lineItemsMap.values().parallelStream().collect(Collectors.toList());
		}

		public ShoppingCart build() {
			return new ShoppingCart(customerId,eventType, getLineItems());
		}

	}

	public String getCustomerId() {
		return customerId;
	}

	public List<LineItems> getLineItems() {
		return lineItems;
	}

	
	public String getEventType() {
		return eventType;
	}

	public String toJson() {
		try {
			return new ObjectMapper().writeValueAsString(this);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return "{'error':" + e.getMessage() + "'}";
		}

	}

	@Override
	public String toString() {
		return toJson();
	}

	public static ShoppingCart parse(String v) {
		try {
			return new ObjectMapper().readValue(v, ShoppingCart.class);
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public boolean isValid() { // TODO Auto-generated method stub
		return true;
	}

}
