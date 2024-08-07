<%@page import="com.vedant_servelets.products.Products"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>







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

.cardBtn {
	width: 12em;
	height: 2.33em;
	cursor: pointer;
	background: white;
	border: 1px solid #938b8b;
}

.paginationBtn {
	display: flex;
	justify-content: space-evenly;
	align-items: center;
}

.paginationBtn>button {
	width: 100px;
	height: 27px;
	font-size: large;
	font-weight: bold;
	background-color: black;
	color: white;
}

.mainSection {
	display: flex;
}

.mainSection .filter {
	background-color: #80808038;
	max-width: 54%;
	min-width: 13%;
	height: fit-content;
}
.header2{
	    display: flex;
    justify-content: space-between;
    align-items: center;
}
.sort{
/*     border: 1px solid gray;
 */    width: 13em;
/*     padding: 7px;      
 */    margin-right: 26px;
/*         height: fit-content;
 *//*     display: inline-block;
 */
}

.sortLabel{
	    border: 1px solid gray;
    width: inherit;
    padding: 7px;
    margin-right: 26px;
    background: #efebeb;
    cursor: pointer;
}

.show{
		display:block !important;
}

.sortDropDown{
	position: absolute;
    border: 1px solid;
    width: inherit;
    padding: 7px;
    right: 10px;
    top: 125px;
    z-index: 11;
    background: #efebeb;
    display: none;
}
.sortDropDown li{
	list-style:none;
	padding:10px 0px;
	cursor: pointer;
}

.sortDropDown li:hover{
background-color: white;
}
</style>
<script type="text/javascript">
	let products =
<%=request.getAttribute("products")%>
	;
	console.log(products);
	let maxPriceProduct = <%= request.getAttribute("maxprice")%>
	let categories = <%= request.getAttribute("categories")%>
	let brands = <%= request.getAttribute("brands")%>
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
	<main>
		<header>
			<t:navbar />
		</header>
		<section class="mainSection">

			<aside class="filter">
				<h1 style="text-align: center;">Filters</h1>

				<hr>
				
					<div style="padding-left: 15px" class="filterPrice" >
						<h1 style="text-align: center;">Price</h1>

						<div id="priceFilter">
							<div>
								<input type="checkbox" value="0-5"> <label>$. 0 to $. 5</label>
							</div>
							<div>
								<input type="checkbox" value="5-10"> <label>$. 5 to $. 10</label>
							</div>
						</div>
					</div>
					<hr>
					<div style="padding-left: 15px" class="filterCate" >
						<h1 style="text-align: center;">Categories</h1>

						<div id="filterCate">
							<div>
								<input type="checkbox"> <label>$. 0 to $. 5</label>
							</div>
							<div>
								<input type="checkbox"> <label>$. 5 to $. 10</label>
							</div>
						</div>
					</div>
					
					<hr>
					<div style="padding-left: 15px" class="filterCate" >
						<h1 style="text-align: center;">Brands</h1>
						
						<div id="filterBrand">
						
						</div>
					</div>
			</aside>
			<section>
			<div class="header2">
			<div style="text-align: center; margin-left: 97px;    font-size: larger;">
				
					<h1>All Products</h1>
				</div>
				<div class="sort">
					<div id="sortLabel" class="sortLabel">
						Sort by :- <span id="sortBy">Select Filter</span><span class="sortArrow">&#11167;</span>
					</div>
					<div class="sortDropDown">
					<hr>
						<ul>
							<li class="sortValue" list-data="0">Select Filter </li>
							<li class="sortValue" list-data="1">Price: High To Low</li>
							<li class="sortValue" list-data="-1">Price: Low To High</li>
						</ul>
					</div>
					<div></div>
				</div>
			</div>
				
				<hr />
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

				<div class="paginationBtn">
					<button id="prev" class="prev" onclick="handlePrev()">Prev</button>
					<button id="next" class="next" onclick="handleNext()">Next</button>
				</div>

			</section>
		</section>


	</main>

	<script src="./js/Products.js" type="text/javascript"></script>

</body>
</html>