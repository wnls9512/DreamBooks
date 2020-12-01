<?xml version="1.0" encoding="UTF-8" ?>
<%@page import="java.sql.Date"%>
<%@page import="user.model.vo.OrderBook"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<%
	List<OrderBook> list = (List<OrderBook>)request.getAttribute("list");
	String total = (String)request.getAttribute("total");
	int result = (int)request.getAttribute("result");
	String cancelYN = (String)request.getAttribute("cancelYN");
%>
<!-- css -->
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/bootstrap.css" />
<style>
#content1{
	position:relative;
	margin: 70px;
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
    position: relative;
}
.table{
    position: relative;
    width:700px;
	margin-left: auto; 
	margin-right: auto;
}
th{
background-color: rgba(246, 246, 246, 0.897);}
tr th {
	text-align : center;
	font-weight: 800;
}

.search-btn:hover {
	color: #fff;
	background-color: #B596FD;
	border-color: #B596FD;
	box-shadow: 0 2px 2px 0 rgba(156, 39, 176, 0.14), 0 3px 1px -2px
		rgba(156, 39, 176, 0.2), 0 1px 5px 0 rgba(156, 39, 176, 0.12);
	border-radius: 30px;
}

.search-btn {
	display: inline-block;
	width: 60px;
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

.search-btn {
	color: #fff;
	background-color: #9c27b0;
	border-color: #9c27b0;
	box-shadow: 0 2px 2px 0 rgba(156, 39, 176, 0.14), 0 3px 1px -2px
		rgba(156, 39, 176, 0.2), 0 1px 5px 0 rgba(156, 39, 176, 0.12);
	border-radius: 30px;
}

</style>
<!-- mypage -->
<div class="mypage">
    <div id="page-wrapper">
        <!-- sidebar -->
        <%--  <%@ include file="/WEB-INF/views/common/MypageSideBar.jsp" %>  --%>
        <!-- /sidebar -->
        <!-- content -->
          <div class="container1">
                    <h1>상세주문조회</h1>
                    <hr /><br /><br />
                    <table class="table table-hover">
                        <thead>
                            <!-- <h1> 상세주문조회 </h1> -->
                        </thead>
                        <tbody>
                                <tr>
                                    <th>주문번호 </th>
                                    <td colspan="2"><%=list.get(0).getOrderNo() %></td>
                                </tr>
                                <tr>
                                    <th>주문날짜 </th>
                                    <td colspan="2"><%=list.get(0).getOrderDate() %></td>
                                </tr>
                                <% int j = 1; %>
                                <% for(OrderBook o : list) { %>
                                <% %>
                                <tr>
                                    <th>[<%=j++ %>] </th>
                                    	<td><%=o.getBookTitle() %></td>
                                    	<td><%=o.getPrice() %> 원 
                                    			<%if(cancelYN.equals("N") /* && !userLoggedIn.getUserRole().equals("A") */) { %>
	                                   				&nbsp;&nbsp;
	                                   				<input type="button" class="search-button" style="width:75px; font-size:13px;"
                                    				value="바로 보기" onclick="window.open('https://www.dropbox.com/s/kuti7plnxwyq5ao/book_file.pdf?dl=0')"/>
	                                    		<% } %>
                                    	</td>
                                </tr>
                                <%} %>
                                <tr>
                                    <th>총 주문금액 </th>
                                    <td colspan="2"><%=total %>원</td>
                                </tr>
                                <tr>
                                    <td colspan="3">
                                    <%if(result <= 7 && cancelYN.equals("N") && !userLoggedIn.getUserRole().equals("A")) { %>
	                                   <input type="button" 
	                                    	   value="주문취소" 
	                                    	   class="search-button" style="width:100px;" onclick="cancelok();"/>
	                                    <br /><br />
	                                    <p>일주일 이내의 도서는 취소가 가능합니다.</p>
	                                    <% } else if(cancelYN.equals("Y")){ %>
	                                        <h4>* 취소된 주문입니다. *</h4>
	                                    <% } %>
	                                    <br /><br />
	                                    <input type="button" class="search-btn" value="목록" onclick="script:history.go(-1);"/>
                                    </td>
                                </tr>
                        </tbody>
                    </table>
                    <form name="cancelFrm">
	                     <input type="hidden" name="orderNo" value="<%=list.get(0).getOrderNo() %>"/>
	                     <input type="hidden" name="total" value="<%=total %>" />
	                     <input type="hidden" name="userId" value="<%=list.get(0).getUserId() %>" />
	               </form>
	                                    	
                    </div>
        <!-- /content -->
    </div>
</div>
<script>
function cancelok(){
	let yn = confirm('정말 취소하시겠습니까?');
	
	if(yn){
		let $frm = $("[name=cancelFrm]");
		$frm.attr("action", "<%=request.getContextPath()%>/order/cancelOrder");
		$frm.attr("method", "POST");
		$frm.submit();		
	};
	
}
</script>
<!-- /mypage -->
<%@ include file="/WEB-INF/views/common/footer.jsp" %>