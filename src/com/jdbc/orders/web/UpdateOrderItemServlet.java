package com.jdbc.orders.web;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jdbc.orders.Dao.OrderDao;
import com.jdbc.orders.model.Items;
import com.jdbc.orders.model.Order_Item;
/**
 * Servlet implementation class UpdateOrderItemServlet
 */
public class UpdateOrderItemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UpdateOrderItemServlet() {
		super();
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		int orderItemId = Integer.parseInt(request.getParameter("orderItemId"));
		Order_Item oi = OrderDao.findOrderItemById(orderItemId);
		session.setAttribute("orderItem", oi);
		List<Items> itemList = OrderDao.getAllItems();
		session.setAttribute("itemList", itemList);
		response.sendRedirect("updateOrderItem.jsp");
	}
}
