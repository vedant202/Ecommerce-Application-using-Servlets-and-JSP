<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Add New Product</title>

<style>
#search {
	width: 17em;
	height: 2.5em;
}

.tableDiv {
	overflow-x: auto;
}

.table {
	width: 100%;
	height: 100%;
	border: 1px solid;
	border-collapse: collapse;
	text-align: left;
	background-color: #212529;
	color: white;
}

th, td {
	border-bottom: 1px solid rgb(167, 164, 164);
	vertical-align: middle;
	padding: 13px;
}

tr:hover {
	background-color: rgb(53, 52, 52);
}

td>a>button, th>button {
	width: 80px;
	height: 30px;
	border-radius: 7px;
	background-color: rgb(43, 226, 43);
	color: white;
	font-weight: 500;
	cursor: pointer;
}

td>button:hover, th>button {
	background-color: green;
}

.colsNames  th {
	cursor: pointer;
}

#id:hover, #status:hover, #title:hover, #brand:hover, #cate:hover,
	#stock:hover, #price:hover { .uparrow , .downarrow{ display:block;
	
}

}
.uparrow {
	display: none;
}

.downarrow {
	display: none;
	transform: rotate(180deg);
	text-align: end;
}

.cateList {
	display: inline-block;
}

.cateList .anchor {
	position: relative;
	display: inline-block;
	padding: 5px 30px 5px 10px;
	border: 1px solid #ccc;
	background-color: white;
	color: black;
	cursor: pointer;
}

.cateList .anchor:after {
	position: absolute;
	content: "";
	border-left: 2px solid black;
	border-top: 2px solid black;
	padding: 5px;
	right: 10px;
	top: 20%;
	transform: rotate(-135deg);
}

.cateList .anchor:active:after {
	right: 8px;
	top: 21%;
}

.items {
	padding: 2px;
	display: none;
	margin: 0;
	border: 1px solid #ccc;
	border-top: none;
}

.items {
	list-style: none;
	position: absolute;
	width: 9em;
	background: white;
	color: black;
	z-index: 10;
}

.cateList.visible .anchor {
	color: #0094ff;
}

.cateList.visible .items {
	display: block;
}
</style>

<script type="text/javascript">
	var users =
<%=request.getAttribute("users")%>
	;
</script>

</head>
<body>
	<div>





		<div class="tableDiv">
			<table border="0 0 1px solid" class="table">

				<thead>
					<tr>
						<th style="text-align: center;" colspan="6"><h1>ALL
								USERS</h1></th>
						
						<th>
							<div class="cateList" id="cateList" tabindex="100">
								<span class="anchor">Select Category</span>
								<ul class="items" id="cateItems">

								</ul>
							</div>
						</th>
						<th colspan="2"><input type="search" id="search"
							name="search" placeholder="Search"></th>
					</tr>
					<tr class="colsNames">
						<th id="id" scope="col"><div class="uparrow">^</div>ID
							<div class="downarrow">^</div></th>
						<th id="name" scope="col"><div class="uparrow">^</div>NAME
							<div class="downarrow">^</div></th>
					
						<th id="email" scope="col"><div class="uparrow">^</div>EMAIL
							<div class="downarrow">^</div></th>
							
						<th id="address"><div class="uparrow">^</div>ADDRESS
							<div class="downarrow">^</div></th>
							
							<th id="state"><div class="uparrow">^</div>STATE
							<div class="downarrow">^</div></th>
							
							<th id="city"><div class="uparrow">^</div>CITY
							<div class="downarrow">^</div></th>
							
						<th id="phone"><div class="uparrow">^</div>PHONE NUMBER
							<div class="downarrow">^</div></th>
							
						<th id="role"><div class="uparrow">^</div>ROLE
							<div class="downarrow">^</div></th>
						<th>Actions</th>
					</tr>
				</thead>
				<tbody id="tableBody">

				</tbody>
			</table>
		</div>
	</div>

	<script src="../js/AdminUser.js" type="text/javascript"></script>
</body>
</html>
