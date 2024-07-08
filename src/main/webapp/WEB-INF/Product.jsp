<%@page import="dtos.ProductDto"%>
<%@page import="java.util.stream.Collectors"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%
ProductDto product = (ProductDto) request.getAttribute("product");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<style type="text/css">
.main {
	display: flex;
	justify-content: space-around;
	align-items: center;
}

.image img {
	width: 430px;
}

.productReviews {
	display: flex;
	/* width: 100vw; */
	justify-content: center;
	align-items: center;
}

.product-btns {
	display: flex;
	justify-content: space-evenly;
	align-items: center;
}

.product-btn {
	width: 200px;
	height: 38px;
	font-size: larger;
	font-weight: bold;
	background: black;
	color: white;
	border-radius: 6px;
	border: none;
	cursor: pointer;
}
</style>
</head>
<body>
	<t:navbar />
	<div class="container">
		<div class="main">
			<div class="image">
				<img alt="" src="<%=product.getImages().get(0)%>">
			</div>
			<div>
				<div class="product-title">
					<h1><%=product.getTitle()%></h1>
				</div>
				<div class="product-brand">
					<h2><%=product.getBrand()%></h2>
				</div>

				<div class="product-desc">
					<p><%=product.getDescription()%></p>
				</div>
				<div class="product-btns">
					<button class="product-btn">Buy Now</button>
					<button class="product-btn">Add To Cart</button>
				</div>
			</div>


		</div>

		<div class="productReviews">
			<div>
				<div>
					<h1>Reviews</h1>
				</div>
				<%
				String html = "";
				for (var i : product.getReviews()) {
					html += String.format("<div><div><h3>%s</h3></div><div><p>%s</p></div><div>", i.getReviewerName(), i.getComment());
				}
				%>
				<%=html%>

			</div>
		</div>

	</div>
</body>
</html>