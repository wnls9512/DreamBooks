package user.controller.mypage;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.util.Utils;
import user.model.service.UserService;
import user.model.vo.Users;

/**
 * Servlet implementation class UserCheckServlet
 */
@WebServlet("/mypage/usercheck")
public class UserCheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UserCheckServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.getRequestDispatcher("/WEB-INF/views/customer/updateCustomerChk.jsp")
			   .forward(request, response);
	
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//1. 사용자 입력값 처리
		String userId = request.getParameter("userId");
		String password = Utils.getEncryptedPassword(request.getParameter("password"));

		//2. 업무로직
		Users u = new UserService().selectOne(userId);
		
		//3. view단 처리 중
		String msg = "";
		String loc = "/";
		
		if(password.equals(u.getPassword())) {
			msg =  "비밀번호 일치합니다!!";
			loc =  "/user/userupdate?userId="+userId;

		}
		else {
			//msg.jsp
			msg = "비밀번호가 일치하지 않습니다";
			loc = "/mypage/usercheck";			
		}
			
		request.setAttribute("msg", msg);
		request.setAttribute("loc", loc);
		request.getRequestDispatcher("/WEB-INF/views/common/popup.jsp")
		.forward(request, response);			
	}

}