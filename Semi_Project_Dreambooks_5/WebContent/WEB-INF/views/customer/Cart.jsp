<?xml version="1.0" encoding="UTF-8" ?>
<%@page import="user.model.vo.CartExtends"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<%
	List<CartExtends> list = (List<CartExtends>)request.getAttribute("list");
%>
    <style>
        *{
           margin: 0;
          box-sizing: border-box;
           /* text-align:center; */
        }
        
        h2{
           position: relative;
           top: 20px;
        }
	    th{
	    	text-align:center;
	    }   
        #container {
           margin: 0 auto;
           text-align: center;
           /* padding-left: 30px;
           padding-top: 30px; */
           display: inline-block;
           width: 80%;
        }
        .basketTab {
           list-style: none;
           margin: 0;
           padding: 0;
           overflow: hidden;
        }
        .basketTab li {
           float: left;
        }
        .basketTab li a {
           display: inline-block;
           color: black;
           text-align: center;
           text-decoration: none;
           /* padding: 14px 16px; */
           font-size: 17px;
           transition: 0.3s;
        }
        .tabcontent {
           display: none;
           /* padding: 6px 12px; */
        }
        li.current a {
           color: white;
        }
        .current {
           color: black;
           background: white;
        }
        .tabcontent.current {
           display: inline-block;
        }
        input[type=checkbox] {
           width: 20px;
           height: 20px;
           margin: 25px 25px;
        }
        .bookImg, .oldBookImg {
           width: 60px;
           height: 100px;
           padding-top: 5px;
           
        }
        #basketTab1 table, #basketTab2 table {
           border: none;
           border-collapse: collapse;
        }
        #basketTab1 table th, #basketTab2 table th {
           border-top: 1px solid silver;
           vertical-align: middle;
           
        }
        #basketTab1 table td, #basketTab2 table td {
           border-top: 1px solid silver;
           border-bottom: 1px solid silver;
           vertical-align: middle;
           
        }
        table tr td:nth-child(4), table tr td:nth-child(5){
           width: 150px;
           vertical-align: middle;
        }
       
       
        #buyBasketBook {
           margin-left: -10px;
           margin-top: 50px;
           margin-bottom: 50px;
           display: inline-block;
           
        }
        table.order4 {
           border: 1px solid;
           border-collapse: collapse;
           margin: 10px;
           width: 400px;
        }
        table#orderTable {
           color: #303343;
           background: white;
           border-color: silver;
        }
       .totalP {
           background: #dee6fd;
           width: 33%;
        }
        table#orderTable td, table#orderTable th {
           text-align: center;
           padding-top: 10px;
           padding-bottom: 10px;
        }
        span.price4 {
           font-size: 25px;
           font-weight: bold;
        }
        span.won4 {
           font-size: 15px;
        }
        img.price4 {
           border: 0px;
           width: 30px;
           height: 30px;
           float: right;
        }
        span.total4 {
           color: #B596FD;
        }
        .deleteBookBtn, .deleteOldBookBtn{
           border-color: silver;
           color: white;
           width: 80px;
           height: 30px;
           background: silver;
        }
       
        
        </style>
</head>
<script>

function checkAll(){
    var check = document.getElementsByName("check");
    var all = document.getElementById("all");
    for(var i=0; i<check.length; i++){
    	check[i].checked = all.checked;
    }
}
$(function(){
	$(".checkBook").change(function(){
	    var sum = 0;
	    
	    // 서점책 쪽
	    $(".checkBook:checked").each(function(){
	       sum += Number($(this).parent().parent().find(".totalprice").text());
				
			});
		 $("#sumBookPrice4").text(sum);
		 $('#sumTotalPrice4').text(sum);
	    });

	
	$('#allBuyBtn').click(function(){
		if($('.checkBook').is(':checked')==true){
			let $frm = $("[name=cartbuyFrm]");
			$frm.attr('action','<%=request.getContextPath()%>/user/BuyView')
				.attr('method','get')
				.submit();
		}else{
			alert("구매할 도서를 체크해주세요");
		}
	 });
	
	$('[name=deleteBookBtn]').click(function(){
		let $frm = $("[name=cartdeleteFrm]");
		$frm.attr('action', '<%= request.getContextPath()%>/user/cartDelete')
			.attr('method', 'POST')
			.submit();
	
	});
});
</script>
<body>
	<br />
   <h2>장바구니</h2>
   <br /><br />
        <div id="container">
            
              <ul class="basketTab">
                 <li class="current" data-tab="basketTab1"><a href="#">주문 도서</a></li>
              </ul>
        
              <div id="basketTab1" class="tabcontent current">
				<form name="cartbuyFrm">
                 <table class="table table-hover">
                    <tr>
                       <th  style="padding-left: 65px;">
                          <input type="checkbox" name="all" id="all" class="checkBook" onchange="checkAll();"/>
                          <label for="all" style="vertical-align: middle;">전체선택</label>
                        </th>
                       <th style="min-width:307px;">제목</th>
                       <th style="min-width:207px;"></th>
                       <th style="min-width:151px;">가격</th>
                       <th style="min-width:151px;">비고</th>
                    </tr>
       
                             	<% if(list == null || list.isEmpty()) { %>
								<td colspan="5">
									<br />
									<p>카트가 비어있습니다.</p>
									<br />
								</td>
								<%} else { %>
									<% for(CartExtends c : list) { %>
                          <tr>
							 <td>
                                <input class="checkBook" type="checkbox" name="check" value="<%=c.getCartCode()%>"/>
                             </td>
                             <td>
                                <img src="<%=request.getContextPath() %>/images/<%= c.getBookImgRenameFileName()==null? 
				            			c.getBookImgOriginalFileName() : c.getBookImgRenameFileName() %>" alt="" class="bookImg" "/>
                             </td>
                             <td>
                                <strong style="padding-top: 5px;"><%=c.getBookTitle() %></strong>
                             </td>
         
                             <td>                     
                                <label class="bPrice4 totalprice" for=""><%=c.getCartTotalPrice() %></label>
                                <span>원</span>
                                <input type="hidden" name="" class="bookPrice" value=""/>
                             </td>
                             <td>
                                <input type="button" value="삭제" class="deleteBookBtn" name="deleteBookBtn"/>
                             </td>
                          </tr>
                          
                          
                          	<form name="cartdeleteFrm">
				            	<input type="hidden" name="book_no" value="<%=c.getBookNo()%>"/>
				            	<input type="hidden" name="user_id" value="<%=c.getUserId()%>"/>
			             	</form>			             
								<%} %>
							<%} %>
                  
                 </table>
	            </form>
              </div>

              <div id="buyBasketBook">         
                 <table id="orderTable" class="order4">
                    <tr>
                       <th>총 상품금액</th>
                      
                       <td>
            
                          <span id="sumBookPrice4">0</span>
                          <span class="won">원</span> 
                        </td>      
                     </tr>
                     <tr>
                        <th>할인금액</th>
                       
                        <td>
                           <span class="sale" id="sale">0</span>
                           <span class="won">원</span> 
                         </td>      
                      </tr>
                     <tr class="totalP">
                        <th>합 계</th>
                       <td>
                          <span class="price4 total4" id="sumTotalPrice4">0</span>
                          <span class="won4 total4">원</span>
                       </td>
                    </tr>            
                 </table>
                 
                 <input style="width: 300px; height: 40px; background: #B596FD; color: white; border-radius: 4px; border: 1px solid #B596FD;" type="button" value="선택 구매하기" id="allBuyBtn"/>
                </div>
            
            
<%@ include file="/WEB-INF/views/common/footer.jsp" %>