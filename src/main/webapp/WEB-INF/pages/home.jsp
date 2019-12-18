<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="true"%>
<%@page import="java.io.File"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">





<style type="text/css">
table, tr {
	border: 1px solid black;
}

tr {
	padding: 0px;
	text-align: left;
}

.header {
	overflow: hidden;
	background-image: url("images/libraryImage.jpg");
	position: absolute;
	top: 0;
	right: 0;
	width: 100%;
	top: 0;
}

.navbar {
	overflow: hidden;
	background-color: #333;
	position: fixed;
	top: 0;
	right: 0;
	width: 100%;
	padding-bottom: 5px;
}

.navbar a {
	float: left;
	display: block;
	color: #f2f2f2;
	text-align: center;
	padding: 14px 16px;
	text-decoration: none;
	font-size: 17px;
}

.navbar a:hover {
	background: #ddd;
	color: black;
}

.navbar ul li {
	display: inline;
}

.search {
	padding: 70px;
	margin-top: 80px;
}

.login, .note {
	float: right;
}

.main {
	margin-top: 150px;
	padding: 100px;
	width: 100%;
	padding-bottom: 5px;
}

.main ul li {
	float: left;
	text-decoration: none;
}

.footer {
	background-color: #333;
	margin-top: 500px;
	right: 0;
	width: 100%;
	margin-bottom: 0;
	padding-bottom: 0;
	width: 100%;
}

.dropdown {
	overflow: hidden;
	float: left;
}

.booklist {
	cursor: pointer;
	display: block;
	transition: all .15s ease;
}

.childbooks {
	display: none;
	position: absolute;
	background-color: #ddd;
	box-shadow: 8px 16px rgba(0, 0.2);
	z-index: 1;
}

.childbooks a {
	float: left;
	color: black;
	text-decoration: none;
	display: block;
}

.dropdown:hover .childbooks {
	display: block;
}
</style>


<title>Insert title here</title>
</head>
<body>



	<div class="header">

		<div class="navbar">
			<ul>
				<li><a href="home">Home</a></li>
				<li><a href="aboutUs">About Us</a></li>

				<div class="dropdown">

					<li class="booklist"><a href="">List of Books</a>
						<ul class="childbooks">


							<li><a href="stories">History</a></li>
							<li><a href="stories">Novel</a></li></li>
			</ul>


			</li>
		</div>
		<li><a href="stories">Stories</a></li>
		<li><a href="AddBook">Add Book</a></li>

		<li class="note"><a href="">Notification</a></li>
		<li class="login"><a href="login">login|Register</a></li>
		</ul>

	</div>



	<div class="search">
		<form:form modelAttribute="searchfrm" action="searchbook"
			method="Post">
			<input type="text" name="title" onfocus="">
			<input type="text" name="author">
			<input type="submit" value="submit">

		</form:form>

	</div>

	</div>


	<div class="main">



		<p>body contain</p>





		<table width="40" hieght="40" bgcolor="cyan" bordercolor="red">
			<c:forEach var="dto" items="${listDto}">
				<tr>
					<c:out value="${dto.title}" />
					<br>
					<c:out value="${dto.author}" />


					<%-- <img src="data:image/jpg;base64,${dto.imagecontent}" width="150"
						height="100" /> --%>
					<br>


					<c:out value="${dto.discription}" />
					<br>




					<a href="ReadBook?bookUrl=${dto.bookUrl}">Read</a>
					<b><blink>----</blink></b>
					<a href="DownloadBook?String=${dto.bookUrl}">download</a>

					<br>
					<br>
				</tr>
		</table>

		</c:forEach>












	</div>

	<div class="footer">
		<footer>
			<ul>
				<li><a href="home">Home</a></li>
				<li><a href="aboutUs">About Us</a></li>
				<li><a href="bookList">List of Books</a></li>
				<li><a href="stories">Stories</a></li>
			</ul>


		</footer>
	</div>
</body>
</html>