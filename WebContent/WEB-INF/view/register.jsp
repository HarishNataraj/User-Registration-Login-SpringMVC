<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
		<form:form action="register" modelAttribute="user">
			<form:input path="firstName" placeholder="FirstName" />
			<br>
			<br>
			<form:input path="lastName" placeholder="lastName" />
			<br>
			<br>
			<form:input type="email" path="email" placeholder="Email" />
			<br>
			<br>
			<form:input type="password" path="password" placeholder="Password" />
			<br>
			<br>
			<input type="submit" value="Register" />
		</form:form>
		<p>${message}</p>
		<a href="login">Login</a>
</body>
</html>