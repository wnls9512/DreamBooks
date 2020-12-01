<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String msg = (String)request.getAttribute("msg");
	String loc = (String)request.getAttribute("loc");
	String script = (String)request.getAttribute("script");
	String encryptedId = (String)request.getAttribute("encryptedId");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>사용자 메세지</title>
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
</head>
<body>
<script>
	swal({
	    text: "<%=msg%>",
	    icon: "success" //"info,success,warning,error" 중 택1
	})
	.then((value) => {
		location.href = "<%= request.getContextPath() + loc %>";
	});
</script>	
</body>
</html>



