<?xml version="1.0" encoding="UTF-8" ?>
<%@page import="user.model.vo.CartExtends"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<%
	Book b = (Book)request.getAttribute("book");
%>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/main.css" />
       <style>
       	*{
          text-align: center;
        }
		 h2{
           margin-top: 60px;
          
        }
        #container {
           margin: 0 auto;
           text-align: center;
           padding-left: 30px;
           padding-top: 30px;
           display: inline-block;
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
           padding: 14px 16px;
           font-size: 17px;
           transition: 0.3s;
        }
        .tabcontent {
           display: none;
           padding: 6px 12px;
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
        .od{
        	padding-left:10px;
        	padding-top:10px;
        	
        }
        #basketTab1 table, #basketTab2 table {
           border: none;
           border-collapse: collapse;
        }
        #basketTab1 table th, #basketTab2 table th {
           border-top: 1px solid silver;
        }
        #basketTab1 table td, #basketTab2 table td {
           border-top: 1px solid silver;
           border-bottom: 1px solid silver;
        }
        table tr td:nth-child(4), table tr td:nth-child(5){
           width: 150px;
        }
        #basketTab1 table td:nth-child(2), #basketTab2 table td:nth-child(2){
           padding-left: 200px;
           padding-right: 200px;
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
        table#orderTable td {
           text-align: center;
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
       <script>
       $(function(){
    	   $("[name=BuyBooks").click(function(){
    	   if($("#agreement_checkbox").is(":checked") == false) {
    		   alert("구매동의를 체크해주세요");
    		   
    		   return;
    		 };
    		   let $frm = $("[name=basketBuyFrm]");
    			$frm.attr('action','<%=request.getContextPath()%>/user/pay')
    				.attr('method','get')
    				.submit();
    	   });

    	   
       });
       </script>
   <h2>주문 목록</h2>
        <div id="container">
            
        
              <div id="basketTab1" class="tabcontent current">
                 <table>
                          <tr>
                             <td>
                                <img src="<%=request.getContextPath() %>/images/<%=b.getBookImgOriginalFileName()%>" alt="" class="bookImg"/> <br />
                            </td>
                            <td>
                                <strong style="padding-top: 5px;"><%=b.getBookTitle() %></strong>
                             </td>
         
                             <td>                     
                                <label class="bPrice4" for=""><%=b.getPrice() %></label>
                                <span>원</span>
                             </td>
                          </tr>
                         
                  
                 </table>
              </div>
        
             
              	<h2>결제 정보</h2>      
              <div id="buyBasketBook" style="padding:5px">   
                 <table id="orderTable" class="order4">
                    <tr>
                       <th class="od">총 주문금액</th>
                      
                       <td>
                          <span id="sumBookPrice4"><%=b.getPrice()%></span>

                          <span class="won">원</span> 
                        </td>      
                     </tr>
                     <tr>
                        <th class="od">할인금액</th>
                       
                        <td>
                           <span class="sale" id="sale">0</span>
                           <span class="won">원</span> 
                         </td>      
                      </tr>
                     <tr class="totalP"  style="border-bottom: 1px solid black;"">
                        <th class="od">총 결제금액</th>
                       <td>
                          <span class="price4 total4" id="sumTotalPrice4"><%=b.getPrice() %></span>
                          <span class="won4 total4">원</span>
                       </td>
                    </tr>
                   
                    <tr>
                        <th class="od">구매 동의</th>
                       
                        <td>
                         </td>      
                      </tr>
                      <tr>
                         <th colspan="2">
                            <input type="checkbox" id="agreement_checkbox" class="agreement_checkbox">
                            <label for="agreement_checkbox" class="checkbox_label">
                               	상품, 가격, 할인 정보, 유의 사항 등을  
                            </th>

                       </tr>
                       <tr>
                           <th></th>
                           <td>확인하였으며 구매에 동의합니다.</td>
                       </tr>
             
                    </table>
                    <p> 결제 취소는 결제일로부터 7일 이내에만 할 수 있습니다.</p>
                 
                 <img src="<%=request.getContextPath() %>/images/kakao2.jpg" name="BuyBooks" alt="" style="width: 200px;height: 50px; border-radius: 4px;">
            <form name="basketBuyFrm">
                <input type="hidden" value="<%=b.getBookNo() %>" name="bookNo"/>
				<input type="hidden" value="<%=userLoggedIn.getUserId()%>" name="userId" />
            </form>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>