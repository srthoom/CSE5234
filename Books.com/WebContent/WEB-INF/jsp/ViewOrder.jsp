<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>View Order</title>
<link href="<c:url value="/css/mystyle.css" />" rel="stylesheet">
</head>
<body>
<%@ include file = "header.jsp" %>
	Order List
	<br>
	<table>
		<c:forEach var="lineitem" items="${sessionScope.order.items}"> 
			<c:if test = "${lineitem.quantity > 0}">
				<tr>
			    	<td><c:out value="${lineitem.item_name}: "/></td>
			    	<td><c:out value="${lineitem.quantity}"/></td>
			    </tr>
			</c:if>
		</c:forEach>
	</table>
	<br/>Shipping Information<br/>
	<table>
		<tr><td><c:out value="Name: ${sessionScope.shipInfo.name}"/></td></tr>
		<tr><td><c:out value="Address Line1:${sessionScope.shipInfo.addressLine1}"/></td></tr>
		<tr><td><c:out value="Address Line2:${sessionScope.shipInfo.addressLine2}"/></td></tr>
		<tr><td><c:out value="City: ${sessionScope.shipInfo.city}"/></td></tr>
		<tr><td><c:out value="State: ${sessionScope.shipInfo.state}"/></td></tr>
		<tr><td><c:out value="Zip: ${sessionScope.shipInfo.zip}"/></td></tr>
	</table>
	
	<form:form method="post" action="confirmOrder">
		<input type="submit" value="Submit" />
	</form:form>
<%@ include file = "footer.jsp" %>
</body>
</html>