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
 * Servlet implementation class SaveUpdateOrderItemServlet
 */
public class SaveUpdateOrderItemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SaveUpdateOrderItemServlet() {
        super();
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		int orderItemId=Integer.parseInt(request.getParameter("orderItemId").toString());
		int itemId=Integer.parseInt(request.getParameter("itemId").toString());
		int itemQuantity=Integer.parseInt(request.getParameter("quantity").toString());
		Order_Item oi=OrderDao.findOrderItemById(orderItemId);
		if(itemQuantity<=0) {
			OrderDao.deleteOrderItem(orderItemId);
		}
		else if(itemQuantity>0) {
		OrderDao.updateOrderItem(oi,orderItemId,itemId,itemQuantity);}
		List<Order_Item> orderItemList = OrderDao.findOrderItemByOrderId(oi.getOrders().getOrderId());
		session.setAttribute("orderItemList", orderItemList);
		response.sendRedirect("viewItems.jsp");
	}
}
