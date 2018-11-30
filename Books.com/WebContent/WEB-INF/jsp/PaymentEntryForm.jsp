<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Payment Information Page</title>
<link href="<c:url value="/css/mystyle.css" />" rel="stylesheet">
</head>
<body>
<%@ include file = "header.jsp" %>

	<form:form method="post" action="submitPayment" modelAttribute="payInfo">
  	<table>
		<tr>
			<td>Card Holder Name:</td>
			<td><form:input path="cardHolderName" /></td>
			<td><form:errors path="cardHolderName" cssClass="error"></form:errors></td>
		</tr>
		<tr>
			<td>Credit Card Number:</td>
			<td><form:input id="creditCardTxtBox" path="creditCardNum" /></td>
			<td><form:errors path="creditCardNum" cssClass="error"></form:errors></td>
			
		</tr>
		<tr>
			<td>CVV Code:</td>
			<td><form:input path="cvvCode" /></td>
			<td><form:errors path="cvvCode" cssClass="error"></form:errors></td>
		</tr>		
		<tr>
			<td>Expiry Date:</td>
							
			<td><form:input path="expDate" type="date" /></td> 			
			<td><form:errors path="expDate" cssClass="error"></form:errors></td>
		</tr>		
		</table>
		<input type="submit" value="Submit" />
	</form:form>
<%@ include file = "footer.jsp" %>

</body>
</html>