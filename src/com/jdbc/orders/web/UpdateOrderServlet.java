package com.jdbc.orders.web;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jdbc.orders.Dao.OrderDao;
import com.jdbc.orders.model.Order_Item;
/**
 * Servlet implementation class UpdateOrderServlet
 */
//@WebServlet("/UpdateOrderServlet")
public class UpdateOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateOrderServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		int orderId=Integer.parseInt(request.getParameter("orderId").toString());
		session.setAttribute("orderId", orderId);
		List<Order_Item> orderItemList = OrderDao.findOrderItemByOrderId(orderId);
		session.setAttribute("orderItemList", orderItemList);
		System.out.println(orderId);
		response.sendRedirect("viewItems.jsp");
	}
}
