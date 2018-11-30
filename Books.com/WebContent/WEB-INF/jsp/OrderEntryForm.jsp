<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Order Entry Form</title>
<spring:url
	value="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"
	var="jqueryJs" />
<spring:url value="/js/jScripts.js" var="Jscripts" />
<script src="${jqueryJs}"></script>
<script src="${Jscripts}"></script>

<link href="<c:url value="/css/mystyle.css" />" rel="stylesheet">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
	<%@ include file="header.jsp"%>
	<div>
		<c:if test="${not empty requestScope.errorMsg}">
			<div class="alert alert-danger">
				<strong>${requestScope.errorMsg}</strong>
			</div>
		</c:if>
	</div>
	<form:form method="post"
		action="http://localhost:9080/Books.com/purchase/submitItems"
		modelAttribute="order">
		<table>
			<tr>
				<th>Item</th>
				<th>Quantity</th>
			</tr>
			<c:forEach items="${order.items}" var="lineitem" varStatus="i">
				<tr>

					<td><form:label path="items[${i.index}].item_name"> ${lineitem.item_name}</form:label></td>
					<td><form:input path="items[${i.index}].quantity" value="${lineitem.quantity}" /></td>
				</tr>
			</c:forEach>
		</table>
		
		<input type="submit" value="Submit" />
				

	</form:form>
	<%@ include file="footer.jsp"%>
</body>
</html>