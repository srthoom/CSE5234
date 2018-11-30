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
<spring:url value="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js" var="jqueryJs" />
<spring:url value="/js/jScripts.js" var="Jscripts" />
<script src="${jqueryJs}"></script>
<script src="${Jscripts}"></script>

<title>Insert title here</title>
</head>
<body>
<div id="top">
    <h1>Welcome to BookMart</h1>
    <p></p>
  </div>
  
   <div>
     <ul>
     
       <li><a href = "<c:url value = "/"/>">Home</a></li>
       <li><a href = "<c:url value = "/purchase"/>">Order Books</a></li>
       <li><a href = "<c:url value = "/aboutus"/>">About Us</a></li>
       <li><a href = "<c:url value = "/contactus"/>">Contact Us</a></li>       
       
       
       
     </ul>
    </div>

</body>
</html>