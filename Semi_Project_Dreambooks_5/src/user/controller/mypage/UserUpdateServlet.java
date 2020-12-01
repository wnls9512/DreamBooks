package user.controller.mypage;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import user.model.service.UserService;
import user.model.vo.Users;

/**
 * Servlet implementation class UserUpdateServlet
 */
@WebServlet("/user/userupdate")
public class UserUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UserUpdateServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/customer/updateCustomer.jsp")
		   .forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//1. 사용자 입력값
		String userId = request.getParameter("userId");
		String userName = request.getParameter("userName");
		String email = request.getParameter("eamil");
		String phone = request.getParameter("phone");
//		System.out.println("userName@servlet = " + userName);
//		System.out.println("userId@servlet = "+ userId);
//		System.out.println("email@servlet = "+email);
//		System.out.println("phone@servlet = "+phone);
		
		Users updateUser = new Users(userId, null, userName, null, email, phone, null);
//		System.out.println("updateUser = "+ updateUser);
		//2. 업무 로직
		int result = new UserService().updateUser(updateUser);
//		System.out.println("result@servlet = " + result);
		
		HttpSession session = request.getSession();
		Users userLoggedIn = (Users)session.getAttribute("userLoggedIn");
		
		//세션에 변경내역 반영
		if(result>0 && userId.equals(userLoggedIn.getUserId())) {
			userLoggedIn.setEmail(email);
			userLoggedIn.setPhone(phone);
		}
		//3. 뷰단
		String view = "/WEB-INF/views/common/popup.jsp";
		String msg = result > 0 ? "정보 수정 성공!" : "정보 수정 실패!";
		String loc = "/user/mypage?userId="+userId;
		
		request.setAttribute("msg", msg);
	    request.setAttribute("loc", loc);
		
		request.getRequestDispatcher(view).forward(request, response);
	}

}
