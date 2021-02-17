<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form action="login" method="post">
		<input type="email" name="email" placeHolder="Email" /><br>
		<br> <input type="password" name="password"
			placeHolder="Password" /><br>
		<br> <input type="submit" value="Login" />
	</form>
	<p>${message}</p>
</body>
</html>