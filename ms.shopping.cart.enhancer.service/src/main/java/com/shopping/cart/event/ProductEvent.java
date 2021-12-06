package com.shopping.cart.event;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ProductEvent {

	private String id;
	private String name;
	private String description;
	private String image;
	private Double price;
	private String type;

	public ProductEvent() {

	}

	public ProductEvent(String id, String name, String description, String image, Double price, String type) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.image = image;
		this.price = price;
		this.type = type;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public static ProductEvent parse(String _event) {
		try {
			return new ObjectMapper().readValue(_event, ProductEvent.class);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			log.error("Error while parsing ProductEvent, Exception=" + e.getMessage());
			return null;
		}
	}

	@Override
	public String toString() {
		return "ProductEvent [id=" + id + ", name=" + name + ", description=" + description + ", image=" + image
				+ ", price=" + price + ", type=" + type + "]";
	}

}
