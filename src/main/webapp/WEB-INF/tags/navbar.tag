
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
</style>
</head>
<body>
	<nav class="navbar">
        <div class="navbar-brand">MyWebsite</div>
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
</body>
</html>