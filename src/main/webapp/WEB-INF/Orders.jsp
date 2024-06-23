<%@page import="dtos.CartDto"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>

<style type="text/css">
	.card{
		display: flex;
		align-items: center;
		border: 1px solid darkgray;
    	width: 55em;
	}
	.col1{
		width: 190px;
		
	}
	.col2{
		 width: 100%;
	    display: flex;
	    flex-direction: column;
	}
	.title{
		font-weight: bold;
    	font-size: 18px;
	}
	.brand{
		font-size: larger;
	}
	.remove{
		width: 7em;
	    height: 2em;
	    border: 1px solid white;
	    background-color: black;
	    color: white;
	    /* font-weight: bolder; */
	    font-size: 15px;
	    border-radius: 5px;
	}
</style>
<script>
	let orders = <%=request.getAttribute("carts") %>
</script>

</head>
<body>
	<t:navbar />
	<h1><%=request.getAttribute("userName")%>
		Your Orders
	</h1>

<%=request.getAttribute("carts") %>
	<div id="container" class="container">
		
		
	</div>
	<div class="purchaseBtn">
			<button type="button"  class="placeOredrBtn"><a href="http://localhost:8080/FilterTuts/placeOrder"> Place Order </a></button>
		</div>
<script type="text/javascript" src="./js/Orders.js"></script>
</body>
</html>