<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<%
	List<Users> list = (List<Users>)request.getAttribute("list");
	String searchType = request.getParameter("searchType");
%>
<title>UserFinder</title>

<!-- js 시작 -->
<script src="<%=request.getContextPath() %>/js/jquery-3.5.1.js"></script>
<script src="<%=request.getContextPath() %>/js/bootstrap.js"></script>
<script src="<%=request.getContextPath() %>/js/bootstrap.js"></script>
<!-- js 끝 -->

<!-- css 시작  -->
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/bootstrap.css" />
<link rel="stylesheet" type="text/css" href="https://cdn.rawgit.com/moonspam/NanumSquare/master/nanumsquare.css">
<style>
 *  { font-family: 'NanumSquare', sans-serif !important;}
 h1 { text-align: center;
    font-weight: bold;}
#main .btn-div{position: relative; text-align: right; }
#main .search-div{position: relative; text-align: center;}
#main nav{text-align: center;}
.container {width: 1000px;}
/*메인화면 중앙*/
.container1 {position:relative;width: 80%; left: 300px;}
.search-container{position:absolute; left:500px;}
#pageBar{width:100px; margin-left:700px; text-align:center;}
/*메인화면 중앙 끝*/
select, .textbox{width: 100px; height: 35px; font-size: 15px; color: #999;
        border: 1.3px solid #999; border-radius: 10px;
    }
.textbox{width: 200px;}
table{font-size: 18px;}
.btn.btn-primary, .search-btn:hover {
    color: #fff;
    background-color: #B596FD;
    border-color: #B596FD;
    box-shadow: 0 2px 2px 0 rgba(156, 39, 176, 0.14), 0 3px 1px -2px rgba(156, 39, 176, 0.2), 0 1px 5px 0 rgba(156, 39, 176, 0.12);
    border-radius: 30px;
}
.btn, .search-btn {
    display: inline-block;
    width: 100px;
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
.btn:hover, .search-btn{
    color: #fff;
    background-color: #9c27b0;
    border-color: #9c27b0;
    box-shadow: 0 2px 2px 0 rgba(156, 39, 176, 0.14), 0 3px 1px -2px rgba(156, 39, 176, 0.2), 0 1px 5px 0 rgba(156, 39, 176, 0.12);
    border-radius: 30px;
}
/*검색*/
div#search-container {margin:0 0 10px 0; padding:3px; background-color: rgba(0, 188, 212, 0.3); width: 100%;}
div#search-userId {<%= "userId".equals(searchType) ? "display: inline-block;" : "display:none;" %> }
div#search-userName {<%= "userName".equals(searchType) ? "display: inline-block;" : "display: none;"%>}
</style>
<!-- css 끝 -->
<script>
$(function(){
	$("#searchType").change(function(){
		$("#search-userId, #search-userName").hide();
		$("#search-" + $(this).val()).css("display","inline-block");
	});
});
</script>
</head>
<body>
	<div class="container1">
        <div id="main">
            <h1>회원 조회</h1>
            <hr><br>
            <div class="search-container">
                <select id="searchType">
                    <option value="userId">ID</option>
                    <option value="userName">NAME</option>
                </select>
                <div id="search-userId">
	                <form action="<%=request.getContextPath()%>/admin/userFinder">
		                <input type="hidden" class="textbox" name="searchType" value="userId">
	    	            <input type="text" class="textbox" name="searchKeyword" size="25" placeholder="검색할 아이디를 입력하세요"
	                	   		value="">
	                	<button type="submit" class="search-btn">검색</button>	
	                </form>
                </div>
                <div id="search-userName">
					<form action="<%=request.getContextPath()%>/admin/userFinder">
						<input type="hidden" class="textbox" name="searchType" value="userName"/>
						<input type="search" class="textbox" name="searchKeyword" id="" size="25" placeholder="검색할 이름을 입력하세요."
					   			value=""/>
					<button type="submit" class="search-btn">검색</button>			
					</form>	
				</div>
                <!-- <button type="button" class="search-btn">검색</button> -->
            </div>
            <br><br>
            <div class="btn-div">
                <button type="button" class="btn btn-primary">전체 보기</button>
                <button type="button" class="btn btn-primary">일반 회원</button>
                <button type="button" class="btn btn-primary">출판사 회원</button>
            </div>
            <br>
            <table class="table table-hover">
                <thead>
                    <tr>
                        <th>아이디</th>
                        <th>이름</th>
                        <th>이메일</th>
                        <th>전화번호</th>
                        <th>권한</th>
                    </tr>
                </thead>
                <tbody>
                <% if(list == null || list.isEmpty()) {%>
                    <!-- 조회된 회원이 없는 경우 -->
                    <tr>
                    	<th colspan=5> 조회된 회원이 없습니다. </th>
                    </tr>
                 <%
                 	} else { 
                 		for(Users u : list) {	
                 %>
                    <tr>
                        <td><a href="<%= request.getContextPath() %>/admin/userInfo?userId=<%=u.getUserId()%>"><%= u.getUserId() %></a></td>
                        <td><%= u.getUserName() %></td>
                        <td><%= u.getEmail() %></td>
                        <td><%= u.getPhone() %></td>
                        <td><%= u.getUserRole().equals("A") 
                        				? "관리자" : (u.getUserRole().equals("P") ? "출판사" : "일반회원")  %></td>
                    </tr>
                 <% 	}
                 	}
                 %>

                </tbody>
            </table>
            <br><br>
            
            <div id="pageBar">
            	<%= request.getAttribute("pageBar") %>
            </div>
        </div>    
    </div>
</body>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>