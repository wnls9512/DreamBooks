<%@page import="book.model.vo.BookExtends"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%
	List<BookExtends> list = (List<BookExtends>)request.getAttribute("list");
	String category = (String)request.getAttribute("category");
	int cPage = (int)request.getAttribute("cPage");
	String pageBar = (String)request.getAttribute("pageBar");
%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<title>Category Book List</title>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/bootstrap.css" />
<style>
#book-list-view {
    width: 1200px;
    float: center;
    margin: 0px auto;
    /* padding: 20px; */
    position: relative;
    *zoom:1
}

#category-list {
    padding: 30px 30px;
    padding-left: 40px;
    /* margin: 50px 50px; */
    margin-top:0px;
    height: 1373px;
    float: left;
    background-color: rgba(246, 246, 246, 0.897);
}

#category-list h3, #category-list a {
    color: black;
    text-decoration: none;
}
#category-list h3:hover, #category-list a:hover {
    background-color: white;
}

#book-list {
    /* width: 580px; */
    padding: 30px;
    padding-left: 300px;
    margin-bottom: 30px;
    /* float: right; */
    /* text-align: left; */
}

#book-list img {
    width: 150px;
    padding-right: 35px;
}

#category-list ul {
    list-style: none;
    margin:0px; 
    padding: 0px;
}

#pageNumber {
	text-align: center;
}

