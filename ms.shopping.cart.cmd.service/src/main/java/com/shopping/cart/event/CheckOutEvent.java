package com.shopping.cart.event;

public class CheckOutEvent implements CartEvent {

	private String customerId;
	private EventType eventType;

	public CheckOutEvent(String customerId, EventType eventType) {
		super();
		this.customerId = customerId;
		this.eventType = eventType;
	}

	public static class Builder {

		private String customerId;
		private EventType eventType;

		public Builder customerId(String customerId) {
			this.customerId = customerId;
			return this;
		}

		public Builder eventType(EventType eventType) {
			this.eventType = eventType;
			return this;
		}

		public CheckOutEvent build() {
			return new CheckOutEvent(customerId, eventType);
		}

	}

	public String getCustomerId() {
		return customerId;
	}

	public EventType getEventType() {
		return eventType;
	}

	@Override
	public String toString() {
		return eventType + "|" + customerId + "|";
	}

	public static Builder builder() {
		return new Builder();
	}

}
