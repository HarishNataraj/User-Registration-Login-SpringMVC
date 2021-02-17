<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<c:set var="contextPath" value="${pageContext.request.contextPath}" />
	<form:form action="addTransaction" modelAttribute="transaction">
		<form:input path="transactionAmount"
			placeholder="Enter transaction amount" />
		<br>
		<br>
		<form:input type="date" path="transactionDate" />
		<br>
		<br>
		Select transaction mode:   
        Cash <form:radiobutton path="transactionMode" value="Cash" />  
        UPI <form:radiobutton path="transactionMode" value="UPI" />  
        Card <form:radiobutton path="transactionMode" value="Card" />
		<br>
		<br>
		<input type="submit" value="Done" />
	</form:form>
	<p>${message}</p>
	<a href="${contextPath}/category/">Go back to catgeory page</a>
</body>
</html>