/* 사이즈 정의 */
@media screen and (max-width:480px) {
    #book-list-view {
        width:auto;
    }
    #book-list {
        float: none;
        width: auto;
    }
    #category-list {
        float: none;
        width: auto;
    }
}
.btn.btn-primary, .search-btn2:hover {
    color: #fff;
    background-color: #B596FD;
    border-color: #B596FD;
    box-shadow: 0 2px 2px 0 rgba(156, 39, 176, 0.14), 0 3px 1px -2px rgba(156, 39, 176, 0.2), 0 1px 5px 0 rgba(156, 39, 176, 0.12);
    border-radius: 30px;
}
.btn, .search-btn2 {
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
.btn:hover, .search-btn2{
    color: #fff;
    background-color: #9c27b0;
    border-color: #9c27b0;
    box-shadow: 0 2px 2px 0 rgba(156, 39, 176, 0.14), 0 3px 1px -2px rgba(156, 39, 176, 0.2), 0 1px 5px 0 rgba(156, 39, 176, 0.12);
    border-radius: 30px;
}
/*2020-07-17*/
#bookFinder a {color: rgb(125, 92, 204) !important;}
#bookFinder nav ul li {display: inline-block; float: left; padding-right: 10px;}
</style>

<script>
function checkAll(){
    var check = document.getElementsByName("check");
    var all = document.getElementById("all");
    for(var i=0; i<check.length; i++){
    	check[i].checked = all.checked;
    }
}
function unChecked(){
    var check = document.getElementsByName("check");
    for(var i=0; i<check.length; i++){
    	check[i].checked = all.checked = false;
    }
}
$(function(){
	$('.search-btn2').click(function(){
		let $this = $(this).val();
		let arr = $this.split("&");
		
		let $bookNo =arr[0];
		let $userId =arr[1];
		let $price =arr[2];
		let $frm = $("[name=cartinsertFrm]");
		$frm.append("<input type='hidden' name='book_no' value='"+$bookNo+"' />");
		$frm.append("<input type='hidden' name='user_id' value='"+$userId+"' />");
		$frm.append("<input type='hidden' name='price' value='"+$price+"' />");
		$frm.attr('action', '<%= request.getContextPath()%>/user/cartInsert')
			.attr('method', 'POST')
			.submit();
	});
	$('.btn.btn-primary').click(function(){
		let $this = $(this).val();
		let $frm = $("[name=orderFrm]");
		$frm.append("<input type='hidden' name='book_no' value='"+$this+"' />");
		$frm.attr('action', '<%= request.getContextPath()%>/user/BuyView')
			.attr('method', 'get')
			.submit();
	});
});
</script>
</head>
<body>
    <div id="book-list-view">
        <div id="category-list">
            <ul>
                <li><a href="<%=request.getContextPath()%>/book/category?category=소설&cPage=1"><h3>소설</h3></a>
                    <ul>
                        <li> <a href="<%=request.getContextPath()%>/book/category?category=로맨스&cPage=1"> 로맨스 </a> </li>
                        <li> <a href="<%=request.getContextPath()%>/book/category?category=판타지&cPage=1"> 판타지 </a> </li>
                        <li> <a href="<%=request.getContextPath()%>/book/category?category=공포/스릴러&cPage=1"> 공포/스릴러 </a> </li>
                        <li> <a href="<%=request.getContextPath()%>/book/category?category=추리&cPage=1"> 추리 </a> </li>
                        <li> <a href="<%=request.getContextPath()%>/book/category?category=액션&cPage=1"> 액션 </a> </li>
                    </ul>
                </li>
                <br>
                <li> <a href="<%=request.getContextPath()%>/book/category?category=에세이/시&cPage=1"><h3> 에세이/시 </h3></a>
                    <ul>
                    <li> <a href="<%=request.getContextPath()%>/book/category?category=에세이&cPage=1"> 에세이 </a> </li>
                    <li> <a href="<%=request.getContextPath()%>/book/category?category=시&cPage=1"> 시 </a> </li>
                    </ul>
                </li>
                <br>
                <li> <a href="<%=request.getContextPath()%>/book/category?category=컴퓨터/IT&cPage=1"><h3> 컴퓨터/IT </h3></a>
                    <ul>
                        <li> <a href="<%=request.getContextPath()%>/book/category?category=개발/프로그래밍&cPage=1"> 개발/프로그래밍 </a> </li>
                        <li> <a href="<%=request.getContextPath()%>/book/category?category=IT자격증&cPage=1"> IT자격증 </a> </li>
                        <li> <a href="<%=request.getContextPath()%>/book/category?category=IT비지니스&cPage=1"> IT비지니스 </a> </li>
                    </ul>
                </li>
                <br>
                <li> <a href="<%=request.getContextPath()%>/book/category?category=교재/수험서&cPage=1"><h3> 교재/수험서 </h3></a>
                    <ul>
                        <li> <a href="<%=request.getContextPath()%>/book/category?category=초등학교"> 초등학교 </a> </li>
                        <li> <a href="<%=request.getContextPath()%>/book/category?category=중학교"> 중학교 </a> </li>
                        <li> <a href="<%=request.getContextPath()%>/book/category?category=고등학교"> 고등학교 </a> </li>
                        <li> <a href="<%=request.getContextPath()%>/book/category?category=기타수험서"> 기타수험서 </a> </li>
                    </ul>
                </li>
            </ul>
    
        </div>

        <div id="book-list">

            <h1><%=category %></h1>
            <br>
            <div id="bookFinder">
				<nav>
					<ul>
						<li><a href="<%=request.getContextPath()%>/category/bookFinder?category=<%=category%>&order=selling">인기순▲</a></li>
						<li>|</li>
						<li><a href="<%=request.getContextPath()%>/category/bookFinder?category=<%=category%>&order=name">작가이름순▲</a></li>
					</ul>
				</nav>
			</div>
            
            <!-- 조회되는 책만큼 테이블 반복 -->
            <table class='table'>
            <% if(list == null || list.isEmpty()){ %>
				<tr>
					<th colspan = "3"> 검색 결과가 없습니다.</th>
				</tr>
            <% } else { %>
            		<% int i = 5 * (cPage-1); %>
            		<% for(BookExtends b : list){ %>
            		<% i++; %>
                <tr>
                	<td>
                		<%= i %>.
                	</td>
                    <td>
                		<% if(b.getBookImgOriginalFileName() != null) { %>
                		<a href="<%=request.getContextPath() %>/book/bookView?bookNo=<%=b.getBookNo() %>">
	                    	<img src="<%=request.getContextPath() %>/images/<%= b.getBookImgRenameFileName()==null? 
				            			b.getBookImgOriginalFileName() : b.getBookImgRenameFileName() %>" >                		
                		</a>
                    	<% } %>
                    </td>
                    <td>
	                    <h4><%=b.getBookTitle() %></h4>
	                    <p><%=b.getAuthorName() %> | <%=b.getUserName() %></p>
	                    <p><%=b.getPrice() %>원</p>
	                    <br />
	                    <button class="search-btn2" 
								value="<%=b.getBookNo()%>&<%=userLoggedIn.getUserId() %>&<%=b.getPrice()%>">장바구니</button>
						<button class="btn btn-primary" 
								value="<%=b.getBookNo()%>">바로구매</button>
                    </td>
						<!--장바구니 폼 -->	
						<form name="cartinsertFrm">
						</form>
						<!--구매하기 폼 -->	
						<form name="orderFrm">
						</form>
                </tr>
                <% } %>
            <% } %>   
            </table>
            
            <div style="text-align: right;">
            	<!-- <input type="button" value="선택해제" onclick="unChecked();" /> -->
				<br />
				<style>
				.btn-info {
					  color: #ffffff;
					  background-color: #5bc0de;
					  border-color: #46b8da;
					  border-radius: 30px;
					  width: 150px;
					}
					
					.btn-info:hover,
					.btn-info:focus,
					.btn-info:active,
					.btn-info.active,
					.open .dropdown-toggle.btn-info {
					  color: #ffffff;
					  background-color: #39b3d7;
					  border-color: #269abc;
					}

				</style>
            	
            </div>
                
 			<br /><br /><br />
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

            
<%--             <div id="pageBar">
            	<%= pageBar %>
            </div> --%>
            
        </div>
    </div>

</body>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>