<?xml version="1.0" encoding="UTF-8" ?>
<%@page import="book.model.vo.BookOrdered"%>
<%@page import="java.util.List"%>
<%@page import="book.model.vo.BookExtends"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<!-- css -->
<%
	List<BookExtends> list = (List<BookExtends>)request.getAttribute("list");
	List<BookOrdered> list1 = (List<BookOrdered>)request.getAttribute("list1");
%>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/bootstrap.css" />
 <style>   
/* .mypage {
	padding-left: 250px;
	height: 700px;
	position: relative;
	width: 50%;
} 
*/
#content1 {
	position:relative;
}

.table {
	margin-left:auto;
	margin-right:auto;
	width: 800px;
	margin-bottom : 10px;
	font-size:16px;
}
tr, th, td {
	text-align:center !important;
}
h1 {
	margin-top : 30px;
}
</style>
 <!-- mypage-->
        <div class="mypage">
            <div id="page-wrapper">
                <!-- sidebar -->
                <%-- <%@ include file="/WEB-INF/views/common/MypageSideBar.jsp" %> --%>
                <!-- /sidebar -->
                <!-- 본문 -->
                <div id="page-content-wrapper">
                    <!--구매한 책-->
                    <div class="content1">
                      <table class="table table-hover">
                      <br />
                          <thead>
                            <h1>구매한 책</h1><br />
                            <tr>
                                <th>책제목</th>
                                <th>저자</th>
                                <th>출판사</th>
                                <th>구매일자</th>
                                
                            </tr>
                        </thead>
                        <tbody>
                        <% if(list1 == null || list1.isEmpty()) { %>
                          <tr>
                            <td colspan="5">조회된 행이 없습니다</td>
                          </tr>
                        <% 
                         	}else{
                         		for(BookOrdered bo : list1){
                        %>
                          <tr>
                            <td><%=bo.getBookTitle() %></td>
                            <td><%=bo.getAuthorName() %></td>
                            <td><%=bo.getPublisherName() %></td>
                            <td><%=bo.getOrderDate() %></td>
                          
                          </tr>
                          <% 
                          			} 
                         		}
                          %>
                        </tbody>  
                      </table>
                      <nav id="pageNumber" aria-label="Page navigation example">
                <ul class="pagination justify-content-center">
            	 <li class="page-item">
	            	<%= request.getAttribute("pageBar") %>     
	            	<style>
	            		.page-link {
	            			color: #428bca;
	            		}
	            	</style>  	 
            	 </li>
                </ul>
            </nav>
                    </div>
                    <hr />
                    <!--최근에 본 책-->
                    <div class="content1">
                        <table class="table table-hover" style="margin-bottom: 60px;">
                            <thead>
                                <h1>최근에 본 책</h1>
                                <p>*최근 보신 5권만 조회 가능합니다</p>
                                <tr>
                                    <th>책제목</th>
                                    <th>저자</th>
                                    <th>출판사</th>
                                </tr>
                            </thead>
                            <tbody>
                            	<% if(list == null || list.isEmpty()){ %>
                            	<tr>
									<th colspan = "3"> 조회된 행이 없습니다. </th>
								</tr>
								<% 
									} else { 
										for(BookExtends b : list){
								%>							
	                                <tr>
	                                    <td><%=b.getBookTitle() %></td>
	                                    <td><%=b.getAuthorName() %></td>
	                                    <td><%=b.getUserName() %></td>
	                                </tr>
                                	<% } %>
                                <% } %>
                            </tbody>  
                        </table>
                    </div>
                  </div>
                  </div>
                <!-- /본문 -->
            </div>
        </div>
        <!-- /mypage-->
<%@ include file="/WEB-INF/views/common/footer.jsp" %>