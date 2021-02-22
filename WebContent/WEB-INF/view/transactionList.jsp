<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<style>
table {
	font-family: arial, sans-serif;
	border-collapse: collapse;
	width: 100%;
}

td, th {
	border: 1px solid #dddddd;
	text-align: left;
	padding: 8px;
}

tr:nth-child(even) {
	background-color: #dddddd;
}
</style>
</head>
<body>
	<c:set var="contextPath" value="${pageContext.request.contextPath}" />
	<h3>Transaction list</h3>
	<c:if test="${transactionList.size() == 0 }">
		<h3>Transaction list is empty</h3>
	</c:if>
	<c:if test="${transactionList.size() >0 }">
		<table>
			<tr>
				<th>S.NO</th>
				<th>Transaction Amount</th>
				<th>Transaction Date</th>
				<th>Transaction Mode</th>
				<th>Delete</th>
			</tr>
			<c:forEach var="transactionDto" items="${transactionList}"
				varStatus="loop">
				<tr>
					<td>${loop.index+1}</td>
					<td>${transactionDto.transactionAmount}</td>
					<td>${transactionDto.transactionMode}</td>
					<td>${transactionDto.transactionDate}</td>
					<td><a
						href="${contextPath}/transactions/deleteTransaction/${transactionDto.transactionId}">Delete</a></td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
	<h2>Total expense : ${expense}</h2>
	<a href="${contextPath}/category/">Back</a>
</body>
</html>