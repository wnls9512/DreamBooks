<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	//쿠키
	Cookie[] cookies = request.getCookies();
	boolean saveIdChecked = false;
	String saveIdValue = "";
	
	if(cookies != null){
		for(Cookie c: cookies){
			String k = c.getName();
			String v = c.getValue();
			
			if("saveId".equals(k)){
				saveIdChecked = true;
				saveIdValue = v;
			}
		}
	}
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>INDEX::LOGIN</title>

<!-- js 시작 -->
<script src="<%=request.getContextPath() %>/js/jquery-3.5.1.js"></script>
<script src="<%=request.getContextPath() %>/js/bootstrap.js"></script>
<script src="<%=request.getContextPath() %>/js/login-register.js"></script>
<script src="<%=request.getContextPath() %>/js/info.js"></script>
<script src="<%=request.getContextPath() %>/js/wrap1.js"></script>
<!-- js 끝 -->

<!-- css 시작  -->
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/bootstrap.css" />
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/coming-sssoon.css" />
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/login-register.css" />
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/info.css" />
<!-- font -->
<link href="http://netdna.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.css" rel="stylesheet">
<link href='http://fonts.googleapis.com/css?family=Grand+Hotel' rel='stylesheet' type='text/css'>
<link href="https://fonts.googleapis.com/css2?family=Nanum+Pen+Script&display=swap" rel="stylesheet">
<!-- css 끝 -->

<script>
<% if(request.getParameter("login") != null 
	|| request.getParameter("join") != null) { %>
	$(()=>{
		$("#loginModal").modal();		
		shakeModal();
	});
<% }%>
$(function(){
	$("[name=userEnrollFrm]").submit(function(){
		//아이디검사
		let $userId = $("#enroll_id_");

		if(!/^[\w]{4,}$/.test($userId.val())){
			alert("아이디가 유효하지 않습니다.");
			$userId.focus();
			return false;
		}
		
		//아이디 중복검사 
		let $isIdValid = $("#isIdValid");
		if($isIdValid.val() == 0){
			alert("아이디 중복검사 해주세요.");
			return false;
		}
		
		//비밀번호 검사
		let $pwd1 = $("#enroll_password");
		let $pwd2 = $("#enroll_password_confirmation");
		
		if(!/^[A-Za-z0-9+]{4,12}$/.test($pwd1.val())){
			alert("4~12자 영문 대 소문자, 숫자를 사용하세요.");
			$pwd1.focus();
			return false;
		}

		if($pwd1.val() !== $pwd2.val()){
			alert("비밀번호가 일치하지 않습니다.");
			$pwd1.focus();
			return false;
		}
		
		
		
		return true;
	});
});
function isEqualPwd(obj, viewObj){
    var pwd = document.getElementById("enroll_password");
    var pwdcheck = obj.value;
   
    if(pwd.value == pwdcheck){
        document.getElementById(viewObj).innerHTML=' ';
    }
    else{
        document.getElementById(viewObj).innerHTML='비밀번호가 일치하지 않습니다.'
    }
}

function checkIdDuplicate(){
	let $enroll_id = $("#enroll_id_");
	if(!/^[\w]{4,}$/.test($enroll_id.val())){
		alert("유효한 아이디를 입력해주세요.");
		$enroll_id.select();
		return;
	}
	
	//팝업생성
	let title = "checkIdDuplicatePopup";
	let spec = "left=300px, top=300px, width=300px, height=200px";
	let popup = open("", title, spec);
	//url부분은 form이 제출될 주소가 오므로, 공란처리
	
	let $frm = $("[name=checkIdDuplicateFrm]");
	$frm.attr("action", "<%=request.getContextPath()%>/user/checkIdDuplicate");
	$frm.attr("method", "POST");
	$frm.attr("target", title);//폼과 팝업을 연결
	$frm.find("[name=enroll_id]").val($enroll_id.val());
	$frm.submit();
	
	
}
</script>

