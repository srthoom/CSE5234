<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>About Us</title>
<spring:url
	value="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"
	var="jqueryJs" />
<spring:url value="/js/jScripts.js" var="Jscripts" />
<script src="${jqueryJs}"></script>
<script src="${Jscripts}"></script>

<link href="<c:url value="/css/mystyle.css" />" rel="stylesheet">
</head>
<body>
<%@ include file="header.jsp"%>
<p>
Sreeja thoom - Chief Information officer<br>
MBA, The Ohio state university<br>
<br>

Devi - Chief technology officer<br>

Masters, computer science<br>
<br>
 
Veda Uppala - Chief executive officer<br>
Masters, computer science<br>
<br>
 




</p>
<%@ include file="footer.jsp"%>
</body>
</html>