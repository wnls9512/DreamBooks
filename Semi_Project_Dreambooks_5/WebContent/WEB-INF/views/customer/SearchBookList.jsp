<%@page import="book.model.vo.BookExtends"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	List<BookExtends> list =(List<BookExtends>)request.getAttribute("list");
	List<BookExtends> list2 =(List<BookExtends>)request.getAttribute("list2");
	String searchKeyword = request.getParameter("searchKeyword");
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<title>Tag Book List</title>

<style>
#search-tag-result {
	width: 1100px;
	float: center;
	margin: 0 auto;
	padding: 30px 50px;
}
#search-tag-result h2{
	padding: 10px 50px;
}
#book-tbl {
	margin: 0 auto;
}
#book-tbl tr, #book-tbl td{
	text-align : center;
}
#book-info img {
	padding-bottom: 10px;
}
#book-info{
	width:160px;
	margin:40px;
	display:inline-block;
}
#book-info2{
	width:170px;
	float: left;
}
#tag-part, #similar-genre{
	display: inline-block;
}
img{
	width:150px;
}
</style>

</head>
    <body>
            
        <div id="search-tag-result">
            <!-- 태그 검색 결과 -->
            <div id="tag-part">
                <h2><%=searchKeyword %></h2>
                <!-- 조회되는 책만큼 book-info 반복 -->
	               <% if(list == null || list.isEmpty()){ %>
                	<div id="book-info">
	                	<table id="book-tbl">
							<tr>
								<th colspan = "3"> 검색 결과가 없습니다.</th>
							</tr>
		            	</table>
		            </div>	
	            	<% } else { %>
           
            		<% for(BookExtends b : list){ %>
            			
            		<div id="book-info">
                    <table id="book-tbl">
                        <tr>
                            <td><% if(b.getBookImgOriginalFileName() != null) { %>
		                    	<a href="<%=request.getContextPath()%>/book/bookView?bookNo=<%=b.getBookNo()%>">
		                    		<img src="<%=request.getContextPath() %>/images/<%= b.getBookImgRenameFileName()==null? 
		    		  					 b.getBookImgOriginalFileName() : b.getBookImgRenameFileName() %>">
		                    	</a>
                    			<% } %>
                    		</td>
                        </tr>
                        <tr>
                            <td><h6><%=b.getBookTitle() %></h6></td>
                        </tr>
                        <tr>
                            <td><h6><%=b.getAuthorName() %> | <%=b.getUserName() %></h6></td>
                        </tr>
                        <tr>
                            <td><%=b.getPrice() %>원</td>
                        </tr>
                    </table>
                	</div>
                    	<%} %>
                    <% } %>
            </div>

            <!-- 구분선 -->
            <hr>

            <!-- 비슷한 장르 -->
            <div id="similar-genre">
                <h2>검색결과와 비슷한 도서</h2>
                
                <!-- 조회되는 책만큼 book-info 반복 -->
               	
                 <% if(list2 == null || list2.isEmpty() || list2.size()==0){ %>
                	<div id="book-info">
	                	<table id="book-tbl">
							<tr>
								<th colspan = "3"> </th>
							</tr>
		            	</table>
		            </div>	
	            	<% } else { %>
           
            		<% for(int i=0;i<5;i++){ %>
            		<div id="book-info2">
                    <table id="book-tbl">
                        <tr>
                            <td>
		                    	<a href="<%=request.getContextPath()%>/book/bookView?bookNo=<%=list2.get(i).getBookNo()%>">
		                    		<img src="<%=request.getContextPath() %>/images/<%= list2.get(i).getBookImgRenameFileName()==null? 
		                    				list2.get(i).getBookImgOriginalFileName() : list2.get(i).getBookImgRenameFileName() %>"  height="200px" >
		                    	</a>
                    		</td>
                        </tr>
                        <tr>
                            <td><h6><%=list2.get(i).getBookTitle() %></h6></td>
                        </tr>
                        <tr>
                            <td><h6><%=list2.get(i).getAuthorName() %> | <%=list2.get(i).getUserName() %></h6></td>
                        </tr>
                        <tr>
                            <td><%=list2.get(i).getPrice() %>원</td>
                        </tr>
                    </table>
                	</div>
                		<%} %>
                   <%} %>

        </div>



</body>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>