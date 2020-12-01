<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<!-- css -->
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/bootstrap.css" />
<style>
#content1{
	height:700px;
	margin-top:50px;
	margin-left:500px;
}
#content1 h1{
	font-weight: 1000;
}
#content1 p {
	font-size: 18px;
}
</style>
<!-- mypage -->
<div class="mypage">
    <div id="page-wrapper">
        <!-- content -->
        <div id="content1">
        	<h1>탈퇴가 완료되었습니다</h1>
        	<br /><br />
        	<p>이용해 주셔서 감사합니다^^</p>
        	<br />
        	<tbody>
        		
        	</tbody>
        </div>
        <!-- /content -->
    </div>
</div>
<!-- /mypage -->
<%@ include file="/WEB-INF/views/common/footer.jsp" %>