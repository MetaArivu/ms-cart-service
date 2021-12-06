package com.shopping.cart.event;

public class ItemAddedEvent implements CartEvent {

	private String customerId;
	private String itemId;
	private int qty;
	private EventType eventType;


	public ItemAddedEvent(String customerId, String itemId, int qty, EventType eventType) {
		super();
		this.customerId = customerId;
		this.itemId = itemId;
		this.qty = qty;
		this.eventType = eventType;
	}
	
	public static class Builder {

		private String customerId;
		private String itemId;
		private int qty;
		private EventType eventType;

		public Builder customerId(String customerId) {
			this.customerId = customerId;
			return this;
		}

		public Builder itemId(String itemId) {
			this.itemId = itemId;
			return this;
		}

		public Builder qty(int qty) {
			this.qty = qty;
			return this;
		}

		public Builder eventType(EventType eventType) {
			this.eventType = eventType;
			return this;
		}

		public ItemAddedEvent build() {
			return new ItemAddedEvent(customerId, itemId, qty, eventType);
		}

	}


	public String getCustomerId() {
		return customerId;
	}

	public String getItemId() {
		return itemId;
	}

	public int getQty() {
		return qty;
	}

	public EventType getEventType() {
		return eventType;
	}

	@Override
	public String toString() {
		return eventType + "|" + customerId + "|" + itemId + "|" + qty;
	}

	public static Builder builder() {
		return new Builder();
	}

}
