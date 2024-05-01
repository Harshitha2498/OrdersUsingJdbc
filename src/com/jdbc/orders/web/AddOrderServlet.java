package com.jdbc.orders.web;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.jdbc.orders.Dao.OrderDao;
import com.jdbc.orders.model.Orders;

/**
 * Servlet implementation class AddOrderServlet
 */
//@WebServlet("/AddOrderServlet")
public class AddOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       OrderDao orderDao=new OrderDao();
      
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddOrderServlet() {
        super();
    }
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		Orders order=new Orders();
		order.setOrderDate(new Date());
		orderDao.createNewOrder(order);
		session.setAttribute("orderId", order.getOrderId());
		session.setAttribute("currentDate",order.getOrderDate());
		session.setAttribute("orderItemList", null);
		response.sendRedirect("viewItems.jsp");
	}
}
