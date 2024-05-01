package com.jdbc.orders.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jdbc.orders.Dao.OrderDao;
import com.jdbc.orders.model.Order_Item;

/**
 * Servlet implementation class DeleteItemServlet
 */
//@WebServlet("/DeleteItemServlet")
public class DeleteOrderItemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteOrderItemServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		int orderItemId=Integer.parseInt(request.getParameter("orderItemId").toString());
		OrderDao.deleteOrderItem(orderItemId);
		int orderId=Integer.parseInt(request.getParameter("orderId").toString());
		System.out.println(orderId);
		List<Order_Item> orderItemList =  OrderDao.findOrderItemByOrderId(orderId);
		if(orderItemList==null||orderItemList.size()==0) {
			session.setAttribute("orderItemList", null);
			OrderDao.deleteOrderItem(orderId);
			OrderDao.deleteOrder(orderId);
			response.sendRedirect("orders.jsp");
		}
		else {
			session.setAttribute("orderItemList", orderItemList);
			response.sendRedirect("viewItems.jsp");
		}
	}

}
