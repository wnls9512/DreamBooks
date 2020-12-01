<%@page import="book.model.vo.Tag"%>
<%@page import="book.model.vo.Comments"%>
<%@page import="java.util.List"%>
<%@page import="oracle.net.aso.b"%>
<%@page import="book.model.vo.BookExtends"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	BookExtends b = (BookExtends)request.getAttribute("book");
	List<BookExtends> list = (List<BookExtends>)request.getAttribute("list");
	List<Book> rankBookList = (List<Book>)request.getAttribute("rankList");
	List<Comments> commentList = (List<Comments>)request.getAttribute("commentList");
	List<Tag> tagList = (List<Tag>)request.getAttribute("tagList");
	String category_top = (String)request.getAttribute("category_top");
%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<title>BookInfo View</title>

<!-- js 시작 -->
<script src="<%=request.getContextPath() %>/js/jquery-3.5.1.js"></script>
<script src="<%=request.getContextPath() %>/js/bootstrap.js"></script>
<!-- js 끝 -->

<!-- css 시작  -->
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/bootstrap.css" />

<style>
*{font-family: 'NanumSquare', sans-serif !important; font-size:15px;}

#book-info-view {
	width: 1100px;
	float: center;
	margin: 0 auto;
/* 	padding: 30px 50px; */
	padding-left:50px;
}

article {
	width:800px;
	float:left;
	margin: 50px;
}

sidebar {
	/* float: left; */
	margin: 50px 0px 0px 0px;
}

#book-info-tbl {
	width: 800px;
	padding: 15px;
}

#tag-content, #input-tag {width: 700px;}

.form-control {
  display: block;
  width: 75%;
  float:left;
  height: 46px;
  padding: 6px 12px;
  margin: 0px 23px;
  font-size: 14px;
  line-height: 1.428571429;
  color: #555555;
  vertical-align: middle;
  background-color: #ffffff;
  background-image: none;
  border: 1px solid #cccccc;
  border-radius: 4px;
  -webkit-box-shadow: inset 0 1px 1px rgba(0, 0, 0, 0.075);
          box-shadow: inset 0 1px 1px rgba(0, 0, 0, 0.075);
  -webkit-transition: border-color ease-in-out 0.15s, box-shadow ease-in-out 0.15s;
          transition: border-color ease-in-out 0.15s, box-shadow ease-in-out 0.15s;
}

.comment-editor {margin-bottom: 40px;}

#comment-tbl {margin: 20px 0px;}

#user-id {
	width:130px;
	text-align:center;
	vertical-align: middle;
	padding: 15px 7px;
}
#user-comment {
	width: 450px;
	padding: 15px 7px;
}

#user-id:hover, #user-comment:hover {
	background-color: rgba(216, 216, 216, 0.334);
	border-radius:5px;
	transition: color 0.2s ease-in-out, background-color 0.2s ease-in-out, border-color 0.2s ease-in-out, box-shadow 0.15s ease-in-out;
}

.tag-btn {
	width:100px;
	vertical-align:middle;
	margin-top:5px;
}

/* 버튼 */
.order-btn:hover, .cart-btn:hover, .tag-btn:hover, .comment-btn:hover {
    color: #fff;
    background-color: #B596FD;
    border-color: #B596FD;
    box-shadow: 0 2px 2px 0 rgba(156, 39, 176, 0.14), 0 3px 1px -2px rgba(156, 39, 176, 0.2), 0 1px 5px 0 rgba(156, 39, 176, 0.12);
    border-radius: 30px;
}
.cart-btn, .order-btn, .tag-btn, .comment-btn {
    display: inline-block;
    width: 120px;
    height: 33px;
    font-weight: 400;
/*     text-align: center; */
    white-space: nowrap;
    vertical-align: middle;
    user-select: none;
    border: 1px solid transparent;
    padding: 0.46875rem 1rem;
    font-size: 15px;
    line-height: 1.2;
    border-radius: 30px;
    transition: color 0.15s ease-in-out, background-color 0.15s ease-in-out, border-color 0.15s ease-in-out, box-shadow 0.15s ease-in-out;
}

