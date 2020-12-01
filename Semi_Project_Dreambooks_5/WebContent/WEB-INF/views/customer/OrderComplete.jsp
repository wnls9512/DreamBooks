<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
		<div class="message" style="text-align:center; margin-top:200px;margin-bottom:200px;">
		<h2>결제 완료 되었습니다.</h2>
		<br />
		<input type="button" value="결제내역" style="background: #B596FD; color: white; border-radius: 4px; border: 1px solid #B596FD;" onclick="location.href='<%=request.getContextPath()%>/mypage/orderList?userId=<%=userLoggedIn.getUserId() %>'"/>	
		</div>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>