<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ page
	import="java.io.*"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Books view page </title>
</head>
<body>
<%
		{
			byte[] filename = (byte[]) session.getAttribute("bookfile");
			
			response.setContentType("application/pdf");
			ByteArrayInputStream in = new ByteArrayInputStream(filename);
			OutputStream out1 = response.getOutputStream();
			byte[] buf = new byte[4096];
			int count = 0;
			while ((count = in.read(buf)) >= 0) {
			
				out1.write(buf, 0, count);
				
			}

			in.close();
			out1.close();
		}
	%>

</body>
</html>