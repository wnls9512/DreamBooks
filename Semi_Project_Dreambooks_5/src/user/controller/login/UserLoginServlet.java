package user.controller.login;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import book.model.service.BookService;
import book.model.vo.Book;
import book.model.vo.BookExtends;
import common.util.Utils;
import user.model.service.UserService;
import user.model.vo.Users;

/**
 * 
 * 로그인 버튼 클릭
 *
 */
@WebServlet("/user/login")
public class UserLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UserLoginServlet() {
        super();
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
 
		List<Book> bestSeller = new BookService().selectRankBoardList();
		List<BookExtends> displayList = new BookService().displayBookList();

		request.setAttribute("bestSeller", bestSeller);
		request.setAttribute("displayList", displayList);
		request.getRequestDispatcher("/WEB-INF/views/customer/MainContentsView.jsp")
				.forward(request, response);
    }


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//사용자 입력값 처리
		String userId = request.getParameter("login-userid");
		//암호화
		String password = Utils.getEncryptedPassword(request.getParameter("login-pwd"));
		String saveId = request.getParameter("login-saveId");
//		System.out.println("userId@servlet="+userId);
//		System.out.println("userPwd@servlet="+password);
		
		//업무로직
		Users user = new UserService().selectOne(userId);
		
		//응답처리
		//아이디, 비번이 모두 일치하는 경우
		if(user != null
		    && userId.equals(user.getUserId())
			&& password.equals(user.getPassword())) {
			
			//로그인 유지 시작//
			//세션가져오기
			//세션객체 없다면 (첫접속), 새로 생성 후 리턴하고 색션객체 있다면, 가져옴
			HttpSession session = request.getSession(true);
			
			//세션유효시간설정 : 초
			session.setMaxInactiveInterval(60*60*24);
			
			//세션에 로그인한 사용자 정보 저장
			session.setAttribute("userLoggedIn", user);
			//로그인 유지 끝//
			
			//아이디 저장 시작//
			//쿠키(saveId) 처리
			Cookie c = new Cookie("saveId", userId);
			c.setPath(request.getContextPath());//쿠키유효디렉토리 설정
			
			//saveId check
			if(saveId != null) {
				c.setMaxAge(60*60*24*7); //7일
			}
			//saveId non-check
			else {
				c.setMaxAge(0);
			}
			response.addCookie(c);
			//아이디 저장 끝//
			
			doGet(request, response);
			
		}
		//아이디 or 비번이 틀린 경우
		else {
			request.setAttribute("msg", "아이디 또는 비밀번호가 일치하지 않습니다.");
			request.setAttribute("loc", "/?login");
			
			//msg.jsp
			request.getRequestDispatcher("/WEB-INF/views/common/popup.jsp")
					.forward(request, response);			
		}
	}
}