<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<%
	Users user = (Users)request.getAttribute("user");
%>
<!-- css -->
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/bootstrap.css" />
<style>
#content1{
	position:absolute;
	top:50px;
	left:700px;
}
#content1 h1{
	font-weight: 1000;
}
#pw{
	width:200px;
}
 /*개인정보변경*/
.container2 {
    position: relative;
    width: 50%;
}
h1 {
    position: absolute;
    top:50px;
    left:500px;
}
.table{
    position: absolute;
    top:150px;
    left: 500px;
    width: 600px;
}
tr th {
	text-align : center;
}
.pull {
	margin:0 50%;
}
.form-control{
	position:relative;
	margin-left:0px;
}

</style>
<script>
function deleteUser(){
	if(!confirm("정말 탈퇴하시겠습니까?")) return;
	
	$("[name=deleteUserFrm]").submit();
}
</script>
<!-- mypage -->
<div class="mypage">
    <div id="page-wrapper" style="text-align: center;">
         <div class="container2" style="display:block; height:650px; width:700px;">
         
	         <form action="<%=request.getContextPath()%>/user/deleteuser" 
	         	   name="deleteUserFrm" 
	         	   method="POST">
	         	<input type="hidden" name="userId"  value="<%=user.getUserId()%>"/>
	         	<input type="hidden" name="userLoggedInRole" value="<%=userLoggedIn.getUserRole()%>"/>
	         </form>
	         
	         <table class="table table-bordered">
	         	<thead>
	         		<h1> 개인정보변경 </h1>
                </thead>
				<tbody>
                	<form name="adminUpdateUserFrm" 
                		  action="<%=request.getContextPath()%>/admin/updateUser" 
                		  method="post" 
                		  encType="multiplart/form-data">
                    <tr>
                    	<th>이름 </th>
                       	<td>
                        	<input type="text" 
                        		   style="width:380px;" 
                        		   value="<%= user.getUserName() %>" 
                        		   name="userName" 
                        		   class="form-control" 
                        		   readonly/>
	                        <input type="button" 
	                        	   value="회원탈퇴" 
	                        	   onclick="deleteUser();" 
	                        	   class="btn btn-info pull-right"/>                                
                         </td>
                    </tr>
                    <tr>
                    	<th>아이디 </th>
                        <td>
                        	<input type="text" 
                        		   style="width:380px;" 
                        		   value="<%= user.getUserId() %>" 
                        		   name="userId" 
                        		   class="form-control" 
                        		   readonly/>
                    	</td>
                    </tr>
                    <tr>
                    	<th>이메일 </th>
                        <td>
                        	<input type="email" 
                        		   value="<%= user.getEmail() %>" 
                        		   name="email" 
                        		   class="form-control"/>
                        </td>
                    </tr>
                    <tr>
                        <th>핸드폰 번호</th>
                        <td>
                        	<input type="phone" 
                        		   value="<%=user.getPhone() %>" 
                        		   name="phone" 
                        		   class="form-control"/>
                        </td>
                    </tr>
                    <tr>
                    	<th>회원 권한</th>
                        <td>
                           <select name="userRole" id="userRole">
                           	<option value="A" <%= user.getUserRole().equals("A") ? "selected" : "" %>>관리자</option>	
                           	<option value="P" <%= user.getUserRole().equals("P") ? "selected" : "" %>>출판사</option>	
                           	<option value="U" <%= user.getUserRole().equals("U") ? "selected" : "" %>>일반 회원</option>	
                           </select>
                        </td>
                     </tr>                                
                     <tr>
		                 <td colspan=2>
		                     <input type="submit" value="수정" class="btn btn-info pull"/>
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