<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>User Registration </title>
</head>
<body>
<fieldset>
<form:form method="post" modelAttribute="registerfrm">

<legend> please fill up the following form </legend>

<dl>

<dt>First Name :</dt>
 <dd><input type="text" name="fname"></dd>
<dt>Last Name :</dt>
<dd><input type="text" name="lname"></dd>
<dt>Email Address :</dt>
<dd><input type="email" name="email"></dd>
<dt> Password : </dt>
<dd><input type="password" name="pass"></dd>
<dt> Nationality </dt>
<dd><input type="text" name="nationality"></dd>


</dl>


</fieldset>
</form:form>
</body>
</html>