<%@page import="book.model.vo.BookExtends"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<%
	List<BookExtends> list = (List<BookExtends>)request.getAttribute("list");
	String searchType = request.getParameter("searchType");
	String searchKeyword = request.getParameter("searchKeyword");
	String pageBar = (String)request.getAttribute("pageBar"); 
%>

<title>AllBookListView</title>

<!-- js 시작 -->
<script src="<%=request.getContextPath() %>/js/jquery-3.5.1.js"></script>
<script src="<%=request.getContextPath() %>/js/bootstrap.js"></script>
<script src="<%=request.getContextPath() %>/js/bootstrap.js"></script>
<!-- js 끝 -->

<!-- css 시작  -->
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/bootstrap.css" />
<link rel="stylesheet" type="text/css" href="https://cdn.rawgit.com/moonspam/NanumSquare/master/nanumsquare.css">
<style>
* {
	font-family: 'NanumSquare', sans-serif !important;
	}
h1 {
	text-align: center;
	font-weight: bold;
}
#main .btn-div {
	position: relative;
	text-align: right;
}

#main .search-div {
	position: relative;
	text-align: center;
}

#main nav {
	text-align: center;
}

.container {
	width: 100%;
}

select, .textbox {
	width: 100px;
	height: 35px;
	font-size: 15px;
	color: #999;
	border: 1.3px solid #999;
	border-radius: 10px;
}

.textbox {
	width: 200px;
}

table {
	font-size: 18px;
}

.btn.btn-primary, .search-btn:hover {
	color: #fff;
	background-color: #B596FD;
	border-color: #B596FD;
	box-shadow: 0 2px 2px 0 rgba(156, 39, 176, 0.14), 0 3px 1px -2px
		rgba(156, 39, 176, 0.2), 0 1px 5px 0 rgba(156, 39, 176, 0.12);
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
	transition: color 0.15s ease-in-out, background-color 0.15s ease-in-out,
		border-color 0.15s ease-in-out, box-shadow 0.15s ease-in-out;
}

.btn:hover, .search-btn {
	color: #fff;
	background-color: #9c27b0;
	border-color: #9c27b0;
	box-shadow: 0 2px 2px 0 rgba(156, 39, 176, 0.14), 0 3px 1px -2px
		rgba(156, 39, 176, 0.2), 0 1px 5px 0 rgba(156, 39, 176, 0.12);
	border-radius: 30px;
}
/*검색*/
div#search-container {
	margin: 0 auto;
	padding: 3px;
	background-color: rgba(0, 188, 212, 0.3);
	width: 100%;
}

div#search-bookNo {
	display: inline-block;
}

div#search-userName {
	display: none;
}
/*메인화면 중앙*/
.container1 {position:relative; width: 70%; margin:0 auto;}
.search-container{position:relative; ;}
#pageBar{text-align:center;}
#pageBar {display:inline-flex;}
/*페이지바*/
.page-link {color: #428bca;}
</style>
<!-- css 끝 -->

<script>
	function displayChange(){
		$("[name=displayBookFrm]").submit();
	}
	
	$(function (){
		$("[name=displayBook]").change(function (){
			let yn = $(this).val();
			let book_no = $(this).prev().val();
			
			let $frm = $("[name=displayBookFrm]");
			$frm.append("<input type='hidden' name='display_yn' value='"+ yn +"' />");
			$frm.append("<input type='hidden' name='bookno' value='"+ book_no +"' />");
			
			$frm.submit();
		});
	});
	
	$(function(){
		$("#searchType").change(function(){
			$("#search-bookNo, #search-userName").hide();
			$("#search-" + $(this).val()).css("display","inline-block");
		});
		
	});
</script>

</head>
<body>
	<div class="container1">
        <div id="main">
            <h1>도서 조회</h1>
            <hr><br>
            <div class="search-container">
                <select id="searchType">
                    <option value="bookNo">ISBN</option>
                    <option value="userName">출판사</option>
                </select>
                <div id="search-bookNo">
                	<form action="<%=request.getContextPath()%>/admin/bookFinder">
                		<input type="hidden" class="textbox" name="searchType" value="bookNo" />
		                <input type="text" class="textbox" name="searchKeyword" placeholder="ISBN을 입력하세요"
		                	   value="<%="bookNo".equals(searchType) ? searchKeyword : ""%>">
    		            <button type="submit" class="search-btn">조회</button>
                	</form>
                </div>
                <div id="search-userName">
                	<form action="<%=request.getContextPath()%>/admin/bookFinder">
                		<input type="hidden" class="textbox" name="searchType" value="userName" />
                		<input type="text" class="textbox" name="searchKeyword" placeholder="출판사를 입력하세요"
                				value="<%="userName".equals(searchType) ? searchKeyword : ""%>"/>
                		<button type="submit" class="search-btn">조회</button>
                	</form>
                </div>
            </div>
            <br>
            <br>
            <table class="table table-hover">
                <thead>
                    <tr>
                        <th>ISBN</th>
                        <th>제목</th>
                        <th>저자</th>
                        <th>출판사</th>
                        <th>게시 여부</th>
                    </tr>
                </thead>
                <tbody>
                <% if(list == null || list.isEmpty()) {%>
                <!-- 조회된 도서가 없는 경우 -->
                <tr>
                	<th colspan=6> 조회된 도서가 없습니다. </th>
                </tr>

                <% 
                	} else {
                		for(BookExtends pb : list) {	
                %>
               
                
                    <tr>
                        <td><%= pb.getBookNo() %></td>
                        <td><a href="<%= request.getContextPath()%>/book/bookInfoView?bookNo=<%=pb.getBookNo()%>"><%= pb.getBookTitle() %></a></td>
                        <td><%= pb.getAuthorName() %></td>
                        <td><%= pb.getUserName() %></td>
                        <td> 
                        	<form name="displayBookFrm" action="<%=request.getContextPath() %>/admin/displayBookUpdate" method="post">
                        		<input type="hidden" name="book_No" value="<%=pb.getBookNo()%>"/>
                        		<select name="displayBook" id="" onchange="displayChange();" style="height:25px; width:50px;">
                        			<option value="N" <%= pb.getDisplayBook().equals("N")? "selected" : "" %>>N</option>
                        			<option value="Y" <%= pb.getDisplayBook().equals("Y")? "selected" : "" %>>Y</option>
								</select>
			                </form>
                        </td>
                    </tr>
                <% 		}
                	}
                %>
                </tbody>
            </table>
            <br><br>
            
    
            <nav id="pageNumber" aria-label="Page navigation example">
                <ul class="pagination justify-content-center">
            	 <li class="page-item">
	            	<%= pageBar %>     
	            	<style>
	            		.page-link {
	            			color: #428bca;
	            		}
	            	</style>  	 
            	 </li>
                </ul>
            </nav>
            
        </div>    
    </div>
</body>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>