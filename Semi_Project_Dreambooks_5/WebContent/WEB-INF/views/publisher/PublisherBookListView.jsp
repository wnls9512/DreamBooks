<%@page import="book.model.vo.BookExtends"%>
<%@page import="book.model.vo.PubBook"%>
<%@page import="java.util.List"%>
<%@page import="book.model.vo.Book"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<%
	List<BookExtends> list = (List<BookExtends>)request.getAttribute("list");
	int cPage = (int)request.getAttribute("cPage");
	String pubId = (String)request.getAttribute("userId");
	String pageBar = (String)request.getAttribute("pageBar");
	String searchType = request.getParameter("select-search");
	String keyword = request.getParameter("search-text");

%>
<title>PublisherBookListView</title>

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
</style>
<!-- css 끝 -->

</head>
<body>
	<div class="container">
        <div id="main">
            <h1>도서 조회</h1>
            <hr><br>
            <div class="search-div">
            <form action="<%=request.getContextPath() %>/book/bookSearchPub">
                <select name="select-search">
                    <option value="book_no" <%="book_no".equals(searchType) ? "selected" : "" %>>ISBN</option>
                    <option value="book_title"<%="book_title".equals(searchType) ? "selected" : "" %>>제목</option>
                    <option value="author_name"<%="author_name".equals(searchType) ? "selected" : "" %>>저자</option>
                </select>
                <input type="text" class="textbox" name="search-text"
                		value="<%=keyword != null ? keyword : "" %>">
                <input type="hidden" name="userId" value=<%=pubId %> />
                <button type="submit" class="search-btn">조회</button>
            </form>
            </div>
            <br><br>
            <div class="btn-div">
                <button type="button" onclick="location.href='<%=request.getContextPath() %>/publisher/postingBookFrm'" class="btn btn-primary">도서 등록</button>
            </div>
            <% if(request.getParameter("select-search") != null) { %>
            <% int total = (int)request.getAttribute("total"); %> 
			 	<p>* 검색 결과 <%=total %> 건 *</p>
			<% }%>
            <br>
            <table class="table table-hover">
                <thead>
                    <tr>
                        <th>ISBN</th>
                        <th>제목</th>
                        <th>저자</th>
                        <th>출판사</th>
                        <th>판매량</th>
                        <th>게시여부</th>
                    </tr>
                </thead>
                <tbody>
                <% if(list == null || list.isEmpty()){ %>
                <!-- 조회된 도서가 없는 경우 -->
                <tr>
                	<th colspan=6 style="text-align: center;"> 조회된 도서가 없습니다. </th>
                </tr>
                <% 
                	} else {
                		for(BookExtends b : list){
                %>
                	<!-- 조회된 도서가 있는 경우 -->
                    <tr>
                        <td><%=b.getBookNo() %></td>
                        <td><a href="<%= request.getContextPath()%>/book/bookInfoView?bookNo=<%=b.getBookNo()%>"><%=b.getBookTitle() %></a></td>
                        <td><%=b.getAuthorName() %></td>
                        <td><%=b.getUserName() %></td>
                        <td><%=b.getSaleCount() %></td>
                        <td><%=b.getDisplayBook() %></td>
                    </tr>
                    
                  <% 	} 
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