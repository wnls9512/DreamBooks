<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<!-- css -->
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/bootstrap.css" />
<style>
#content1{
	position:relative;
	/* width:100%; */
	margin: 70px;
/* 	top:50px;
	left:700px; */
}
/* #content1 h1{
	font-weight: 600;
} */
#password{
	width:250px;
	/* display:inline-block;
	float:center; */
}
table {
	margin-left : auto;
	margin-right: auto;
}
.search-btn2:hover {
    color: #fff;
    background-color: #B596FD;
    border-color: #B596FD;
    box-shadow: 0 2px 2px 0 rgba(156, 39, 176, 0.14), 0 3px 1px -2px rgba(156, 39, 176, 0.2), 0 1px 5px 0 rgba(156, 39, 176, 0.12);
    border-radius: 30px;
}
.btn, .search-btn2 {
    display: inline-block;
    width: 80px;
    height: 33px;
    font-weight: 400;
    text-align: center;
    white-space: nowrap;
    vertical-align: middle;
    user-select: none;
    border: 1px solid transparent;
    padding: 0.46875rem 1rem;
    font-size: 15px;
    line-height: 1.5;
    border-radius: 0.25rem;
    transition: color 0.15s ease-in-out, background-color 0.15s ease-in-out, border-color 0.15s ease-in-out, box-shadow 0.15s ease-in-out;
}
.btn:hover, .search-btn2{
    color: #fff;
    background-color: #9c27b0;
    border-color: #9c27b0;
    box-shadow: 0 2px 2px 0 rgba(156, 39, 176, 0.14), 0 3px 1px -2px rgba(156, 39, 176, 0.2), 0 1px 5px 0 rgba(156, 39, 176, 0.12);
    border-radius: 30px;
}

input[type=password] {
    font-family: sans-serif !important;
}
input[type=password]::placeholder{
	font-family: 'NanumSquare';
} 

</style>
<script>
function chkPwd(){
	//사용자가 입력한 비밀번호와 원래 비밀번호가 동일하면 된다
	let $password = $("[name=password]").val();
	
	if($password == ""){
		alert("비밀번호를 입력하여 주세요");
		$password.select();
		return false;
	} 
		return true;		
}
</script>
<!-- mypage -->
<div class="mypage">
    <div id="page-wrapper">
        <!-- sidebar -->
        <%-- <%@ include file="/WEB-INF/views/common/MypageSideBar.jsp" %> --%>
        <!-- /sidebar -->
        <!-- content -->
        <div id="content1">
        	<h1>개인정보 변경</h1>
        	<hr />
        	<br /><br />
        	<p>보안을 위해 비밀번호를 한번 더 입력해 주세요.</p>
        	<br />
        	<!-- 비밀번호 중복 폼 -->
        	<form name="passwordChkFrm" action="" method="post">
         		<input type="hidden" name="userId" value="<%=userLoggedIn.getUserId() %>" />
         	<table>
         	<tbody>
	        		<tr class="pwck">
        				<td><input type="password" 
                                   placeholder="비밀번호를 입력해주세요." 
                                   name="password" 
                                   id="password" 
                                   class="form-control" 
                                   required/>
						</td>
        				<td><input type="submit" class="search-btn2" value="확인"></input></td>
    	    		</tr>
        	</tbody>
        	</table>
        	</form>
        	
        </div>
        <!-- /content -->
    </div>
</div>
<!-- /mypage -->
<%@ include file="/WEB-INF/views/common/footer.jsp" %>