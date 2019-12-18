<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>


<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>


	<form:form modelAttribute="addbookfrm" method="Post"
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
						<form:input type="file" path="pdffile" valu="filecontent"></form:input>
					</dd>

					<dl>
						<label>Book image:</label>
					</dl>
					<dd>
						<form:input type="file" path="image" valu="imgfile"></form:input>

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