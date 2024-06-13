<%@page import="com.vedant_servelets.products.Products"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>







<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<%

%>
<style type="text/css">
body {
    margin: 0;
    padding: 0;
}

#cards {
    display: flex;
    flex-wrap: wrap;
    gap: 20px;
    padding: 20px;
    justify-content: center;
}

.card {
    background-color: #ffffff;
    border: 1px solid #dddddd;
    border-radius: 8px;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
    overflow: hidden;
    max-width: 300px;
    margin: 20px;
    display: flex;
    flex-direction: column;
    transition: transform 0.3s, box-shadow 0.3s;
}

.card:hover {
    transform: translateY(-10px);
    box-shadow: 0 8px 16px rgba(0, 0, 0, 0.2);
}

#cardHeader {
    background-color: #f7f7f7;
    padding: 10px;
    border-bottom: 1px solid #dddddd;
}

#cardHeader h3 {
    margin: 0;
    font-size: 1.5em;
    color: #333333;
}

#cardBody {
    padding: 10px;
    flex-grow: 1;
}

#cardBody p {
    margin: 0;
    color: #666666;
    line-height: 1.5;
}

#cardFooter {
    background-color: #f7f7f7;
    padding: 10px;
    border-top: 1px solid #dddddd;
    text-align: center;
}

#cardFooter p {
    margin: 0;
    color: #999999;
    font-size: 0.9em;
}
.cardBtn{
	width: 12em;
    height: 2.33em;
    cursor: pointer;
    background: white;
    border: 1px solid #938b8b;
}


</style>
<script type="text/javascript">
	let products =<%=request.getAttribute("products")%>;
	console.log(products);
	
</script>
</head>
<body>
	<!-- <nav class="navbar">
        <div class="navbar-brand">MyWebsite</div>
        <ul class="navbar-menu">
            <li class="navbar-item"><a href="#home" class="navbar-link">Home</a></li>
            <li class="navbar-item"><a href="#products" class="navbar-link">Products</a></li>
            <li class="navbar-item"><a href="#cart" class="navbar-link cart-icon"><img src="cart-icon.png" alt="Cart"></a></li>
        </ul>
    </nav> -->
    <t:navbar />

<div style="text-align: center;">
	<h1>All Products
	</h1>
	</div>
	<div id="products">
		<div id="cards">
			<div id="card">
				<div id="cardHeader">
					<h3>Card Header2</h3>
				</div>
				<div id="cardBody">
					<p>Card Body</p>
				</div>
				<div id="cardFooter">
					<p>Card Footer</p>
				</div>
			</div>
			
			<div id="card">
				<div id="cardHeader">
					<h3>Card Header2</h3>
				</div>
				<div id="cardBody">
					<p>Card Body</p>
				</div>
				<div id="cardFooter">
					<p>Card Footer</p>
				</div>
			</div>
		</div>
	</div>
<script src="./js/Products.js" type="text/javascript" ></script>

</body>
</html>