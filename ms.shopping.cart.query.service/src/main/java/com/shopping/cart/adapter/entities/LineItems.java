package com.shopping.cart.adapter.entities;

public class LineItems {

	private String itemId;
	private int qty;
	private String itemName;
	private String image;
	private double unitPrice;
	private double totalPrice;

	public LineItems() {

	}

	public LineItems(String _itemId, String _itemName, String _image, int _qty, double _unitPrice) {
		this.itemId = _itemId;
		this.itemName = _itemName;
		this.image = _image;
		this.qty = _qty;
		this.unitPrice = _unitPrice;
		this.totalPrice = this.qty * this.unitPrice;
	}

	public String getItemId() {
		return itemId;
	}

	public int getQty() {
		return qty;
	}

	public String getItemName() {
		return itemName;
	}

	public double getUnitPrice() {
		return unitPrice;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public String getImage() {
		return image;
	}

	@Override
	public String toString() {
		return "LineItems [itemId=" + itemId + ", qty=" + qty + ", itemName=" + itemName + ", unitPrice=" + unitPrice
				+ ", totalPrice=" + totalPrice + "]";
	}

}
