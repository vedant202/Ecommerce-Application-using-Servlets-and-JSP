<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<div>
		<div>
			<h1>Signup Page</h1>
		</div>

		<div>
			<form action="signup" method="POST">
				<div class="row">
					<div class="col1">
						<label for="fname">First Name :-</label>
					</div>
					<div class="col2">
						<input type="text" name="fname" id="fname" />
					</div>
				</div>
				
				<div class="row">
					<div class="col1">
						<label for="lname">Last Name :-</label>
					</div>
					<div class="col2">
						<input type="text" name="lname" id="lname" />
					</div>
				</div>
				
				<div class="row">
					<div class="col1">
						<label for="lname">Phone :-</label>
					</div>
					<div class="col2">
						<input type="number" name="number" id="number" />
					</div>
				</div>
				
				<div class="row">
					<div class="col1">
						<label for="email">Email :-</label>
					</div>
					<div class="col2">
						<input type="email" name="email" id="email" />
					</div>
				</div>
				
				<div class="row">
					<div class="col1">
						<label for="address1">Address 1 :-</label>
					</div>
					<div class="col2">
						<input type="text" name="address1" id="address1" />
					</div>
				</div>
				
				<div class="row">
					<div class="col1">
						<label for="address2">Address 2 :-</label>
					</div>
					<div class="col2">
						<input type="text" name="address2" id="address2" />
					</div>
				</div>
				
				<div class="row">
					<div class="col1">
						<label for="city">City :-</label>
					</div>
					<div class="col2">
						<input type="text" name="city" id="city" />
					</div>
				</div>
				
				<div class="row">
					<div class="col1" >
						<label for="state">State :-</label>
					</div>
					<div class="col2">
						<input type="text" name="state" id="state" />
					</div>
				</div>
				
				<div class="row">
					<div class="col1">
						<label for="password">Password :-</label>
					</div>
					<div class="col2">
						<input type="password" name="password" id="password" />
					</div>
				</div>
				
				<div class="row">
					<div class="col1">
						<label for="cpassword">Confirm Password :-</label>
					</div>
					<div class="col2">
						<input type="password" name="cpassword" id="cpassword" />
					</div>
				</div>
				
				<div class="btnrow">
					<button class="btn" id="btn">SUBMIT</button>
				</div>
				
			</form>
		</div>

	</div>
</body>
</html>