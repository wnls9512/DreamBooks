<%@page import="user.model.service.UserService"%>
<%@page import="book.model.vo.Book"%>
<%@page import="user.model.vo.Users"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//session: 선언없이 사용할 수 있는 내장 객체
	Users userLoggedIn = (Users)session.getAttribute("userLoggedIn");

%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<!--         <meta name="description" content="">
        <meta name="author" content=""> -->
        <title>Dream Books::Index</title>
 
        <link rel="apple-touch-icon" sizes="76x76" href="<%=request.getContextPath() %>/assets/img/apple-icon.png">
        <link rel="icon" type="image/png" href="<%=request.getContextPath() %>/assets/img/favicon.png">
 
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
 
        <!-- Font Awesome icons (free version)-->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/js/all.min.js" crossorigin="anonymous"></script>
 
        <!-- Core theme CSS (includes Bootstrap)-->
        <link href="<%=request.getContextPath() %>/css/styles.css" rel="stylesheet">
 
        <!-- Fonts CSS-->
        <link rel="stylesheet" href="<%=request.getContextPath() %>/css/heading.css">
        <link rel="stylesheet" href="<%=request.getContextPath() %>/css/body.css">
 
        <!-- CSS Just for demo purpose, don't include it in your project -->
        <link rel="stylesheet" type="text/css" href="https://cdn.rawgit.com/moonspam/NanumSquare/master/nanumsquare.css"> 
        
        <!-- js 시작 -->
		<script src="<%=request.getContextPath() %>/js/jquery-3.5.1.js"></script>
		<script src="<%=request.getContextPath() %>/js/bootstrap.js"></script>
		<!-- js 끝 -->
		
		<!-- css 시작  -->
<%-- 		<link rel="stylesheet" href="<%=request.getContextPath() %>/css/bootstrap.css" /> --%>
        <link rel="stylesheet" href="<%=request.getContextPath() %>/css/main.css" />
        
       
<style>
*{font-family: 'NanumSquare', sans-serif !important;/*  font-size:15px; */}

/* 버튼 */
.search-button:hover {
    color: rgb(126, 98, 190);
    background-color: rgb(221, 221, 221);
    border-color: rgb(221, 221, 221);
    box-shadow: 0 2px 2px 0 rgba(156, 39, 176, 0.14), 0 3px 1px -2px rgba(156, 39, 176, 0.2), 0 1px 5px 0 rgba(156, 39, 176, 0.12);
    border-radius: 30px;
}
.search-button {
    display: inline-block;
    width: 60px;
    height: 35px;
    font-weight: 400;
/*     text-align: center; */
    white-space: nowrap;
    vertical-align: middle;
    user-select: none;
    border: 1px solid transparent;
    padding: 0.46875rem 1rem;
    font-size: 15px;
    line-height: 1;
    border-radius: 30px;
    transition: color 0.15s ease-in-out, background-color 0.15s ease-in-out, border-color 0.15s ease-in-out, box-shadow 0.15s ease-in-out;
}

.form-control {
  display: block;
  width: 75%;
  float:left;
  height: 46px;
  padding: 6px 12px;
  margin: 0px 23px;
  font-size: 14px;
  line-height: 1.428571429;
  color: #555555;
  vertical-align: middle;
  background-color: #ffffff;
  background-image: none;
  border: 1px solid #cccccc;
  border-radius: 4px;
  -webkit-box-shadow: inset 0 1px 1px rgba(0, 0, 0, 0.075);
          box-shadow: inset 0 1px 1px rgba(0, 0, 0, 0.075);
  -webkit-transition: border-color ease-in-out 0.15s, box-shadow ease-in-out 0.15s;
          transition: border-color ease-in-out 0.15s, box-shadow ease-in-out 0.15s;
}

#mainNav{
	position: relative;
	/* display: block; */
}

.container, .card{
	display: inline-block;
	position: relative;
}

.container, .container a, .container a img {
	text-align: center;
	margin: 0 1.5rem;
}
.card{
	background-color: rgba(0, 0, 0, 0);
	display: inline-block;
	position: relative;
	color: white;
	border: 0;
}
#page-top{
	width: 100%;
}
#navbarResponsive{
	display: inline-block;
	position: relative;
	left: -30px;
}
.card-body, .card-body a{
	color: white;
	margin: 0px;
} 

#icon1{
	animation-name: spin;
  	animation-duration: 1.5s;
  	animation-iteration-count: infinite;
}
@keyframes spin {
  0% {transform: rotateY(0deg);}
  100% {transform: rotateY(360deg);}
}

.spacialMenu{
/* 	display: inline-block; */
	position: relative;
	text-align: right;
}

