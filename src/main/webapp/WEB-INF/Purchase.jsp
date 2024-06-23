<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>

<style type="text/css">
.Container {
	display: flex;
	justify-content: center;
}

.card {
	border: 1px solid gray;
	width: 24em;
}

.footer {
	display: flex;
	justify-content: center;
}
</style>
<script type="text/javascript">
	let cartsItems =
<%=request.getAttribute("cartMap")%>
	;
</script>
</head>
<body>
	<div class="Container">


		<div class="card">
			<div class="header">
				<h1>Place Order</h1>
			</div>
			<hr>
			<div class="main">
				<div>
					<h4>
						Order Products :- <span id="totalProducts">4</span>
					</h4>
					<div class="items" id="items">
						
					</div>


					<h4>
						Total items :- <span id="totalItems">4</span>
					</h4>
					<hr>
					<h4>
						Total price :- <span id="totalPrice">$. 100</span>
					</h4>
				</div>
			</div>
			<div class="footer">
				<button class="placeOrder" id="placeOrder">Place Order</button>
			</div>
		</div>
	</div>
	<script type="text/javascript" src="./js/PlaceOrder.js"></script>
</body>
</html>