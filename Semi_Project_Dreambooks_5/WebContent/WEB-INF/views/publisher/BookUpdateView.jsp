<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<%
	String pubId = (String)request.getAttribute("userId");
	Book book = (Book)request.getAttribute("book");
%>
<title>BookUpdateView</title>

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
form{width: 500px; margin: 0 auto;}
select, .textbox{width: 300px; height: 35px; font-size: 15px; color: #999;
        border: 1.3px solid #999; border-radius: 10px;
    }
.textbox{width: 300px; position: relative;}
textarea{font-size: 15px; color: #999; width:300px;
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

td{
    position: relative;
    left: 20px;
}
span#frame {
	position : absolute;
	left: 98px;
	top: 12px;
	width: 250px;
	background-color: #fff;
	text-align: left;
}

</style>
<!-- css 끝 -->

<script>
function bookValidate(){
	
	let $isbn1 = $("[name=isbn1]");
	let $isbn2 = $("[name=isbn2]");
	let $isbn3 = $("[name=isbn3]");
	let $bookTitle = $("[name=bookTitle]");
	let $bookAuthor = $("[name=bookAuthor]");
	let $price = $("[name=price]");
	let $bookContent = $("[name=bookContent]");
	let $category = $("select[name=category]");
	
	let num = $isbn1.val() + $isbn2.val() + $isbn3.val();
	// 공백이거나, 숫자 13글자가 안 되면 X
	if(
		num.trim().length == 0 || (!/\d{13}$/.test(num))		
		){
		alert("ISBN을 정확히 입력해주세요.");
		return false;
	}
	
	if($bookTitle.val().trim().length == 0){
		alert("도서 제목을 입력하세요.");
		return false;
	}
	
	if($bookAuthor.val().trim().length == 0){
		alert("저자명을 입력하세요.");
		return false;
	}
	
 	if($price.val().trim().length == 0){
		alert("도서 가격을 입력하세요.");
		return false;
	}

	//도서가격 0으로 시작X, 숫자만 입력가능, 공백 X
	if(!/^[1-9][0-9]\S/g.test($price.val())) {
		alert("도서가격을 정확히 입력해주세요.")
		return false;
	}
	
	if($bookContent.val().trim().length == 0){
		alert("책 소개를 입력하세요.");
		return false;
	}

/*  	var chk = document.getElementById('upFile');
	if(!chk.value){
		alert("이미지를 업로드해주세요.");
		return false;
	}
	
	var chkFile = document.getElementById('bookFile');
	if(!chkFile.value){
		alert("파일을 업로드해주세요.");
		return false;
	}  */
	

	if($category.val()==null){
		alert("카테고리를 선택해주세요.");
		return false;
	}
	
	return true;
}


function bookView(){
	history.go(-1);
}
$(function(){
	//사용자가 파일을 선택/해제한 경우
	$("[name=upFile]").change(function(){
		console.log($(this).val());
		if($(this).val() != ""){
			$("#frame").hide();
		}else{
			$("#frame").show();
		}
			
	});
});

$(function(){
	//사용자가 파일을 선택/해제한 경우
	$("[name=bookFile]").change(function(){
		console.log($(this).val());
		if($(this).val() != ""){
			$("#frame").hide();
		}else{
			$("#frame").show();
		}
			
	});
});
</script>


