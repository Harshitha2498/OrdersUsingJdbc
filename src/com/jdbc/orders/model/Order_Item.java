package com.jdbc.orders.model;

/**
 * @Author : Harshitha.D
 * @Created: Apr 15, 2024
 * @File : Order_Item.java
 * @Description : 
 */
public class Order_Item {
	private int id;
	private Orders orders;
	private Items items;
	private int quantity;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Orders getOrders() {
		return orders;
	}
	public void setOrders(Orders orders) {
		this.orders = orders;
	}
	public Items getItems() {
		return items;
	}
	public void setItems(Items items) {
		this.items = items;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	@Override
	public String toString() {
		return "Order_Item [id=" + id + ", orders=" + orders + ", items=" + items + ", quantity=" + quantity + "]";
	}
	
}
