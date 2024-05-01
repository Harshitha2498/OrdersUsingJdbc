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
 * Servlet implementation class AddItemServlet
 */
public class AddOrderItemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddOrderItemServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		int orderId = Integer.parseInt(request.getParameter("orderId").toString());
		int itemId = Integer.parseInt(request.getParameter("itemId").toString());
		int itemQuantity = Integer.parseInt(request.getParameter("quantity").toString());
		if (itemQuantity > 0) {
			OrderDao.saveOrderItem(orderId, itemId, itemQuantity);
			List<Order_Item> orderItemList = OrderDao.getAllOrderItems(orderId);
			session.setAttribute("orderItemList", orderItemList);
		}
		request.getRequestDispatcher("viewItems.jsp").forward(request, response);
	}
}
