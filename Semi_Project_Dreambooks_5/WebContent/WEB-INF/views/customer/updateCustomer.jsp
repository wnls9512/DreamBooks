<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<!-- css -->
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/bootstrap.css" />

<style>
 *  { font-family: 'NanumSquare', sans-serif !important;}
 h1 { text-align: center;
    font-weight: bold;}
#main .btn-div{position: relative; text-align: center; }
#main .search-div{position: relative; text-align: center;}
#main nav{text-align: center;}
.container {width: 100%;}
form{margin: 0 auto;}
table{font-size: 18px; text-align: center; position:relative; margin-left: auto; margin-right: auto;}
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
th {
   /*  text-align: center;
    vertical-align: middle;  */
}
th{
    right: 15px;
    font-weight:700;
   /*  vertical-align: middle; 
    text-align: center; */
}


</style>
<script>
function deleteUser(){
	let $frm = $("[name=updateUserFrm]");
	$frm.attr("action","<%=request.getContextPath()%>/user/deleteuser");
	$frm.attr("method","post");
	$frm.submit();
}
</script>
<!-- mypage -->
<div class="mypage">
    <div id="page-wrapper">
        <!-- sidebar -->
        <%-- <%@ include file="/WEB-INF/views/common/MypageSideBar.jsp" %> --%>
        <!-- /sidebar -->
        <!-- content -->
         <div class="container">
         	<div id="main">
         		<h1>개인 정보 조회</h1>
         		<hr /><br /><br />
                    <table class="table table-hover" style="width:800px;">

                            <form name="updateUserFrm" action="<%=request.getContextPath()%>/user/userupdate" method="post" encType="multiplart/form-data">
                        		<input type="hidden" name="userId" value="<%=userLoggedIn.getUserId() %>"/>
                        		<input type="hidden" name="userName" value="<%=userLoggedIn.getUserName() %>"/>
                        		<input type="hidden" name="userLoggedInRole" value="<%=userLoggedIn.getUserRole()%>"/>
                        <thead>
                                <tr>
                                    <th>이름 </th>
                                    <td>
                                    	<%=userLoggedIn.getUserName() %>
                                    </td>
                                    <td>
                                    	<% if(!userLoggedIn.getUserRole().equals("A")) { %>
	                                    <input type="button" value="회원탈퇴" onclick="deleteUser();" style="width:80px;" class="search-button"/>       
	                                    <% } %>                         
                                    </td>
                                </tr>
	                    
	                    
                                <tr>
                                    <th>아이디 </th>
                                    <td><%=userLoggedIn.getUserId() %></td>
                                	<td></td>
                                </tr>
                                <tr>
                                    <th>이메일 </th>
                                    <td><input type="email" value="<%=userLoggedIn.getEmail() %>" name="eamil" class="form-control"/></td>
                                	<td></td>
                                </tr>
                                <tr>
                                    <th>핸드폰 번호</th>
                                    <td><input type="phone" value="<%=userLoggedIn.getPhone() %>" name="phone" class="form-control"/></td>
                                	<td></td>
                                </tr>
                                <tr>
                                    <td colspan="3">
                                        <input type="submit" value="등록" class="search-btn2"/>
                                    </td>
                                </tr>
                             </form>
                        
                        </thead>
                    </table>
                    </div>
                </div>
        <!-- /content -->
    </div>
</div>
<!-- /mypage -->
<%@ include file="/WEB-INF/views/common/footer.jsp" %>