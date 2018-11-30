<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Shipping Entry Form</title>
<link href="<c:url value="/css/mystyle.css" />" rel="stylesheet">
</head>
<body>
<%@ include file = "header.jsp" %>
<form:form modelAttribute="shipInfo" action="submitShipping" method="POST">
   <table>
       <tr>
           <td>name:</td>
           <td><form:input path="name" /></td>
           <td><form:errors path="name" cssClass="error"></form:errors></td>
       </tr>
       <tr>
           <td>addressLine1:</td>
           <td><form:input path="addressLine1" /></td>
           <td><form:errors path="addressLine1" cssClass="error"></form:errors></td>
       </tr>
       <tr>
           <td>addressLine2:</td>
           <td><form:input path="addressLine2" /></td>
           <td><form:errors path="addressLine2" cssClass="error"></form:errors></td>
       </tr>
       <tr>
           <td>city:</td>
           <td><form:input path="city" /></td>
           <td><form:errors path="city" cssClass="error"></form:errors></td>
       </tr>
       <tr>
           <td>state:</td>
           <td><form:input path="state" /></td>
           <td><form:errors path="state" cssClass="error"></form:errors></td>
       </tr>
       <tr>
           <td>zip:</td>
           <td><form:input path="zip" /></td>
           <td><form:errors path="zip" cssClass="error"></form:errors></td>
       </tr>
       
   </table>
   <input type="submit" value="Submit" />
</form:form>
<%@ include file = "footer.jsp" %>

</body>
</html>