</head>
<body>
	<div class="container">
        <div id="main">
            <h1>도서 수정</h1>
            <hr><br>
            <br>
            <form action="<%=request.getContextPath()%>/publisher/updateBook?bookNo=<%=book.getBookNo()%>"
            	  enctype="multipart/form-data"
            	  method="post">
                <table class="table">
                    <thead>
                        <tr>
                            <th>ISBN</th>
                            <td>
                                <input type="text" name="isbn1" id="isbn1" class="textbox" value="<%=book.getBookNo().substring(0,5) %>" maxlength="5" style="width: 90px;">
                                -
                                <input type="text" name="isbn2" id="isbn3" class="textbox" value="<%=book.getBookNo().substring(5,9) %>" maxlength="4" style="width: 90px;">
                                -
                                <input type="text" name="isbn3" id="isbn4" class="textbox" value="<%=book.getBookNo().substring(9) %>" maxlength="4" style="width: 90px;">
                            </td>
                        </tr>
                        <tr>
                            <th>제목</th>
                            <td>
                                <input type="text" name="bookTitle" value="<%=book.getBookTitle() %>" id="bookTitle" class="textbox">
                            </td>
                        </tr>
                        <tr>
                            <th>저자</th>
                            <td>
                                <input type="text" name="bookAuthor" value="<%=book.getAuthorCode() %>" id="bookAuthor" class="textbox">
                            </td>
                        </tr>
                        <tr>
                            <th>가격</th>
                            <td>
                                <input type="text" name="price" id="price" value="<%=book.getPrice() %>" class="textbox">&#32;원
                            </td>
                        </tr>
                        <tr>
                            <th style="vertical-align: middle;">책소개</th>
                            <td>
                                <textarea name="bookContent" id="bookContent" cols="30" rows="4" ><%=book.getBookContent()%></textarea>
                            </td>
                        </tr>
                        <tr>
                            <th style="vertical-align: middle;">이미지</th>
                            <td>
                                <input type="file" name="upFile" id="upFile">
                                <span id="frame"><%= book.getBookImgOriginalFileName() != null ? book.getBookImgOriginalFileName() : ""%></span>
                                <input type="hidden" name="oldOriginalFileName" 
                                		value="<%=book.getBookImgOriginalFileName() != null ? book.getBookImgOriginalFileName() : ""%>" />
                                <input type="hidden" name="oldRenamedFileName" 
                                		value="<%=book.getBookImgRenameFileName() != null? book.getBookImgRenameFileName() : ""%>" />
                               	<!-- 첨부파일 삭제 버튼! 기존 첨부파일이 존재할 때만! -->
                               	<!-- 첨부파일 아예 없애는 것..우리는 이미지가 필수기 때문에 X -->
<%--                                 	<% if(book.getBookImgOriginalFileName() != null) { %>
                               		<br />
                               		<input type="checkbox" name="delFile" id="delFile" />
                               		<label for="delFile">첨부파일삭제</label>
                               	<% } %>  --%>
                            </td>
                        </tr>
                        <tr>
                        	<th>도서 파일</th>
                        	<td>
                        		<input type="file" name="bookFile" id="bookFile" />
                        		<span id="frame"><%=book.getBookFileOriginalName() %></span>
                        		<input type="hidden" name="oldOriginalBookFileName" 
                        				value="<%=book.getBookFileOriginalName()%>"/>
                        		<input type="hidden" name="oldRenamedBookFileName" 
                        				value="<%=book.getBookFileRenamedName() != null? book.getBookFileRenamedName() : "" %>"/>
                        	</td>
                        </tr>
                        <tr>
                            <th>카테고리</th>
                            <td>
                                <select name="category" id="category">
                                	<option value="none" selected disabled hidden>카테고리를 선택해주세요.</option>

									<optgroup label="소설">
										<option value="2" <%= book.getCategoryCode().equals("2")? "selected" : "" %>>로맨스</option>
										<option value="3" <%= book.getCategoryCode().equals("3")? "selected" : "" %>>판타지</option>
										<option value="4" <%= book.getCategoryCode().equals("4")? "selected" : "" %>>공포/스릴러</option>
										<option value="5" <%= book.getCategoryCode().equals("5")? "selected" : "" %>>추리</option>
										<option value="6" <%= book.getCategoryCode().equals("6")? "selected" : "" %>>액션</option>
									</optgroup>
									<optgroup label="에세이/시">
										<option value="8" <%= book.getCategoryCode().equals("8")? "selected" : "" %>>에세이</option>
										<option value="9" <%= book.getCategoryCode().equals("9")? "selected" : "" %>>시</option>
									</optgroup>
									<optgroup label="컴퓨터/IT">
										<option value="11" <%= book.getCategoryCode().equals("11")? "selected" : "" %>>개발/프로그래밍</option>
										<option value="12" <%= book.getCategoryCode().equals("12")? "selected" : "" %>>IT비지니스</option>
									</optgroup>
									<optgroup label="교재/수험서">
										<option value="14" <%= book.getCategoryCode().equals("14")? "selected" : "" %>>초등학교</option>
										<option value="15" <%= book.getCategoryCode().equals("15")? "selected" : "" %>>중학교</option>
										<option value="16" <%= book.getCategoryCode().equals("16")? "selected" : "" %>>고등학교</option>
										<option value="17" <%= book.getCategoryCode().equals("17")? "selected" : "" %>>기타수험서</option>
									</optgroup>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <th>출판사</th>
                            <td>
                                <input type="text" name="userId" id="userId" 
                                       value="<%=userLoggedIn.getUserId()%>" 
                                       class="textbox"
                                       readonly>
                            </td>
                        </tr>
                    </thead>
                </table>
                <div class="btn-div">
                    <button type="submit" class="btn btn-primary"
                    		onclick="return bookValidate();">수정</button>
                    <input type="button" class="btn btn-primary" value="취소" onclick="bookView();">
                </div>
            </form>
            <br><br>
    
        </div>    
    </div>
</body>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>