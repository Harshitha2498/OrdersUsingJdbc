<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page
	import="com.jdbc.orders.model.Orders,java.util.List,com.jdbc.orders.Dao.OrderDao"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Orders</title>
<link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"	rel="stylesheet"> 
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<style>
form{
 text-align: right;
}
table {
	border-collapse: collapse;
	width: 100%;
	border: 1px solid black;
	text-align:center;
}

th, td {
	text-align: left;
	padding: 8px;
	text-align:center;
}

tr:nth-child(even) {
	background-color: #f2f2f2
}

th {
	background-color: #04AA6D;
	color: white;
}
.center {
  margin-left: auto;
  margin-right: auto;
}
</style>
</head>
<body>
<div class=container>
	<h3 id="h3" style="text-align: center">Welcome to DashBoard</h3>
	<form action="addOrder" method="post" style="text-allign:right">
		<input type="submit" value="Add Order" class="btn btn-primary">
	</form>
	<div>
	<table class="center">
		<tr>
			<th>Order Id</th>
			<th>Order Date</th>
			<th>Edit</th>
			<th>Delete</th>
		</tr>
		<%
			List<Orders> orders = new OrderDao().getAllOrders();
		for (Orders o : orders) {
		%>
		<tr>
			<td><%=o.getOrderId()%></td>
			<td><%=o.getOrderDate()%></td>
			<td><a href="updateOrder?orderId=<%=o.getOrderId()%>"><span class="glyphicon glyphicon-pencil"></span></a></td>
			<td><a href="deleteOrder?orderId=<%=o.getOrderId()%>"><span class="glyphicon glyphicon-trash" style="color:red"></span></a></td>
		</tr>
		<%
			}
		%>
	</table>
	</div>
</div>
<script>
document.getElementById("h3").style.color ="purple";

</script>
</body>
</html>