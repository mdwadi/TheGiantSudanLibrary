<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>
	<meta charset="UTF-8">
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>


	<form:form modelAttribute="addfrm" method="Post"
		enctype="multipart/form-data">
		<fieldset>
			<legend>Add Book </legend>
			<dl>
				<label>Book Title: </label>
				<dl>
					<dd>
						<form:input path="title" placeholder="Title" />
					</dd>
					<dl>
						<label>Book file:</label>
					</dl>
					<dd>
						<form:input type="file" path="book"></form:input>
						
					</dd>
					
					<dl>
						<label>Book image (Cover):</label>
					</dl>
					<dd>
						<form:input type="file" path="image"></form:input>
						
					</dd>
					
					<dl>
						<label>Book Author :</label>
					</dl>
					<dd>
						<form:input path="author" placeholder="Author" />
					</dd>
					<dl>
						<lable>Discription</lable>
					</dl>
					<dd>
						<form:textarea rows="7" cols="25" path="discription"
							placeholder="Discription" />
					</dd>


					<input type="submit" value="Send">
		</fieldset>
	</form:form>

</body>
</html>