<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>


<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<table width="100" hieght="100" bgcolor="cyan" bordercolor="red">
		<c:forEach var="dto" items="${listDto}">
			<c:out value="${dto.title}" /><br>
			<c:out value="${dto.author}" /><br>
			<c:out value="${dto.filecontent}" /><br>

				<img src="data:image/jpg;base64,${dto.imagecontent}" width="200"
					height="200" /><br>
			

			<c:out value="${dto.discription}" /><br>



 
			<a href="Read?bookUrl=${dto.bookUrl}">Read</a> <b><blink>----</blink></b><a href="Edit?id=${dto.id}">add</a><b><blink>----</blink></b>
			<a href="Delete">Delete</a><br><br>
			<a href="Download">Download</a>
		</c:forEach>
	</table>


</body>
</html>