<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<div>Login Method</div>
	
	<div>
		<form method="post" action="/FilterTuts/login">
			<div>
				<div>
				<label for="email">Email</label>
				<input type="email" name='email'></input>
			</div>
			<div>
				<div>
				<label for="email">Password</label>
				<input type="password" name='password'></input>
			</div>
			<button type="submit">submit</button>
			</div>
		</form>
	</div>
</body>
</html>