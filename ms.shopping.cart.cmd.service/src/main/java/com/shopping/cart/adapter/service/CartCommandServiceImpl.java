package com.shopping.cart.adapter.service;

import static com.shopping.cart.APPConstant.KAFKA_TOPIC_CART_EVENT;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.shopping.cart.command.AddItemCommand;
import com.shopping.cart.command.CheckOutCommand;
import com.shopping.cart.command.ClearCartCommand;
import com.shopping.cart.command.RemoveItemCommand;
import com.shopping.cart.domainlayer.service.CartCommandService;
import com.shopping.cart.event.CartEvent;
import com.shopping.cart.event.CheckOutEvent;
import com.shopping.cart.event.ClearCartEvent;
import com.shopping.cart.event.EventType;
import com.shopping.cart.event.ItemAddedEvent;
import com.shopping.cart.event.ItemRemovedEvent;
import com.shopping.cart.exception.ServiceException;
import com.shopping.cart.server.secutiry.JWTUtil;;

@Service
public class CartCommandServiceImpl implements CartCommandService {

	private static final Logger log = (Logger) LoggerFactory.getLogger(CartCommandServiceImpl.class);

	@Autowired
	private JWTUtil jwtUTIL;
	
	@Autowired
	private KafkaTemplate<String, CartEvent> cartKafkaTemplate;
	
	@Override
	public boolean addItem(AddItemCommand addItem) throws ServiceException {
		
		ItemAddedEvent event = ItemAddedEvent.builder()
								.customerId(this.getUserId())
								.itemId(addItem.getItemId())
								.qty(addItem.getQty())
								.eventType(EventType.ADD_ITEM)
								.build();
		log.info("Item Added Event Published, Event={}",event);
		cartKafkaTemplate.send(KAFKA_TOPIC_CART_EVENT, event.getCustomerId(), event);
		return true;
	}

	@Override
	public boolean removeItem(RemoveItemCommand removeItem) throws ServiceException {
		ItemRemovedEvent event = ItemRemovedEvent.builder()
									.customerId(this.getUserId())
									.itemId(removeItem.getItemId())
									.qty(0)
									.eventType(EventType.REMOVE_ITEM)
									.build();
		log.info("Item Removed Event Published, Event={}",event);
		cartKafkaTemplate.send(KAFKA_TOPIC_CART_EVENT, event.getCustomerId(), event);
		return true;
	}

	@Override
	public boolean checkOut(CheckOutCommand checkOut) throws ServiceException {
		CheckOutEvent event = CheckOutEvent.builder()
				.customerId(this.getUserId())
				.eventType(EventType.CHECKOUT)
				.build();
		log.info("Checkout Event Published, Event={}",event);
		cartKafkaTemplate.send(KAFKA_TOPIC_CART_EVENT, event.getCustomerId(), event);
		return true;
	}

	@Override
	public boolean clearCart(ClearCartCommand clearCart) throws ServiceException {
		ClearCartEvent event = ClearCartEvent.builder()
				.customerId(this.getUserId())
				.eventType(EventType.CLEAR_CART)
				.build();
		log.info("Clearcart Event Published, Event={}",event);
		cartKafkaTemplate.send(KAFKA_TOPIC_CART_EVENT, event.getCustomerId(), event);
		return true;
	}
	
	private String getUserId() {
		String token = MDC.get("Authorization");
		System.out.println("TOKEEN="+token);
		return jwtUTIL.getUserIdFromToken(token);
	}
	

}
