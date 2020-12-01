<%@page import="user.model.vo.SaledBook"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>

<title>AllSalesListView</title>
<%
	List<SaledBook> list = (List<SaledBook>)request.getAttribute("list");
%>

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
            <h1>기간별 매출 조회</h1>
            <hr><br>
            
            <%-- <div class="search-div">
	            <form action="<%=request.getContextPath()%>/admin/saledFinder">
        	        <input type="date" name="saledDate" id="" class="textbox" >
            	    <button type="submit" class="search-btn">조회</button>
    	        </form>
            </div> --%>
            <br><br>
            <br>
            <table class="table table-hover">
                <thead>
                    <tr>
                        <th>날짜</th>
                        <th>판매량(총)</th>
                        <th>매출액(원)</th>
                    </tr>
                </thead>
                <tbody>
                <% if(list == null || list.isEmpty()) { %>
                    <tr>
                        <td colspan="3">조회된 행이 없습니다</td>
                    </tr>
                 <% 
                	}else{
                		for(SaledBook sb : list){
                 %>
                    <tr>
                        <td><%=sb.getDateOrder() %></td>
                        <td><%=sb.getDateBookCount() %></td>
                        <td><%=sb.getDateSaledPrice() %>원</td>
                    </tr>
                    <%
                		}
                	}
                    %>
                </tbody>
            </table>
            <br><br>
            
    
          <!--   <nav aria-label="Page navigation example">
                <ul class="pagination justify-content-center">
                  <li class="page-item"><a class="page-link" href="http://127.0.0.1:5500/index.html#">Previous</a></li>
                  <li class="page-item"><a class="page-link" href="http://127.0.0.1:5500/index.html#">1</a></li>
                  <li class="page-item"><a class="page-link" href="http://127.0.0.1:5500/index.html#">2</a></li>
                  <li class="page-item"><a class="page-link" href="http://127.0.0.1:5500/index.html#">3</a></li>
                  <li class="page-item"><a class="page-link" href="http://127.0.0.1:5500/index.html#">4</a></li>
                  <li class="page-item"><a class="page-link" href="http://127.0.0.1:5500/index.html#">5</a></li>
                  <li class="page-item"><a class="page-link" href="http://127.0.0.1:5500/index.html#">Next</a></li>
                </ul>
            </nav> -->
        </div>    
    </div>
</body>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>