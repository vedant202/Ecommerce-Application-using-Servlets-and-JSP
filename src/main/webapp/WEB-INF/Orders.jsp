<%@page import="dtos.CartDto"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<t:navbar />
	<h1><%= request.getAttribute("userName") %> Your Orders</h1>
	
	<% 
		List<CartDto> cartDtos = (List<CartDto>) request.getAttribute("carts");
		
	%>
	<%= cartDtos.size()==0 || cartDtos==null?"<h1>Carts is Empty</h1>":"" %>
	<%= cartDtos.size()>=0|| cartDtos!=null?cartDtos:null%>
</body>
</html>