</style>
<script>
$(function(){
	$('#search').click(function(){
		if($('[name=searchKeyword]').val()==""){
			alert("검색어를 입력하세요");
		}else{
			$('[name=search]').submit();
		}
	});
});
</script>
</head>
	<body id="page-top" onload="printClock();">

        <nav class="navbar navbar-expand-lg bg-secondary fixed-top" id="mainNav">
        
			<!-- 검색창  -->
            <div class="container">
            	<a href="<%= request.getContextPath() %>/user/login">
            	   	<img src="<%=request.getContextPath() %>/images/img3-2.png" style="width: 250px;">
            	</a>
	         	<div class="" id="navbarResponsive">
	            	<form action="<%=request.getContextPath() %>/book/search" name="search">
	                      <input type="text" 
	                      	   name="searchKeyword" 
	                      	   id="searchKeyword"
	                      	   class="form-control" 
	                      	   placeholder="Search" 
	                      	   style="width:350px;">
						   <button type="button" 
						  		  class="search-button" 
						  		  id="search"
						  		  style="height: 45px; width: 100px;">
						  		 <svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-search" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
								 <path fill-rule="evenodd" d="M10.442 10.442a1 1 0 0 1 1.415 0l3.85 3.85a1 1 0 0 1-1.414 1.415l-3.85-3.85a1 1 0 0 1 0-1.415z"/>
								 <path fill-rule="evenodd" d="M6.5 12a5.5 5.5 0 1 0 0-11 5.5 5.5 0 0 0 0 11zM13 6.5a6.5 6.5 0 1 1-13 0 6.5 6.5 0 0 1 13 0z"/>
								 </svg> 
						  		   검색
						  </button>
	                </form>
	          	</div>
            <!-- 로그인 정보 -->
	        <div class="card">
				<div class="card-body">
					<svg width="25px" height="" viewBox="0 0 16 16" class="bi bi-star-fill" fill="currentColor" xmlns="http://www.w3.org/2000/svg" id="icon1">
					  <path d="M3.612 15.443c-.386.198-.824-.149-.746-.592l.83-4.73L.173 6.765c-.329-.314-.158-.888.283-.95l4.898-.696L7.538.792c.197-.39.73-.39.927 0l2.184 4.327 4.898.696c.441.062.612.636.283.95l-3.523 3.356.83 4.73c.078.443-.36.79-.746.592L8 13.187l-4.389 2.256z"/>
					</svg> <br />
					<div class="card-title"><%= userLoggedIn.getUserName() %> 님</div>
					<a href="<%= request.getContextPath() %>/user/logout" 
					   class="card-link">LOGOUT</a>
				</div>
			</div>
			<!-- 로그인 정보 -->			
            </div>
            <!-- 검색창  -->
        </nav>
        
        <nav class="navbar navbar-expand-lg bg-secondary fixed-top" id="mainNav">
            <div>
                <ul class="navbar-nav ml-auto">
                    <li class="nav-item mx-0 mx-lg-1">
                    	<a class="nav-link py-3 px-0 px-lg-3 rounded js-scroll-trigger" href="#">
                    	<svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-moon" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
						  <path fill-rule="evenodd" d="M14.53 10.53a7 7 0 0 1-9.058-9.058A7.003 7.003 0 0 0 8 15a7.002 7.002 0 0 0 6.53-4.47z"/>
						</svg>
                    	CATEGORY
                    	</a>
                        <ul>
                            <li><a href="<%=request.getContextPath()%>/book/category?category=소설&cPage=1">소설</a>
                                <ul>
                                    <li> <a href="<%=request.getContextPath()%>/book/category?category=로맨스&cPage=1"> 로맨스 </a> </li>
			                        <li> <a href="<%=request.getContextPath()%>/book/category?category=판타지&cPage=1"> 판타지 </a> </li>
			                        <li> <a href="<%=request.getContextPath()%>/book/category?category=공포/스릴러&cPage=1"> 공포/스릴러 </a> </li>
			                        <li> <a href="<%=request.getContextPath()%>/book/category?category=추리&cPage=1"> 추리 </a> </li>
			                        <li> <a href="<%=request.getContextPath()%>/book/category?category=액션&cPage=1"> 액션 </a> </li>
                    			</ul>
                            </li>
                            <li> <a href="<%=request.getContextPath()%>/book/category?category=에세이/시&cPage=1"> 에세이/시 </a>
                                <ul>
                                   <li> <a href="<%=request.getContextPath()%>/book/category?category=에세이&cPage=1"> 에세이 </a> </li>
                                   <li> <a href="<%=request.getContextPath()%>/book/category?category=시&cPage=1"> 시 </a> </li>
                    			</ul>
                           </li>
                           <li> <a href="<%=request.getContextPath()%>/book/category?category=컴퓨터/IT&cPage=1"> 컴퓨터/IT </a>
                               <ul>
                                   <li> <a href="<%=request.getContextPath()%>/book/category?category=개발/프로그래밍&cPage=1"> 개발/프로그래밍 </a> </li>
			                        <li> <a href="<%=request.getContextPath()%>/book/category?category=IT자격증&cPage=1"> IT자격증 </a> </li>
			                        <li> <a href="<%=request.getContextPath()%>/book/category?category=IT비지니스&cPage=1"> IT비지니스 </a> </li>
			                    </ul>
                           </li>
                           <li> <a href="<%=request.getContextPath()%>/book/category?category=교재/수험서&cPage=1"> 교재/수험서 </a>
                               <ul>
                                   <li> <a href="<%=request.getContextPath()%>/book/category?category=초등학교&cPage=1"> 초등학교 </a> </li>
			                        <li> <a href="<%=request.getContextPath()%>/book/category?category=중학교&cPage=1"> 중학교 </a> </li>
			                        <li> <a href="<%=request.getContextPath()%>/book/category?category=고등학교&cPage=1"> 고등학교 </a> </li>
			                        <li> <a href="<%=request.getContextPath()%>/book/category?category=기타수험서&cPage=1"> 기타수험서 </a> </li>
			                    </ul>	
                           </li>
                        </ul>
                    </li>
                    <li class="nav-item mx-0 mx-lg-1">
                    	<a class="nav-link py-3 px-0 px-lg-3 rounded js-scroll-trigger" 
                    	   href="<%= request.getContextPath() %>/user/login">
                    	   <svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-house-door" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
							  <path fill-rule="evenodd" d="M7.646 1.146a.5.5 0 0 1 .708 0l6 6a.5.5 0 0 1 .146.354v7a.5.5 0 0 1-.5.5H9.5a.5.5 0 0 1-.5-.5v-4H7v4a.5.5 0 0 1-.5.5H2a.5.5 0 0 1-.5-.5v-7a.5.5 0 0 1 .146-.354l6-6zM2.5 7.707V14H6v-4a.5.5 0 0 1 .5-.5h3a.5.5 0 0 1 .5.5v4h3.5V7.707L8 2.207l-5.5 5.5z"/>
							  <path fill-rule="evenodd" d="M13 2.5V6l-2-2V2.5a.5.5 0 0 1 .5-.5h1a.5.5 0 0 1 .5.5z"/>
							</svg>
                    	   HOME                   	   
                    	</a>
                    </li>
					<li class="nav-item mx-0 mx-lg-1">
                    	<a class="nav-link py-3 px-0 px-lg-3 rounded js-scroll-trigger" 
                    	   href="<%= request.getContextPath() %>/user/cartView?userId=<%= userLoggedIn.getUserId() %>">
                    	   <svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-cart4" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
		  					<path fill-rule="evenodd" d="M0 2.5A.5.5 0 0 1 .5 2H2a.5.5 0 0 1 .485.379L2.89 4H14.5a.5.5 0 0 1 .485.621l-1.5 6A.5.5 0 0 1 13 11H4a.5.5 0 0 1-.485-.379L1.61 3H.5a.5.5 0 0 1-.5-.5zM3.14 5l.5 2H5V5H3.14zM6 5v2h2V5H6zm3 0v2h2V5H9zm3 0v2h1.36l.5-2H12zm1.11 3H12v2h.61l.5-2zM11 8H9v2h2V8zM8 8H6v2h2V8zM5 8H3.89l.5 2H5V8zm0 5a1 1 0 1 0 0 2 1 1 0 0 0 0-2zm-2 1a2 2 0 1 1 4 0 2 2 0 0 1-4 0zm9-1a1 1 0 1 0 0 2 1 1 0 0 0 0-2zm-2 1a2 2 0 1 1 4 0 2 2 0 0 1-4 0z"/>
						   </svg>
                    	   CART
                    	</a>
                    </li>
					<li class="nav-item mx-0 mx-lg-1">
						<a class="nav-link py-3 px-0 px-lg-3 rounded js-scroll-trigger" 
						   href="<%= request.getContextPath() %>/user/mypage?userId=<%=userLoggedIn.getUserId()%>">
						   <svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-person" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
							  <path fill-rule="evenodd" d="M13 14s1 0 1-1-1-4-6-4-6 3-6 4 1 1 1 1h10zm-9.995-.944v-.002.002zM3.022 13h9.956a.274.274 0 0 0 .014-.002l.008-.002c-.001-.246-.154-.986-.832-1.664C11.516 10.68 10.289 10 8 10c-2.29 0-3.516.68-4.168 1.332-.678.678-.83 1.418-.832 1.664a1.05 1.05 0 0 0 .022.004zm9.974.056v-.002.002zM8 7a2 2 0 1 0 0-4 2 2 0 0 0 0 4zm3-2a3 3 0 1 1-6 0 3 3 0 0 1 6 0z"/>
							</svg>
						   MYPAGE
						</a>
                        <ul>
                        	<li><a href="<%=request.getContextPath()%>/mypage/usercheck">개인정보 조회/변경</a></li>
                        	<li><a href="<%=request.getContextPath()%>/mypage/updatePassword">비밀번호 변경</a></li>
                        	<li><a href="<%=request.getContextPath()%>/mypage/orderList?userId=<%=userLoggedIn.getUserId() %>">주문내역</a></li>
                    	</ul>
                    </li>

					<% 	if(userLoggedIn != null
		            	&& UserService.USER_ROLE_ADMIN.equals(userLoggedIn.getUserRole())){ %>
                    <li class="nav-item mx-0 mx-lg-1">
						<a class="nav-link py-3 px-0 px-lg-3 rounded js-scroll-trigger" 
						   href="#">
							<svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-check2-circle" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
								<path fill-rule="evenodd" d="M15.354 2.646a.5.5 0 0 1 0 .708l-7 7a.5.5 0 0 1-.708 0l-3-3a.5.5 0 1 1 .708-.708L8 9.293l6.646-6.647a.5.5 0 0 1 .708 0z"/>
								<path fill-rule="evenodd" d="M8 2.5A5.5 5.5 0 1 0 13.5 8a.5.5 0 0 1 1 0 6.5 6.5 0 1 1-3.25-5.63.5.5 0 1 1-.5.865A5.472 5.472 0 0 0 8 2.5z"/>
						   </svg>
						   MANAGEMENT
						</a>
                        <ul>
                        	<li><a href="<%= request.getContextPath()%>/admin/allBookList">도서관리</a></li>
                        	<li><a href="<%= request.getContextPath()%>/admin/orderListView">주문관리</a></li>
                        	<li><a href="<%= request.getContextPath()%>/admin/SalesListView">매출관리</a></li>
                        	<li><a href="<%= request.getContextPath()%>/admin/userListView">회원관리</a></li>
                    	</ul>
                    </li>
                    <% } %>
					<% 	if(userLoggedIn != null
		            	&& UserService.USER_ROLE_PUBLISHER.equals(userLoggedIn.getUserRole())){ %>
	                    <li class="nav-item mx-0 mx-lg-1">
						<a class="nav-link py-3 px-0 px-lg-3 rounded js-scroll-trigger" 
						   href="#">
						   <svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-check2-circle" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
								<path fill-rule="evenodd" d="M15.354 2.646a.5.5 0 0 1 0 .708l-7 7a.5.5 0 0 1-.708 0l-3-3a.5.5 0 1 1 .708-.708L8 9.293l6.646-6.647a.5.5 0 0 1 .708 0z"/>
								<path fill-rule="evenodd" d="M8 2.5A5.5 5.5 0 1 0 13.5 8a.5.5 0 0 1 1 0 6.5 6.5 0 1 1-3.25-5.63.5.5 0 1 1-.5.865A5.472 5.472 0 0 0 8 2.5z"/>
						   </svg>
							MANAGEMENT
						</a>
                        <ul>
                        	<li><a href="<%=request.getContextPath()%>/publisher/bookListView?userId=<%= userLoggedIn.getUserId() %>">도서관리</a></li>
                    	</ul>
                    </li>
                    <% } %>
                </ul>
            </div>
            
<%-- 			<div class="spacialMenu">
            <% if(userLoggedIn != null
            		&& UserService.USER_ROLE_PUBLISHER.equals(userLoggedIn.getUserRole())){ %>
            	<a href="<%=request.getContextPath()%>/publisher/bookListView?userId=<%= userLoggedIn.getUserId() %>">도서관리</a>
            <% } %>
            </div>
            <div class="spacialMenu">
            <% 	if(userLoggedIn != null
            		&& UserService.USER_ROLE_ADMIN.equals(userLoggedIn.getUserRole())){ %>
            	<a href="<%= request.getContextPath()%>/admin/allBookList">도서관리</a> | 
            	<a href="<%= request.getContextPath()%>/admin/orderListView">주문관리</a> | 
            	<a href="<%= request.getContextPath()%>/admin/SalesListView">매출관리</a> | 
            	<a href="<%= request.getContextPath()%>/admin/userListView">회원관리</a>
            <% } %>
            </div> --%>
            
        </nav>
        <section id="content" style="text-align: center;">
        