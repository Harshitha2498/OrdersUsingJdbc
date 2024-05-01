package com.jdbc.orders.Dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.jdbc.orders.model.Items;
import com.jdbc.orders.model.Order_Item;
import com.jdbc.orders.model.Orders;
import com.jdbc.orders.utils.JdbcDatabase;
import com.mysql.jdbc.Statement;

/**
 * @Author : Harshitha.D
 * @Created: Apr 15, 2024
 * @File : OrderDao.java
 * @Description :
 */
public class OrderDao {
	static JdbcDatabase db;
	ResultSet rs = null;

	public List<Orders> getAllOrders() {
		db = new JdbcDatabase();
		List<Orders> orderList = new ArrayList<>();
		Connection con = JdbcDatabase.openDBConn();
		try {
			PreparedStatement ps = con.prepareStatement("select * from orders");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Orders or = new Orders();
				or.setOrderId(rs.getInt(1));
				or.setOrderDate(rs.getDate(2));
				orderList.add(or);
			}
			return orderList;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return orderList;
	}

	public void createNewOrder(Orders order) {
		db = new JdbcDatabase();
		Connection con = JdbcDatabase.openDBConn();
		try {
			String sql = "Insert into Orders(order_date) values(?)";
			PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setDate(1, new Date(order.getOrderDate().getTime()));
			int i = ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) {
				order.setOrderId(rs.getInt(1));
			}
			System.out.println(i + "record inserted");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static List<Items> getAllItems() {
		db = new JdbcDatabase();
		Connection con = JdbcDatabase.openDBConn();
		List<Items> itemsList = new ArrayList<>();
		try {
			PreparedStatement ps = con.prepareStatement("Select * from Items");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Items items = new Items();
				items.setItemId(rs.getInt(1));
				items.setItemName(rs.getString(2));
				items.setItemPrice(rs.getDouble(3));
				itemsList.add(items);
			}
			System.out.println(itemsList);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return itemsList;
	}

	public static void saveOrderItem(int orderId, int itemId, int itemQuantity) {
		db = new JdbcDatabase();
		Connection con = JdbcDatabase.openDBConn();
		try {
			PreparedStatement ps = con.prepareStatement(
					"insert into order_Items(order_id,item_id,quantity) values(?,?,?) on duplicate key update quantity=quantity+? ");
			ps.setInt(1, orderId);
			ps.setInt(2, itemId);
			ps.setInt(3, itemQuantity);
			ps.setInt(4, itemQuantity);
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static Orders findOrderByOrderId(int orderId) {
		db = new JdbcDatabase();
		Connection con = JdbcDatabase.openDBConn();
		Orders order = null;
		try {
			PreparedStatement ps = con.prepareStatement("select * from orders where order_id=?");
			ps.setInt(1, orderId);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				order = new Orders();
				order.setOrderId(rs.getInt(1));
				System.out.println(order);
				return order;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return order;
	}

	public static List<Order_Item> getAllOrderItems(int orderId) {
		db = new JdbcDatabase();
		Order_Item oi;
		Items items;
		Orders order;
		Connection con = JdbcDatabase.openDBConn();
		List<Order_Item> orderitemsList = new ArrayList<>();
		try {
			PreparedStatement ps = con.prepareStatement(
					"select oi.id,i.item_id,i.item_name,i.item_price,oi.quantity from order_items oi left join items i on i.item_id=oi.item_id where ?=oi.order_id");
			ps.setInt(1, orderId);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				oi = new Order_Item();
				items = new Items();
				order = new Orders();
				oi.setId(rs.getInt(1));
				items.setItemId(rs.getInt(2));
				items.setItemName(rs.getString(3));
				items.setItemPrice(rs.getDouble(4));
				oi.setItems(items);
				order.setOrderId(orderId);
				oi.setOrders(order);
				oi.setQuantity(rs.getInt(5));
				orderitemsList.add(oi);
			}
			System.out.println(orderitemsList);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return orderitemsList;
	}

	public static void deleteOrderItem(int order_item_id) {
		db = new JdbcDatabase();
		Connection con = JdbcDatabase.openDBConn();
		try {
			PreparedStatement ps = con.prepareStatement("DELETE FROM order_items WHERE id= ?");
			ps.setInt(1, order_item_id);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void deleteOrder(int orderId) {
		db = new JdbcDatabase();
		Connection con = JdbcDatabase.openDBConn();
		try {
			PreparedStatement ps = con.prepareStatement("DELETE FROM order_items WHERE order_id= ?");
			ps.setInt(1, orderId);
			ps.executeUpdate();
			PreparedStatement ps1 = con.prepareStatement("DELETE FROM orders WHERE order_id= ?");
			ps1.setInt(1, orderId);
			ps1.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static List<Order_Item> findOrderItemByOrderId(int orderId) {
		db = new JdbcDatabase();
		Connection con = JdbcDatabase.openDBConn();
		Order_Item order_items;
		Orders order;
		Items item;
		List<Order_Item> orderitemsList = new ArrayList<>();
		try {
			PreparedStatement ps = con.prepareStatement(
					"select  oi.id,oi.order_id,i.item_id,i.item_name,i.item_price,oi.quantity from order_items oi left join items i on i.item_id=oi.item_id where ?=oi.order_id");
			ps.setInt(1, orderId);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				order_items = new Order_Item();
				order = new Orders();
				item = new Items();
				order_items.setId(rs.getInt(1));
				order.setOrderId(rs.getInt(2));
				order_items.setOrders(order);
				item.setItemId(rs.getInt(3));
				item.setItemName(rs.getString(4));
				item.setItemPrice(rs.getDouble(5));
				order_items.setItems(item);
				order_items.setQuantity(rs.getInt(6));
				orderitemsList.add(order_items);
				System.out.println(order_items);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return orderitemsList;
	}

	public static Order_Item findOrderItemById(int orderItemId) {
		db = new JdbcDatabase();
		Connection con = JdbcDatabase.openDBConn();
		Order_Item order_items = null;
		Orders order;
		Items item;
		try {
			PreparedStatement ps = con.prepareStatement(
					"select  oi.id,oi.order_id,i.item_id,i.item_name,i.item_price,oi.quantity from order_items oi left join items i on i.item_id=oi.item_id where ?=oi.id");
			ps.setInt(1, orderItemId);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				order_items = new Order_Item();
				order = new Orders();
				item = new Items();
				order_items.setId(rs.getInt(1));
				order.setOrderId(rs.getInt(2));
				order_items.setOrders(order);
				item.setItemId(rs.getInt(3));
				item.setItemName(rs.getString(4));
				item.setItemPrice(rs.getDouble(5));
				order_items.setItems(item);
				order_items.setQuantity(rs.getInt(6));
				System.out.println(order_items);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return order_items;
	}

	public static void updateOrderItem(Order_Item oi, int order_item_id, int item_id, int item_Quantity) {
		db = new JdbcDatabase();
		Connection con = JdbcDatabase.openDBConn();
		try {
			PreparedStatement ps = con.prepareStatement("update order_items set item_id=?,quantity=? where id=?");
			ps.setInt(1, item_id);
			ps.setInt(2, item_Quantity);
			ps.setInt(3, order_item_id);
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
