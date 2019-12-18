<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login page</title>
</head>
<body>
	<form:form method="post" modelAttribute="registerfrm">

		<fieldset>
			<legend> please login </legend>
			<label> Username :</label> <input type="email" name="email"><br>
			<label> Password : </label> <input type="password" name="pass"><br>
			<input type="submit" value="submit">

		</fieldset>
	</form:form>
	
	<a href="registration">Registration </a>
	
</body>
</html>