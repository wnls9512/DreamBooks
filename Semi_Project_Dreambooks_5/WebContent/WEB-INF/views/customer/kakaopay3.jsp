<%@page import="user.model.vo.Users"%>
<%@page import="book.model.vo.Book"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
		int size = (int)request.getAttribute("size");
		Book[] b = (Book[])request.getAttribute("book");
 		Users u = (Users)request.getAttribute("user");
 		int sum =(int)request.getAttribute("sum");
 		String str="";
 		for(int i=0;i<size-1;i++){
 			str += b[i].getBookNo()+"&bookNo=";
 		}
 			str +=b[size-1].getBookNo();

%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js" ></script>
<script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.1.5.js"></script>
</head>
<body>
    <script>
    $(function(){
        var IMP = window.IMP; 
        var msg;
        IMP.init('imp63677074'); // 'iamport' 대신 부여받은 "가맹점 식별코드"를 사용
        
        IMP.request_pay({
            pg : 'kakaopay',
            pay_method : 'card',
            merchant_uid : 'merchant_' + new Date().getTime(),
            name : '<%=b[0].getBookTitle()%> 외 <%=size-1%>권',
            amount : <%=sum %>,
        }, function(rsp) {
            if ( rsp.success ) {
            	//성공시 이동할 페이지
               location.href='<%=request.getContextPath()%>/user/orderComplete?bookNo=<%=str%>&userId=<%=u.getUserId()%>&';
            } else {
                msg = '결제에 실패하였습니다.';
                msg += '에러내용 : ' + rsp.error_msg;
                //실패시 이동할 페이지
                location.href="<%=request.getContextPath()%>/user/cartView?userId=<%=u.getUserId()%>";
            	alert(msg);
            }
        });
        
        
        
    });
    </script>
    <form id="OrderTable">
    	<input type="hidden" name="userId" value="<%=u.getUserId() %>" />
    	<input type="hidden" name="BookNo" value="<%=str %>" />
    	<input type="hidden" name="sum" value="<%=sum %>" />
    </form>
 
</body>
</html>