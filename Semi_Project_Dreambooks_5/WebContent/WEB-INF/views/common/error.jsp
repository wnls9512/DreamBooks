<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ERRORPAGE</title>
<link href="https://fonts.googleapis.com/css2?family=Jua&display=swap" rel="stylesheet">
<style>
.container{
    text-align: center;
    vertical-align: middle;
    width: 2000px;
}
img{
    position: relative;
    width: 80%;
}
.link{
	position: relative;
	bottom: 390px;
}
a{
	font-size: 40px;
	font-family: 'Jua', sans-serif;
	font-weight:bold;
	text-decoration: none;
	color: #3a4ca8;
	/* text-shadow: 2px 2px 1px #3a4ca8; */
}
</style>
</head>
<body>
	<div class="container">
        <img src="<%=request.getContextPath() %>/images/error.png" alt="">
        <div class="link">
	        <a href="<%=request.getContextPath() %>">#홈으로 이동</a>
	        <br />
    	    <a href="<%=request.getContextPath() %>/user/login">#메인 페이지로 이동</a>
        </div>
    </div>
</body>
</html>