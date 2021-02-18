<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<style>
.error {
	color: red
}
</style> 
</head>
<body>
	<c:set var="contextPath" value="${pageContext.request.contextPath}" />
	<form:form action="addTransaction" modelAttribute="transaction">
		<form:input path="transactionAmount"
			placeholder="Enter transaction amount" id="transactionAmount" />
		<form:errors path="transactionAmount" cssClass="error" />
		<br>
		<br>
		<form:input type="date" path="transactionDate" id="transactionDate" />
		<form:errors path="transactionDate" cssClass="error" />
		<br>
		<br> 
		Select transaction mode:   
        Cash <form:radiobutton path="transactionMode" value="Cash" id="transactionMode" />  
        UPI <form:radiobutton path="transactionMode" value="UPI" id="transactionMode" />  
        Card <form:radiobutton path="transactionMode" value="Card" id="transactionMode" />
		<form:errors path="transactionMode" cssClass="error" />
		<br>
		<br>
		<input type="submit" value="Done" />
	</form:form>
	<p>${message}</p>
	<a href="${contextPath}/category/">Back</a>
</body>
</html>