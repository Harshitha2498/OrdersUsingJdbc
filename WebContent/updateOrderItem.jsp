<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="com.jdbc.orders.model.Items"%>
<%@ page import="com.jdbc.orders.model.Order_Item"%>
<%@ page import="java.util.List"%>
<%@ page import="com.jdbc.orders.Dao.OrderDao"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Updated order Items</title>
<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
	rel="stylesheet">
<style>
.card {
	width: 50%;
	margin: 0 auto;
	float: none;
	margin-bottom: 10px;
	font-size: 1em;
}
</style>
</head>
<body>
	<form action="saveUpdateOrderItem" method="Post">
		<%
			Order_Item oi = (Order_Item) session.getAttribute("orderItem");
		List<Items> itemsList = (List<Items>) session.getAttribute("itemList");
		if (itemsList != null && itemsList.size() > 0) {
		%>
		<div class="card">
			<div class="card-header"
				style="background-color: lightgreen; text-align: center;">Edit
				Items</div>
			<div class="card-body" style="text-align: center;">
				<label>Item Name : </label><select name="itemId"
					style="width: 188px">
					<%
						for (Items i : itemsList) {

						if (oi.getItems().getItemId() == i.getItemId()) {
					%>
					<option id="itemname" value="<%=i.getItemId()%>"
						selected="selected"><%=i.getItemName() + " - " + i.getItemPrice()%>rs
					</option>
					<%
						} else {
					%>
					<option id="itemname" value="<%=i.getItemId()%>"><%=i.getItemName() + " - " + i.getItemPrice()%>rs
					</option>
					<%
						}
					}
					%>
				</select>
				<div>
					<label>Item Quantity : </label><input type="text" name="quantity"
						value=<%=oi.getQuantity()%>>
				</div>
				<input type="hidden" name="orderItemId" value=<%=oi.getId()%>>
			</div>
			<%
				} else {
			%>
			<td>No data available</td>
			<%
				}
			%>
			<div class="card-footer">
				<div class="btn-wrapper text-center">
					<input class="btn btn-primary col-md-3" type="submit"
						value="Submit"> <input class="btn btn-secondary col-md-3"
						type="reset" value="reset">
				</div>
			</div>
		</div>
	</form>
	<script>
		document.getElementById("itemname").readOnly = true;
	</script>
</body>
</html>