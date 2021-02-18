<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
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

.error {
	color: red
}
</style>
</head>
<body>
	<c:set var="contextPath" value="${pageContext.request.contextPath}" />
	<form:form action="addCategory" method="post" modelAttribute="category">
		<form:input path="categoryName" placeHolder="Category Name" />
		<form:errors path="categoryName" cssClass="error" />
		<br>
		<br>
		<input type="submit" value="Add Category" />
	</form:form>
	<br>
	<br>
	<c:if test="${categoryList.size() == 0 }">
		<h3>Category list is empty</h3>
	</c:if>
	<c:if test="${categoryList.size() >0 }">
		<table>
			<tr>
				<th>Category</th>
				<th>Transactions</th>
				<th>Add Transaction</th>
				<th>Delete Category</th>
			</tr>
			<c:forEach var="categoryDto" items="${categoryList}">
				<tr>
					<td>${categoryDto.category_name}</td>
					<td><a
						href="${contextPath}/transactions/viewTransactions/${categoryDto.category_id}">View
							Transactions</a></td>
					<td><a
						href="${contextPath}/transactions/${categoryDto.category_id}">Add
							Transaction</a></td>
					<td><a href="deleteCategory/${categoryDto.category_id}">Delete
							Category</a></td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
	<p>${message}</p>
	<a href="${contextPath}/logout">Logout</a>
</body>
</html>