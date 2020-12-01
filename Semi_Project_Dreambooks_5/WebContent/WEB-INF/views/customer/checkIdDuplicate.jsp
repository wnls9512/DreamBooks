<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	boolean isIdUsable = (boolean)request.getAttribute("isIdUsable");
	String enroll_id = request.getParameter("enroll_id");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아이디 중복 검사</title>
<script src="<%= request.getContextPath() %>/js/jquery-3.5.1.js"></script>
<script>
function closePopup(){
	//부모윈도우에서 요소 찾기
	let $enrollFrm = $("[name=userEnrollFrm]", opener.document);
	console.log($enrollFrm);
	
	$enrollFrm.find("#enroll_id_").val("<%=enroll_id%>");
	$enrollFrm.find("#isIdValid").val(1);
	
	close();//팝업 닫기
}

function checkIdDuplicate(){
	let $enroll_id = $("#enroll_id_");
	if(!/^[\w]{4,}$/.test($enroll_id.val())){
		alert("유효한 아이디를 입력해주세요.");
		$enroll_id.select();
		return;
	}
	
	let $frm = $("[name=checkIdDuplicateFrm]");
	$frm.attr("action", "<%=request.getContextPath()%>/user/checkIdDuplicate");
	$frm.attr("method", "POST");
	$frm.submit();
	
}

</script>
<style>
#checkid-container {
	text-align: center;
	padding-top: 50px;
}
span {
	text-weight: bold;
}
span#duplicated {
	color: red;
}
</style>
</head>
<body>
	<div id="checkid-container">
	<% if(isIdUsable) { %>	
		[ <span><%= enroll_id %></span> ]는 사용가능합니다.
		<br /><br />
		<input type="button" value="닫기" onclick="closePopup();" />
	<% } else { %>
		[ <span id="duplicated"><%= enroll_id%></span> ]는 이미 사용중입니다.
		<br /><br />
		<form name="checkIdDuplicateFrm">
			<input type="text" name="enroll_id"
				   id="enroll_id_" 
				   placeholder="아이디를 입력하세요." />
			&nbsp;&nbsp;
			<input type="button" value="IDcheck" onclick="checkIdDuplicate();" />
		</form>
	
	
	<% } %>
	</div>
</body>
</html>