#book-info{
	width:130px;
	margin:30px;
	float: left;
}

#book-tbl {margin: 0 auto;}

#book-tbl tr, #book-tbl td{
	text-align : center;
	font-size: 12px;
	font-weight: 400;
}
.nav-link{color:#f8f9fa;}

#hidden-btn .btn.btn-info{display:none;}
#comment-tr:hover #hidden-btn .btn.btn-info{display:inline;}
.tag-x{ background-color: rgba(0, 0, 0, 0); color: white; font-weight: bold; border: 0px;}

#best0 {
  animation-name: best;
  animation-duration: 2s;
  animation-fill-mode: forwards;
}
#best1 {
  animation-name: best;
  animation-duration: 2s;
  animation-delay: 1s;
  animation-fill-mode: forwards;
}
#best2 {
  animation-name: best;
  animation-duration: 2s;
  animation-delay: 2s;
  animation-fill-mode: forwards;
}

@keyframes best {
  from {color: #f8f9fa;}
  to {color: #428bca;}
}

</style>

<!-- script -->
<script>

function gotocart(){
	
	let $frm = $("[name=cartinsertFrm]");
	$frm.attr('action', '<%= request.getContextPath()%>/user/cartInsert')
		.attr('method', 'POST')
		.submit();
}

function gotoorder(){
	
	let $frm = $("[name=orderFrm]");
	$frm.attr('action', '<%= request.getContextPath()%>/user/BuyView')
		.attr('method', 'get')
		.submit();
}


$(function(){	
	$(".btn-delete").click(function(){
		if(!confirm('정말 삭제하시겠습니까?')) return;
		
		let bookCommentNo = $(this).val();
		/* alert(bookCommentNo); */
		
		let $frm = $("[name=deleteCommentFrm]");
		$frm.children("[name=bookCommentNo]").val(bookCommentNo);
		$frm.attr("action", "<%=request.getContextPath()%>/book/bookCommentDelete")
			.attr("method", "POST")
			.submit();
	
	});

});

$(function(){
	$(".btn-update").click(function(){
		
		let bookInfo = $(this).val();
		/* alert(bookCommentNo); */		
		let arr = bookInfo.split("&");
		/* alert(arr[0]); */
		let bookCommentNo = arr[0];
		
		let $comment = arr[1];
				
		let $tr = $("<tr></tr>");
		let $tdtd = $("<td></td>");
		let $td = $("<td style='display:none; text-align:left;' colspan=2'></td>");		
			
		let $frm = $("<form action='<%=request.getContextPath() %>/book/bookCommentUpdate' method='post'></form>");
		$frm.append("<input type='hidden' name='book_no' value='<%=b.getBookNo() %>' />");
		$frm.append("<input type='hidden' name='bookCommentNo' value='"+bookCommentNo+"' />");
		$frm.append("<textarea class='form-control' name='bookCommentContent' cols='100' rows='1' style='margin:0px;'>" 
						+ $comment + "</textarea>");
		$frm.append("<button type='submit' class='btn btn-info' style='margin: 4px 10px;'>수정</button>");
		
		$td.append($frm);
		$tr.append($tdtd);
		$tr.append($td);
		
		let $commentTr = $(this).parent().parent();
		$tr.insertAfter($commentTr)
		   .children("td").slideDown(800)
		   .children("form").submit(function(){
			   let $textarea = $(this).find("textarea");
			   if($textarea.val().length == 0)
				   return false;
		   }).children("textarea").focus();
		$(this).off("click");
	});
	
});

function fx(tag){
	let yn = confirm("정말 삭제하시겠습니까?");				
	if(yn){				
		let $frm = $("[name=deleteTagFrm]");
		$frm.children("[name=tagCode]").val(tag);
		$frm.attr("action", "<%=request.getContextPath() %>/book/deleteTag");
		$frm.attr("method", "POST");
		$frm.submit();					
	};
}
</script>

</head>
<body>


<section id="book-info-view">

	<article>
	<!-- 기본 책 정보 -->
		<div>
			<table id="book-info-tbl">
			<tr>
				<td colspan=2><%=category_top %> > <%=b.getCategoryName() %></td>				
			</tr>
			<tr>
				<td rowspan=5 colspan=3>
					<img src="<%=request.getContextPath() %>/images/<%= b.getBookImgRenameFileName()==null? 
				            	b.getBookImgOriginalFileName() : b.getBookImgRenameFileName() %>" 
	                    		 style="width: 200px;"> 				
				</td>
				<td colspan=3><h3><%=b.getBookTitle() %></h3></td>
			</tr>
			<tr>
				<td>저자명</td>
				<td><%=b.getAuthorName() %> 저</td>
				<td></td>
			</tr>
			<tr>
				<td>출판사</td>
				<td><%=b.getUserName() %> 출판</td>
				<td></td>
			</tr>
			<tr>
				<td>판매가</td>
				<td style="text-align:left"><h4><%=b.getPrice() %> 원</h4></td>
				<td></td>
			</tr>
			<tr>
				<td><input type="button" class="cart-btn" id='cart' value="장바구니담기" onclick="gotocart();"/>
				</td> 
				<td><input type="button" class="order-btn" id='order' value="바로구매" onclick="gotoorder();"/></td>
				<td></td>
			</tr>
			</table>
			</table>
			<!--장바구니 폼 -->	
			<form name="cartinsertFrm">
			<input type="hidden" name="book_no" value="<%=b.getBookNo()%>" />
			<input type="hidden" name="user_id" value="<%=userLoggedIn.getUserId() %>" />
			<input type="hidden" name="price" value="<%=b.getPrice()%>" />
			</form>
			<!--구매하기 폼 -->	
			<form name="orderFrm">
			<input type="hidden" name="book_no" value="<%=b.getBookNo()%>" />
			</form>
		</div>
		<br />
		<br />
		<br />
		<!-- 감상태그 -->
		<div id="tag-content">
			<h2 style="padding-bottom:5px;">#감성태그</h2>
			<% if(tagList == null || tagList.isEmpty()) { %>
				<p>아직 추가된 태그가 없습니다. 태그를 추가해주세요</p>
			<%} else { %>
				<% for(Tag t : tagList) { %>
					<label class="badge badge-white" style="background: #7dc3ff">#<%=t.getTagName() %>
				<!-- x 버튼 보여주기 -->
				<% if(userLoggedIn != null 
					&&	(UserService.USER_ROLE_ADMIN.equals(userLoggedIn.getUserRole())
					|| t.getUserId().equals(userLoggedIn.getUserId()))){ %>	
					<button class="tag-x" value="<%=t.getTagCode() %>"
							onclick="fx(value);">x</button>
					<% } %>
					</label>
				<%} %>
			<%} %>
			<form name="deleteTagFrm">
				<input type="hidden" name="book_no" value="<%=b.getBookNo() %>"/>
				<input type="hidden" name="tagCode" />
			</form>
		
 			<!-- 태그 입력폼 -->
 			<form method="post"
				  action="<%=request.getContextPath() %>/book/insertTag"
				  name="tagFrm">
				<div id="input-tag">
					<div class="form-group bmd-form-group">
						<input type="hidden" name="tagbookno" value="<%=b.getBookNo() %>" />
						<input type="hidden" name="tagWriter" 
							   value="<%=userLoggedIn != null ? userLoggedIn.getUserId() : "" %>" />
								   
				 		<input type="text" class="form-control" placeholder="태그를 입력해주세요." name="tagname">
					</div>
					<button type="submit" style="width:100px;" class="tag-btn">
						<svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-hash" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
  						<path d="M8.39 12.648a1.32 1.32 0 0 0-.015.18c0 .305.21.508.5.508.266 0 .492-.172.555-.477l.554-2.703h1.204c.421 0 .617-.234.617-.547 0-.312-.188-.53-.617-.53h-.985l.516-2.524h1.265c.43 0 .618-.227.618-.547 0-.313-.188-.524-.618-.524h-1.046l.476-2.304a1.06 1.06 0 0 0 .016-.164.51.51 0 0 0-.516-.516.54.54 0 0 0-.539.43l-.523 2.554H7.617l.477-2.304c.008-.04.015-.118.015-.164a.512.512 0 0 0-.523-.516.539.539 0 0 0-.531.43L6.53 5.484H5.414c-.43 0-.617.22-.617.532 0 .312.187.539.617.539h.906l-.515 2.523H4.609c-.421 0-.609.219-.609.531 0 .313.188.547.61.547h.976l-.516 2.492c-.008.04-.015.125-.015.18 0 .305.21.508.5.508.265 0 .492-.172.554-.477l.555-2.703h2.242l-.515 2.492zm-1-6.109h2.266l-.515 2.563H6.859l.532-2.563z"/>
						</svg>
					태그 등록</button>
				</div>
			</form>
		</div>
		<br /><br />
		
		<!-- 책 소개 문단 -->
		<div>
			<table style="width:700px;">
				<tr>
					<td><h2>저자소개</h2></td>
				</tr>
				<tr>
					<td>
						<%=b.getAuthorContent() %>
					</td>
				</tr>
				<tr>
					<td><br /><br /></td>
				</tr>
				<tr>
					<td>
						
					</td>
				</tr>
				<tr>
					<td><br /><br /></td>
				</tr>
				<tr>
					<td><h2>책소개</h2></td>
				</tr>
				<tr>
					<td>
						<%=b.getBookContent() %>
					</td>
				</tr>
				<tr>
					<td><br /><br /></td>
				</tr>				
			</table>
		</div>
		
		<!-- 댓글 부분 -->
		<div class="comment-container">
			<h3>한줄평</h3>
			<!-- 댓글 작성폼 -->
			<div class="comment-editor">
				<form method="post"
					  action="<%=request.getContextPath() %>/book/insertcomment"
					  name="commentFrm">
					<div class="form-group bmd-form-group">
						<input type="hidden" name="bookno" value="<%=b.getBookNo() %>" />
						<input type="hidden" name="commentWriter" 
							   value="<%=userLoggedIn != null ? userLoggedIn.getUserId() : "" %>" />
				 		<textarea class="form-control" name="bookComment" id="bookComment"
							 	  cols="80" rows="3"
							 	  placeholder="감상을 적어주세요."></textarea>
					</div>
					<button type="submit" style="height:50px; margin:12px 10px" class="comment-btn">댓글 등록</button>
				</form>
			</div>
		
		<!-- 댓글 보기 -->
		<% if(commentList == null || commentList.isEmpty()){ %>
		<table id="comment-tbl">
		<tr>
			<th colspan = "2"> 아직 작성된 한줄평이 없습니다. 첫번째 한줄평을 작성해주세요. </th>
		</tr>
		</table>
        <% } else { %>
        <% for(Comments c : commentList){ %>
		<table id="comment-tbl">
			<tr id="comment-tr">
				<td id="user-id">
					<%=c.getUserId() %>
				</td>				
				<td id="user-comment">
					<%=c.getCommentContent() %> 
				</td>
				<td id="user-id">
					<%=c.getCommentDate() %>
				</td>
				
				<!-- 버튼 보여주기 -->
				<% if(userLoggedIn != null 
					&&	(UserService.USER_ROLE_ADMIN.equals(userLoggedIn.getUserRole())
					|| c.getUserId().equals(userLoggedIn.getUserId()))){ %>					
				<td id="hidden-btn">
					<button class="btn btn-info btn-update"
							value="<%=c.getCommentNo() %>&<%=c.getCommentContent() %>">수정</button>
					<button class="btn btn-info btn-delete"
							value="<%=c.getCommentNo() %>">삭제</button>				
				</td>
				<%} %>
				<!-- 버튼 보여주기 -->
				
			</tr>
		</table>
		<hr />
			<% } %>
		<% } %>
		
		<!-- 댓글이 제한개수를 넘어갈 경우 클릭시 더보기로 구현하고 싶습니다.. -->

		<!-- 한줄평 삭제 Frm -->
		<form name="deleteCommentFrm">
			<input type="hidden" name="bookCommentNo" />
			<input type="hidden" name="bookNo" value="<%=b.getBookNo() %>" />
		</form>
		<!-- 한줄평 삭제 Frm -->
		
		</div>
		<br />
		<br />
		
		<!-- 추천도서 -->
		<div id="recommend-book">
			<h3>추천도서</h3>
				<% if(list == null || list.isEmpty()){ %>
	                <div id="book-info">
	                    <table id="book-tbl">
							<tr>
								<th colspan = "3"> 검색 결과가 없습니다.</th>
							</tr>
						 </table>
					 </div>
	            <% } else { %>
	            		<% for(int i=0; i<4; i++) { %>
	            			<% int num = (int) (Math.random()*list.size()); %>
	            				<!-- 같은 카테고리의 책 추천 -->
	            			<% if(list.get(num).getBookNo().equals(b.getBookNo())) {
	            				/* 현재 보고있는 책은 추천하지 않도록 거르기  */
	            					i--;
	            				} else {
	            			%>
				            <div id="book-info">
				            	<table id="book-tbl">
				            		<tr>
				            			<td>
				            				<a href="<%=request.getContextPath() %>/book/bookView?bookNo=<%=list.get(num).getBookNo() %>">
				            					<img src="<%=request.getContextPath() %>/images/<%= list.get(num).getBookImgRenameFileName()==null? 
				            							list.get(num).getBookImgOriginalFileName() : list.get(num).getBookImgRenameFileName() %>" 
				                            	     style="width:100px;">
				                            </a>
				                         </td>
				                     </tr>
				                     <tr>
				                      	<td><%=list.get(num).getBookTitle() %></td>
				                      </tr>
				                      <tr>
				                         <td><%=list.get(num).getAuthorName() %></td>
				                      </tr>
				                      <tr>
				                         <td><%=list.get(num).getPrice() %>원</td>
				                      </tr>
				                  </table>
				             </div> 
				             <% } %>
		                 <% } %>
		           <% } %> 
		                                          
            </div>
	</article>

	<sidebar>
	<div class="" style="margin: 50px 0px;">
		<h4>
			<svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-award" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
  			<path fill-rule="evenodd" d="M9.669.864L8 0 6.331.864l-1.858.282-.842 1.68-1.337 1.32L2.6 6l-.306 1.854 1.337 1.32.842 1.68 1.858.282L8 12l1.669-.864 1.858-.282.842-1.68 1.337-1.32L13.4 6l.306-1.854-1.337-1.32-.842-1.68L9.669.864zm1.196 1.193l-1.51-.229L8 1.126l-1.355.702-1.51.229-.684 1.365-1.086 1.072L3.614 6l-.25 1.506 1.087 1.072.684 1.365 1.51.229L8 10.874l1.356-.702 1.509-.229.684-1.365 1.086-1.072L12.387 6l.248-1.506-1.086-1.072-.684-1.365z"/>
  			<path d="M4 11.794V16l4-1 4 1v-4.206l-2.018.306L8 13.126 6.018 12.1 4 11.794z"/>
			</svg>
			베스트셀러
		</h4>
		<ul style="list-style:none; margin:0px; padding:0px;">
		<% int j = 1; %>
		<% for(int i=0; i<rankBookList.size(); i++) { %>
			<li>
				<a id="best<%=i %>" class="nav-link" href="<%=request.getContextPath() %>/book/bookView?bookNo=<%=rankBookList.get(i).getBookNo() %>">
				<%=j++ %>위. <%=rankBookList.get(i).getBookTitle() %>
				</a>
			</li>
		<%} %>
		</ul>		
	</div>
	</sidebar>
            
</section>
</body>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>