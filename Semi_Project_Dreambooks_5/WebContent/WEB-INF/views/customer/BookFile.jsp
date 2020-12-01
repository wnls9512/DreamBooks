<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String fileName = (String)request.getAttribute("fileName");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Book File</title>
</head>
<body>
	<iframe	src="<%= request.getContextPath()%>/files/<%=fileName%>"
			 style="height: 100vh; width: 100%; padding: 0px; margin: 0px;">
	</iframe>
</body>
</html>