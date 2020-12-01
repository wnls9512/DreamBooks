<%@page import="book.model.vo.BookExtends"%>
<%@page import="book.model.vo.PubBook"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<%
	List<BookExtends> displayList = (List<BookExtends>)request.getAttribute("displayList");
	List<Book> bestSeller = (List<Book>)request.getAttribute("bestSeller");
%>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/main.css" />
<title>main</title>
<!-- font -->
</head>
<style>
#main .btn-div{position: relative; text-align: center; }
#main .search-div{position: relative; text-align: center;}
#main nav{text-align: center;}
.container {width: 100%;}
body {text-align: center;}
/* .main1 h3 {color: white;} */
</style>
<body>
	<br /><br />
	<span class="ngc"><h2>#사람들이 지금 많이 읽고 있는 책</h2></span>
	<% for(Book b : bestSeller) { %>
    <div class="mySlides fade">
        <a href="<%=request.getContextPath() %>/book/bookView?bookNo=<%=b.getBookNo() %>">
        	<img src="<%=request.getContextPath() %>/images/<%=b.getBookImgOriginalFileName() %>" height="430px;"></a>
      </div>
     <%} %>
    <br>
    <div style="text-align:center">
      <span class="dot"></span> 
      <span class="dot"></span> 
      <span class="dot"></span> 
    </div>
    <br><br><br>
      <div class="newBook" style="background-color: blueviolet;">
      <br>
      <div style="position: absolute; left: 300px;">
      <span id="clock" class="clock" style="background-color: #B596FD; border-radius: 10%;padding: 5px;color: white">
      </span>
      </div>
      <br><br>
      <ul class="main1">
      	<span style="font-size: 30px; color: white;">#드림's Pick !</span>
      	<br />
       	<% for(int i=0; i<5; i++) { %>
       <li>
        	<div class="list1">
		       <a href="<%=request.getContextPath() %>/book/bookView?bookNo=<%=displayList.get(i).getBookNo() %>">
		       <img src="<%=request.getContextPath() %>/images/<%= displayList.get(i).getBookImgRenameFileName()==null ?  
		    		   displayList.get(i).getBookImgOriginalFileName() : displayList.get(i).getBookImgRenameFileName() %>" width="180px;" height="270px;"></a>

	           <p style="font-size: 13px; font-weight: 500;""><%=displayList.get(i).getBookTitle() %></p>	           

	           <span class="cont">
	           <%=displayList.get(i).getBookTitle() %>
				|
	           <%=displayList.get(i).getCategoryName() %>
	          	|
	          	<%=displayList.get(i).getAuthorName() %>
	          	|
	          	<%=displayList.get(i).getPrice() %>
	          </span>
          </div>
        </li>
          <%} %>
      </ul>
      
      <br><br><br> 
    </div>
    <br><br><br>
    <div class="category-book">
      <div style="position: absolute; left: 300px;">
        <span style="font-size: 30px;">#서재에 꽂아두고 싶은 책</span>
    </div>
    <br><br>
    <ul class="main2">
    <% for(int i=5; i<10; i++) { %>
         <li>
        	<div class="list1">
		       <a href="<%=request.getContextPath() %>/book/bookView?bookNo=<%=displayList.get(i).getBookNo() %>">
		       <img src="<%=request.getContextPath() %>/images/<%= displayList.get(i).getBookImgRenameFileName()==null? 
		    		 		  displayList.get(i).getBookImgOriginalFileName() : displayList.get(i).getBookImgRenameFileName() %>" width="180px;" height="270px;"></a>

	           <p style="font-size: 13px; font-weight: 500;"><%=displayList.get(i).getBookTitle() %></p>	           

	           <span class="cont">
	           <%=displayList.get(i).getBookTitle() %>
				|
	           <%=displayList.get(i).getCategoryName() %>
	          	|
	          	<%=displayList.get(i).getAuthorName() %>
	          	|
	          	<%=displayList.get(i).getPrice() %>
	          </span>
          </div>
        </li>
        <%} %>
      </ul>
    </div>
	<br><br>
</body>
    <script>
        var slideIndex = 0;
        showSlides();

        function showSlides() {
            var i;
            var slides = document.getElementsByClassName("mySlides");
            var dots = document.getElementsByClassName("dot");
            for (i = 0; i < slides.length; i++) {
            slides[i].style.display = "none";  
            }
            slideIndex++;
            if (slideIndex > slides.length) {slideIndex = 1}    
            for (i = 0; i < dots.length; i++) {
                dots[i].className = dots[i].className.replace(" active", "");
            }
            slides[slideIndex-1].style.display = "block";  
            dots[slideIndex-1].className += " active";
            setTimeout(showSlides, 3000); // Change image every 2 seconds
        }
        function printClock() {
    
            var clock = document.getElementById("clock");            // 출력할 장소 선택
            var currentDate = new Date();                                     // 현재시간
            var calendar = currentDate.getFullYear() + "-" + (currentDate.getMonth()+1) + "-" + currentDate.getDate() // 현재 날짜
            var amPm = 'AM'; // 초기값 AM
            var currentHours = addZeros(currentDate.getHours(),2); 
            var currentMinute = addZeros(currentDate.getMinutes() ,2);
            
            if(currentHours >= 12){ // 시간이 12보다 클 때 PM으로 세팅, 12를 빼줌
              amPm = 'PM';
              currentHours = addZeros(currentHours - 12,2);
            }

        
            clock.innerHTML = currentHours+":"+currentMinute + amPm; //날짜를 출력해 줌
            
            setTimeout("printClock()",1000);         // 1초마다 printClock() 함수 호출
        }

        function addZeros(num, digit) { // 자릿수 맞춰주기
            var zero = '';
            num = num.toString();
            if (num.length < digit) {
              for (i = 0; i < digit - num.length; i++) {
                zero += '0';
              }
            }
            return zero + num;
        }

    </script>

</body>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>