</head>
<body>

    <div class="main" style="background-image: url('<%=request.getContextPath() %>/images/background2.jpg')">
    
        <!-- <div class="cover black" data-color="black"></div> -->
        
        <!--logo-->
        <div class="container wrapper">
            <div class="title">    
                <img src="<%=request.getContextPath() %>/images/logo.png" alt="logo">
            </div>
            <div class="content">
                <div class="subscribe">
                    <div class="row">
                        <button type="submit" class="btn btn-info btn-fill" onclick="openLoginModal();">LOGIN</button>
                        <button type="submit" class="btn btn-info btn-fill" onclick="openRegisterModal();">SIGNUP</button>
                    </div>
                </div>
            </div>
        </div>
        <!--/logo-->

        <!--info-->
        <div class="info">
            <h1><strong>매년 독서하기</strong>가
            <br>
            목표인
            <br>
            <strong>당신에게</strong></h1>
            <div class="info-image">
                <img src="<%=request.getContextPath() %>/images/내 게시물.png" alt="">
            </div>
        </div>
        </div>
        <!--/info-->
        <!--footer-->
        <div class="footer">
            <div class="container">
                Copyright© RedingGirls All rights reserved.
            </div>
        </div>
        <!--/footer-->
       

        <!--로그인창-->
        <div class="modal fade login" id="loginModal">
            <div class="modal-dialog login animated">
                <div class="modal-content">
                    <!--로그인 header-->
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        <h4 class="modal-title">Login with</h4>
                    </div>
                    <!--/로그인 header-->
                    <!--로그인 body -->
                    <div class="modal-body">
                        <div class="box">
                            <div class="content">
                              

                                <div class="error"></div>
                                <!--로그인창-->
                                <div class="form loginBox">
                                    <form method="post" 
                                    	  action="<%=request.getContextPath() %>/user/login" 
                                    	  accept-charset="UTF-8">
                                        <input type="text" 
                                        	   class="form-control"  
                                        	   placeholder="Id" 
                                        	   name="login-userid"
                                        	   value="<%= saveIdChecked ? saveIdValue : "" %>">
                                        <input type="password"
                                        	   class="form-control" 
                                        	   placeholder="Password" 
                                        	   name="login-pwd">
                                        <input type="checkbox" name="login-saveId" id="saveId" <%= saveIdChecked ? "checked" : " " %>>
                                        <label for="saveId">아이디 저장</label>
                                        <br><br>
                                        <input class="btn btn-default btn-login" type="submit" value="Login" >
                                    </form>
                                </div>
                                <!--/로그인창-->
                         </div>
                    </div>

                   <!--회원가입 창-->
                   <div class="box">
                        <div class="content registerBox" style="display:none;">
                            <div class="form">
							<!-- 아이디 중복 체크폼 -->
							<form name="checkIdDuplicateFrm">
								<input type="hidden" name="enroll_id" />
							</form>
                             <form method="post" name="userEnrollFrm" html="{:multipart=>true}" data-remote="true" action="<%=request.getContextPath()%>/user/userEnroll" accept-charset="UTF-8">
                                 NAME <br>
                                 <input id="enroll_name" name="enroll_name"type="text" class="form-control" placeholder="홍길동">
                                 ID 
                                 <input id="enroll_id_" name="enroll_id" type="text" class="form-control" placeholder="honggd">
                                 <input class="btn btn-default btn-register" type="button" value="Check ID" style="background-color:gray;" onclick="checkIdDuplicate();">
                                 <input type="hidden" id="isIdValid" value="0"/>
                                 PASSWORD 
                                 <br>
                                  <span id="chkPwView" style="color:red;"></span>
                                 <br />
                                 <input id="enroll_password" name="enroll_password" class="form-control" type="password" placeholder="Password" name="password">
                                 <input id="enroll_password_confirmation" name="enroll_password_confirmation" onkeyup="isEqualPwd(this,'chkPwView');" class="form-control" type="password" placeholder="Repeat Password" name="password_confirmation">
                                 EMAIL <br>
                                 <input id="enroll_email" name="enroll_email"class="form-control" type="text" placeholder="honggd@gmail.com" name="email">
                                 PHONE <br>
                                 <input id="enroll_phone" name="enroll_phone" type="tel" class="form-control" placeholder="01012341234" maxlength="11">
                                 <br>
                                 <input class="btn btn-default btn-register" type="submit" value="Create account" name="commit">
                                </form>
                            </div>
                        </div>
                    </div>
                    <!--/회원가입 창-->

                    <!--야이디찾기 창-->
                    <div class="box">
                        <div class="content findIdBox" style="display:none;">
                            <div class="form">
                        <form method="post" html="{:multipart=>true}" data-remote="true" name="searchIdFrm" action="<%=request.getContextPath()%>/user/searchId" accept-charset="UTF-8">
                            NAME <br>
                            <input type="text" class="form-control" 
                            		id="name1" 
                            		placeholder="홍길동" 
                            		name="findId-name">
                            EMAIL <br>
                            <input type="email" class="form-control" 
                            		id="email1" 
                            		placeholder="honggd@gmail.com" 
                            		name="findId-email">
                            <br>
                            <input class="btn btn-default btn-findId" type="submit" value="아이디 찾기" name="commit">
                        </form>
                    </div>
                    <!--/야이디찾기 창-->
                </div>
                </div>
                <!--패스워드찾기-->            
                <div class="box">
                <div class="content findPwdBox" style="display:none;">
                    <div class="form">
                            <form method="post" html="{:multipart=>true}" data-remote="true" action="<%=request.getContextPath() %>/user/findPassword" accept-charset="UTF-8">
                                ID <br>
                                <input type="text" class="form-control" name="findPwdId" id="findPwdId" placeholder="honggd">
                                EMAIL <br>
                                <input class="form-control" type="text" name="findPwdEmail" id="findPwdEmail" placeholder="honggd@gmail.com" name="email">
                                <br>
                                <input class="btn btn-default btn-findPwd" type="submit" value="임시 비밀번호 발급" name="commit"/>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
            <!--패스워드찾기-->             
            <!--/로그인 body -->

                <div class="modal-footer">
                    <div class="forgot login-footer">
                        <span>
                            Looking to
                            <a href="javascript: showRegisterForm();">create an account</a>?    
                        </span>
                    </div>
                    <div class="forgot findIdBox-footer">
                        <span>
                            Forgot
                            <a href="javascript: showFindIdForm();">Id</a>?
                        </span>
                    </div>
                    <div class="forgot findPwdBox-footer">
                        <span>
                            Forgot
                            <a href="javascript: showFindPwdForm();">Password</a>?
                        </span>
                    </div>
                    <div class="forgot register-footer" style="display:none">
                         <span>Already have an account?</span>
                         <a href="javascript: showLoginForm();">Login</a>
                    </div>
                </div>
          </div>
      </div>
  </div>


<div class="wrap1 list">
    <h1 class="up-on-scroll">방안까지
    <br>책을
    <br><strong>가져다 드립니다</strong></h1>
    <img src="<%=request.getContextPath() %>/images/북선반.png" alt="북선반입니다." class="up-on-scroll">
</div>


</body>
</html>