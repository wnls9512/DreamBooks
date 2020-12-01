<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<%@page import="user.model.vo.Users" %>

<!-- css -->
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/bootstrap.css" />

<style>

#content1{
	position:relative;

}
#content1 h1{
	font-weight: 1000;
	position: relative;
}
#pw{
	width:200px;
}
 
h1 {
	margin-top:70px;

}
.table{
	position: relative;
	width:700px;
	margin-left: auto; 
	margin-right: auto;
	margin-bottom: 50px;
}
.text_pwd{
	position:relative;
	text-align: center;
	font-size: 20px;
}

/*7월10일 추가*/
.form-control{
	position:relative;
	margin-left:0px;
}
.plz{
	line-height: 100px;
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
    width: 120px;
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
function passwordValidate(){
	var $newPwd = $("#newPassword");
	var $newPwdChk = $("#newPasswordCheck");
	
	if(!/^[A-Za-z0-9+]{4,12}$/.test($newPwd.val())){
		alert("4~12자 영문 대 소문자, 숫자를 사용하세요.");
		$newPwd.focus();
		return false;
	}
	
	if($newPwd.val()!=$newPwdChk.val()){
		alert("입력한 비밀번호가 일치하지 않습니다.");
		$newPwd.select();
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
         <div class="content1">
         		<h1>비밀번호 변경</h1>
         		<hr /><br /><br />
                    <table class="table table-hover">
                     
                        <tbody>
                            <form name="updatePasswordFrm" action="<%=request.getContextPath() %>/mypage/updatePassword" method="post" encType="multiplart/form-data">
                                <input type="hidden" name="userId" value="<%=userLoggedIn.getUserId()%>" />
                                <tr class="text_pwd">
                                    <td class="plz" rowspan="4" style="vertical-align: middle;">
                                        	<h4>비밀번호 변경</h4>
                                    </td>
                                </tr>
                                <tr>
                                    <td><input type="password" 
                                    		   placeholder="현재 비밀번호" 
                                    		   name="password" 
                                    		   id="password" 
                                    		   class="form-control text-dark" 
                                    		   required/></td>
                                </tr>
                                <tr>
                                    <td><input type="password" 
                                    		   placeholder="변경할 비밀번호" 
                                    		   name="newPassword" 
                                    		   id="newPassword" 
                                    		   class="form-control" 
                                    		   required/></td>
                                </tr>
                                <tr>
                                    <td><input type="password" 
                                    		   placeholder="비밀번호 확인" 
                                    		   id="newPasswordCheck" 
                                    		   class="form-control" 
                                    		   required/></td>
                                </tr>
                                <tr>
                                    <td colspan="2" style="width:250px;">
                                        <input type="submit" 
                                        	   value="비밀번호 변경" 
                                        	   onclick="return passwordValidate();" 
                                        	   class="search-btn2"/>
                                    </td>
                                </tr>
                            </form>
                        </tbody>
                    </table>
                    </div>
        <!-- /content -->
    </div>
</div>
<!-- /mypage -->
<%@ include file="/WEB-INF/views/common/footer.jsp" %>