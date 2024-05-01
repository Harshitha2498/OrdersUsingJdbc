<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List"%>
<%@ page import="com.jdbc.orders.model.Items"%>
<%@ page import="com.jdbc.orders.model.Order_Item"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Items</title>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<style>
.card {
	box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2);
	max-width: 300px;
	/* 	margin: auto; */
	text-align: center;
	font-family: arial;
	padding: 1rem;
}

.title {
	color: grey;
	font-size: 18px;
}

a {
	border: solid;
	outline: 0;
	display: inline-block;
	padding: 8px;
	color: white;
	background-color: green;
	text-align: center;
	cursor: pointer;
	/* width: 100%; */
	font-size: 18px;
}

img {height =
	
}

a:hover {
	opacity: 0.7;
}
</style>
</head>
<body>
	<h2 style="text-align: center">Ordered Items</h2>
		<div class="container-fluid">
			<div class="row">
				<div class="col-md-6">
					<form
						action="createOrderItems?orderId=<%=session.getAttribute("orderId")%>"
						method="post" style="text-align: left">
						<input type="submit" value="Add Item" class="btn btn-primary">
					</form>
				</div>
				<div class="col-md-6">
					<form action="orders.jsp" style="text-align: right">
						<input type="submit" value="back" class="btn btn-primary">
					</form>
				</div>
			</div>
		</div>
	<div class="container-fluid">
		<div class="row">
			<%
				List<Order_Item> orderItemList = (List<Order_Item>) session.getAttribute("orderItemList");
			if (orderItemList != null && orderItemList.size() > 0) {
				for (Order_Item oi : orderItemList) {
			%>
			<div class="col-md-3 h-100 d-flex" *ngFor="let card of cards">
				<div class="card h-100">
					<img src="img/<%=oi.getItems().getItemId()%>.jpg" alt="Items"
						class="card-img-top img-fluid"
						style="width: 100%; height: 200px; object-fit: cover;">
					<h1><%=oi.getItems().getItemName()%></h1>
					<p>
						Price :
						<%=oi.getItems().getItemPrice()%></p>
					<p>
						Quantity :
						<%=oi.getQuantity()%>
					</p>
					<div class="d-flex align-items-center">
						<a href="updateOrderItem?orderItemId=<%=oi.getId()%>"
							class="btn btn-secondary ">Edit</a> <a
							href="deleteOrderItem?orderItemId=<%=oi.getId()%>&orderId=<%=oi.getOrders().getOrderId()%>"
							class="btn btn-danger">Delete</a>
					</div>
				</div>
			</div>
			<%
				}
			} else {
			%>
			<h2 style="text-align: center;">No Data Available</h2>
			<%
				}
			%>
		</div>
		<!-- Bootstrap JS (Optional) -->
		<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
		<script
			src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
		<script
			src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>