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
/**
 * Servlet implementation class CreateOrderServlet
 */
public class CreateOrderItemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateOrderItemServlet() {
        super();
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		List<Items> itemList=OrderDao.getAllItems();
		int orderId=Integer.parseInt(request.getParameter("orderId"));
		session.setAttribute("orderId",orderId);
		session.setAttribute("itemList", itemList);
		response.sendRedirect("additems.jsp");
	}
}
