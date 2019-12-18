<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@ page import="java.sql.*, javax.sql.*, java.util.HashSet, java.util.ArrayList, java.util.Iterator, java.io.*"%>
	
<%@page import="java.io.File"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>


	Stories page







	<%-- <table border=1>

		<tr>
			<td>FileName</td>
			<td>View</td>
			<td>download</td>
			<%
				String path = "D:/books/UI";

				String files;
				File folder = new File(path);

				File[] listOfFile = folder.listFiles();

				for (int i = 0; i < listOfFile.length; i++) {
					if (listOfFile[i].isFile()) {
						files = listOfFile[i].getName();
			%>
		
		<tr>
			<td><%=files%></td>



			<td><iframe
					src="D:\books\UI\Java Hibernate Cookbook.pdf&embedded=true"
					style="width: 50px; height: 50px;" frameborder="0"></iframe></td>
			<td><a href="download?file_name=<%=files%>">Download</a>
			<td>
		</tr>
		<%
			}
			}
		%>

	</table> --%>

</body>
</html>