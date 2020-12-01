<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<%
	Book book = (Book)request.getAttribute("book");
	String bookTagCode = book.getBookTagCode() != null? book.getBookTagCode() : "";
	String bookImgRenameFileName = book.getBookImgRenameFileName() != null? book.getBookImgRenameFileName() : "";
%>
<title>BookInfoView</title>

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
#main .btn-div{position: relative; text-align: center; }
#main .search-div{position: relative; text-align: center;}
#main nav{text-align: center;}
.container {width: 1000px;}
form{width: 600px; margin: auto;}
select, .textbox{width: 260px; height: 35px; font-size: 15px; color: #999;
        border: 1.3px solid #999; border-radius: 10px;
    }
.textbox{width: 260px; position: relative;}
textarea{font-size: 15px; color: #999;
        border: 1.3px solid #999; border-radius: 10px;
}
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
th {
    text-align: center;
}
td{
    position: relative;
    left: 20px;
}
</style>
<!-- css 끝 -->

<script>
	function deleteBook(){
		$("[name=deleteBookFrm]").submit();
	}
</script>
</head>
<body>
    <div class="container">
        <div id="main">
            <h1>도서 상세보기</h1>
            <hr><br>
            <br>
            <form action="<%=request.getContextPath()%>/book/deleteBook?bookNo=<%=book.getBookNo() %>" name="deleteBookFrm" method="post">
            	<input type="hidden" name="bookNo" value="<%=book.getBookNo()%>"/>
            	<input type="hidden" name="userLoggedInRole" value="<%=userLoggedIn.getUserRole()%>" />
            	<input type="hidden" name="userLoggedInId" value="<%=userLoggedIn.getUserId()%>" />
            </form>
            <form action="<%=request.getContextPath()%>/book/updateBookFrm?bookNo=<%=book.getBookNo()%>" method="post">
            	<input type="hidden" name="bookNo" value="<%=book.getBookNo()%>"/>
                <table class="table">
                    <thead>

                        <tr>
                            <th>ISBN</th>
                            <td><%= book.getBookNo() %></td>
                        </tr>
                        <tr>
                            <th>제목</th>
                            <td><%= book.getBookTitle() %></td>
                        </tr>
                        <tr>
                            <th>저자</th>
                            <td><%=book.getAuthorCode() %></td>
                        </tr>
                        <tr>
                            <th>가격</th>
                            <td><%= book.getPrice() %></td>
                        </tr>
                        <tr>
                            <th>책소개</th>
                            <td>
                                <div style="width: 470px;">
									<%= book.getBookContent() %>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <th>이미지</th>
                            <td>
							<img src="<%=request.getContextPath() %>/images/<%= book.getBookImgRenameFileName()==null? 
									book.getBookImgOriginalFileName() : book.getBookImgRenameFileName() %>" width="300px" alt="">                            
							</td>
                        </tr>
                        <tr>
                            <th>카테고리</th>
                            <td>
                                <select name="" id="">
									<optgroup label="소설">
										<option value="" <%= Integer.parseInt(book.getCategoryCode()) == 2? "selected" : "disabled" %>>로맨스</option>
										<option value="" <%= Integer.parseInt(book.getCategoryCode()) == 3? "selected" : "disabled" %>>판타지</option>
										<option value="" <%= Integer.parseInt(book.getCategoryCode()) == 4? "selected" : "disabled" %>>공포/스릴러</option>
										<option value="" <%= Integer.parseInt(book.getCategoryCode()) == 5? "selected" : "disabled" %>>추리</option>
										<option value="" <%= Integer.parseInt(book.getCategoryCode()) == 6? "selected" : "disabled" %>>액션</option>
									</optgroup>
									<optgroup label="에세이/시">
										<option value="" <%= Integer.parseInt(book.getCategoryCode()) == 8? "selected" : "disabled" %>>에세이</option>
										<option value="" <%= Integer.parseInt(book.getCategoryCode()) == 9? "selected" : "disabled" %>>시</option>
									</optgroup>
									<optgroup label="컴퓨터/IT">
										<option value="" <%= Integer.parseInt(book.getCategoryCode()) == 11? "selected" : "disabled" %>>IT비지니스</option>
										<option value="" <%= Integer.parseInt(book.getCategoryCode()) == 12? "selected" : "disabled" %>>개발/프로그래밍</option>
									</optgroup>
									<optgroup label="교재/수험서">
										<option value="" <%= Integer.parseInt(book.getCategoryCode()) == 14? "selected" : "disabled" %>>초등학교</option>
										<option value="" <%= Integer.parseInt(book.getCategoryCode()) == 15? "selected" : "disabled" %>>중학교</option>
										<option value="" <%= Integer.parseInt(book.getCategoryCode()) == 16? "selected" : "disabled" %>>고등학교</option>
										<option value="" <%= Integer.parseInt(book.getCategoryCode()) == 17? "selected" : "disabled" %>>기타수험서</option>
									</optgroup>
                                    <!--disabled : 선택하지 못하게 함-->
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <th>출판사</th>
                            <td><%= book.getUserId()%></td>
                        </tr>
                        <tr>
                            <th>판매량(권)</th>
                            <td><%=book.getSaleCount() %></td>
                        </tr>
                    </thead>
                </table>
                <div class="btn-div">
                <% if(userLoggedIn != null
                		&& UserService.USER_ROLE_PUBLISHER.equals(userLoggedIn.getUserRole())){ %>
                    <button type="submit" class="btn btn-primary">도서 수정</button>
                <% } %>
                    <button type="button" onclick="deleteBook();" class="btn btn-primary">도서 삭제</button>
                    <% if(!userLoggedIn.getUserRole().equals("A")) { %>
                    <input type="button" class="btn btn-primary" style="width:50px;"
                    onclick="location.href='<%=request.getContextPath() %>/publisher/bookListView?userId=<%=userLoggedIn.getUserId() %>'" value="목록" />
                    <% } else { %>
                    <input type="button" class="btn btn-primary" style="width:50px;"
                    onclick="location.href='<%=request.getContextPath() %>/admin/allBookList'" value="목록" />
                    <% } %>
                </div>
            </form>
            <br><br>
        </div>    
    </div>
</body>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>