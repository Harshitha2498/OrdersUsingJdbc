package com.jdbc.orders.model;

import java.util.Date;
import java.util.List;

/**
 * @Author : Harshitha D
 * @Created: Apr 15, 2024
 * @File : Orders.java
 * @Description : 
 */

public class Orders {
	private int orderId;
	private Date orderDate;
	private List<Order_Item> order_Item;
	@Override
	public String toString() {
		return "Orders [orderId=" + orderId + ", orderDate=" + orderDate + ", order_Item=" + order_Item + "]";
	}
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	public List<Order_Item> getOrder_Item() {
		return order_Item;
	}
	public void setOrder_Item(List<Order_Item> order_Item) {
		this.order_Item = order_Item;
	}
	
}
