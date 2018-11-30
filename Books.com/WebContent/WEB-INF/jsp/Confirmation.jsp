<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="<c:url value="/css/mystyle.css" />" rel="stylesheet">
<title>Insert title here</title>
</head>
<body>
<%@ include file = "header.jsp" %>
Hi ${sessionScope.shipInfo.name}, your order has been
	confirmed <br>
Confirmation Code : ${sessionScope.confirmationCode}
<%@ include file = "footer.jsp" %>
</body>
</html>