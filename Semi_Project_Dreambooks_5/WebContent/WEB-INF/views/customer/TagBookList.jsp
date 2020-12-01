<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
	width:170px;
	margin:40px;
	float: left;
}

#tag-part, #similar-genre{
	display: inline-block;
}

</style>

</head>
    <body>
            
        <div id="search-tag-result">
            <!-- 태그 검색 결과 -->
            <div id="tag-part">
                <h2>#태그</h2>
                <!-- 조회되는 책만큼 book-info 반복 -->
                <div id="book-info">
                    <table id="book-tbl">
                        <tr>
                            <td><a href=""><img src="<%= request.getContextPath() %>/images/mainbook1.jpg" width="150px" alt=""></a></td>
                        </tr>
                        <tr>
                            <td><h6>결심만 하는 당신에게</h6></td>
                        </tr>
                        <tr>
                            <td>아무개</td>
                        </tr>
                        <tr>
                            <td>6500원</td>
                        </tr>
                    </table>
                </div>

                <div id="book-info">
                    <table id="book-tbl">
                        <tr>
                            <td><a href=""><img src="<%= request.getContextPath() %>/images/mainbook1.jpg" width="150px" alt=""></a></td>
                        </tr>
                        <tr>
                            <td><h6>결심만 하는 당신에게</h6></td>
                        </tr>
                        <tr>
                            <td>아무개</td>
                        </tr>
                        <tr>
                            <td>6500원</td>
                        </tr>
                    </table>
                </div>

                <div id="book-info">
                    <table id="book-tbl">
                        <tr>
                            <td><a href=""><img src="<%= request.getContextPath() %>/images/mainbook1.jpg" width="150px" alt=""></a></td>
                        </tr>
                        <tr>
                            <td><h6>결심만 하는 당신에게</h6></td>
                        </tr>
                        <tr>
                            <td>아무개</td>
                        </tr>
                        <tr>
                            <td>6500원</td>
                        </tr>
                    </table>
                </div>

                <div id="book-info">
                    <table id="book-tbl">
                        <tr>
                            <td><a href=""><img src="<%= request.getContextPath() %>/images/mainbook1.jpg" width="150px" alt=""></a></td>
                        </tr>
                        <tr>
                            <td><h6>결심만 하는 당신에게</h6></td>
                        </tr>
                        <tr>
                            <td>아무개</td>
                        </tr>
                        <tr>
                            <td>6500원</td>
                        </tr>
                    </table>
                </div>

                <div id="book-info">
                    <table id="book-tbl">
                        <tr>
                            <td><a href=""><img src="<%= request.getContextPath() %>/images/mainbook1.jpg" width="150px" alt=""></a></td>
                        </tr>
                        <tr>
                            <td><h6>결심만 하는 당신에게</h6></td>
                        </tr>
                        <tr>
                            <td>아무개</td>
                        </tr>
                        <tr>
                            <td>6500원</td>
                        </tr>
                    </table>
                </div>

                <div id="book-info">
                    <table id="book-tbl">
                        <tr>
                            <td><a href=""><img src="<%= request.getContextPath() %>/images/mainbook1.jpg" width="150px" alt=""></a></td>
                        </tr>
                        <tr>
                            <td><h6>결심만 하는 당신에게</h6></td>
                        </tr>
                        <tr>
                            <td>아무개</td>
                        </tr>
                        <tr>
                            <td>6500원</td>
                        </tr>
                    </table>
                </div>


            </div>

            <!-- 구분선 -->
            <hr>

            <!-- 비슷한 장르 -->
            <div id="similar-genre">
                <h2>검색한 태그와 비슷한 장르</h2>
                
                <!-- 조회되는 책만큼 book-info 반복 -->
                <div id="book-info">
                    <table id="book-tbl">
                        <tr>
                            <td><a href=""><img src="<%= request.getContextPath() %>/images/mainbook1.jpg" width="150px" alt=""></a></td>
                        </tr>
                        <tr>
                            <td><h6>결심만 하는 당신에게</h6></td>
                        </tr>
                        <tr>
                            <td>아무개</td>
                        </tr>
                        <tr>
                            <td>6500원</td>
                        </tr>
                    </table>
                </div>

            </div>

        </div>



</body>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>