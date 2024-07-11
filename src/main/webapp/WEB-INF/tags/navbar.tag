
<%-- <%@page import="org.apache.commons.lang3.StringUtils"%>
 --%><%@ tag description="Reusable navbar" pageEncoding="UTF-8"%>
<%@ tag import="org.apache.commons.lang3.StringUtils"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<style type="text/css">
	.navbar {
    display: flex;
    justify-content: space-between;
    align-items: center;
    background-color: #ffffff;
    border-bottom: 1px solid #eaeaea;
    padding: 10px 20px;
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

.navbar-brand {
    font-size: 24px;
    font-weight: bold;
    color: #333333;
}

.navbar-menu {
    list-style: none;
    display: flex;
    margin: 0;
    padding: 0;
}

.navbar-item {
    margin-left: 20px;
}

.navbar-link {
    text-decoration: none;
    color: #333333;
    font-size: 18px;
    transition: color 0.3s ease;
}

.navbar-link:hover {
    color: #007bff;
}

.cart-icon img {
    width: 24px;
    height: 24px;
    vertical-align: middle;
}

.searchbox{
    	width: 600px;
    	background: #efebeb;
    	border-radius: 4px;
    	
    }
    .row{
    	display: flex;
    	align-items: center;
    	padding: 10px 20px;
    }
    .inputSearch{
    	flex:1;
    	height: 29px;
    	background: transparent;
    	border: 0;
    	outline: 0;
    	font-size: 18px;
    	color: #333;
    	
    }
    #searchBtn{
    border: none;
    	background-color: inherit;
    	cursor: pointer;
    }
    ::placeholder{
    	color:#555;
    }
    
    .result-box{
    	    position: absolute;
    z-index: 100;
    width: inherit;
    background: inherit;
    border-radius: 7px;
        
    }
    .result-box > ul{
    	max-height: 300px;
    overflow: scroll;
    }
    .result-box ul{
    	border-top: 1px solid #999;
    	padding:15px 10px;
    }
	
	.result-box ul li{
		list-style:none;
		border-radius: 3px;
		padding:15px 10px;
		cursor:pointer;
		text-align: left;
	}
	
	.result-box ul li:hover{
		background: #e9f3ff;
	}
	
@media (max-width: 768px) {
    .navbar {
        flex-direction: column;
        align-items: flex-start;
    }
    
    .navbar-menu {
        flex-direction: column;
        width: 100%;
    }
    
    .navbar-item {
        margin-left: 0;
        width: 100%;
    }
    
    .navbar-link {
        padding: 10px 0;
        display: block;
        width: 100%;
        text-align: left;
    }
    }
</style>
</head>
<body>
	<nav class="navbar">
        <div class="navbar-brand">MyWebsite</div>
        <div class="searchbox">
        	<div class="row">
        		<input type="text" id="inputSearch" class="inputSearch" placeholder="Search...." autocomplete="off" data-input-search>
        		<button type="button" id="searchBtn" data-search-button><svg xmlns="http://www.w3.org/2000/svg" x="0px" y="0px" width="20" height="20" viewBox="0 0 50 50">
<path d="M 21 3 C 11.601563 3 4 10.601563 4 20 C 4 29.398438 11.601563 37 21 37 C 24.355469 37 27.460938 36.015625 30.09375 34.34375 L 42.375 46.625 L 46.625 42.375 L 34.5 30.28125 C 36.679688 27.421875 38 23.878906 38 20 C 38 10.601563 30.398438 3 21 3 Z M 21 7 C 28.199219 7 34 12.800781 34 20 C 34 27.199219 28.199219 33 21 33 C 13.800781 33 8 27.199219 8 20 C 8 12.800781 13.800781 7 21 7 Z"></path>
</svg></button>
        	</div>
        	
        	<div class="result-box" data-input-search-container>
        		<template data-user-template>
        			<ul class="list" data-list>
        		</ul>
        		</template>
        	</div>
        	
        </div>
        <ul class="navbar-menu">
            <li class="navbar-item"><a href=index class="navbar-link">Home</a></li>
            <li class="navbar-item"><a href="products" class="navbar-link">Products</a></li>
            <li class="navbar-item"><a href="orders" class="navbar-link cart-icon"><img src="cart-icon.png" alt="Cart"></a></li>
           <% 
            String loginLogoutTag = "";
            session = request.getSession(false);
	System.out.println("Session is null :- " + session != null);
	System.out.println("Session user :- " + (String) request.getAttribute("user"));
	if (session != null) {
		String user = (String) session.getAttribute("user");
		if (!StringUtils.isEmpty(user)) {
			loginLogoutTag = "<a class=\"navbar-link\" href=\"logout\">Logout</a>";
		} else {
			loginLogoutTag = "<a class=\"navbar-link\" href=\"login\">Login</a>";
		}
	} else {
		loginLogoutTag = "<a class=\"navbar-link\" href=\"login\">Login</a>";
	}
	%>
            <li class="navbar-item"><%=loginLogoutTag%></li>
	
        </ul>
    </nav>
    
    <script type="text/javascript" src="./js/navbar.js" defer="defer"></script>
</body>
</html>