<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ page
	import="java.sql.*, javax.sql.*, java.util.HashSet, java.util.ArrayList, java.util.Iterator, java.io.*"%>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page session="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<style>
table, th, td {
  border: 1px solid black;
}
th, td {
  padding: 0px;
  text-align: left;
}
</style>

</head>

<body class="fmovies with-slide" data-ts="1560078000">

	<table width="100" hieght="100" bgcolor="cyan" bordercolor="red">
		<tr>
		
		<th>Title</th>
		<th>Author</th>
		<th>Book</th>
		<th>Image</th>
		<th>Discription</th>
		<th>Action</th>
		
		</tr>
		<tr>
		<c:forEach var="dto" items="${listDto}">
			
			<td><c:out value="${dto.title}" /></td>
			<td><c:out value="${dto.author}" /></td>
			<td><c:out value="${dto.filecontent}" /></td>

				<td><img src="data:image/jpg;base64,${dto.imagecontent}" width="100"
					height="50" /></td>
			

			<td><c:out value="${dto.discription}" /></td>
			<td><a href="ReadBook?id=${dto.id}"><button>Read</button></a> <blink>----</blink><a href="EditBook?id=${dto.id}"><button>Edit</button></a><blink>----</blink>
			<a href="Delete?id=${dto.id}"><button > Delete</button></a></td>
			</tr>
		</c:forEach>
		
		
	</table>

	
</body>
</html>