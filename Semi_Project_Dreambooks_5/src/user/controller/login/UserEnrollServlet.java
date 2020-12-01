package user.controller.login;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.util.Utils;
import user.model.service.UserService;
import user.model.vo.Users;

/**
 * Servlet implementation class UserEnrollServlet
 */
@WebServlet("/user/userEnroll")
public class UserEnrollServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
     public UserEnrollServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher reqDispatcher
		= request.getRequestDispatcher("/index.jsp");
		reqDispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String userId = request.getParameter("enroll_id");
		String password = Utils.getEncryptedPassword(request.getParameter("enroll_password"));
		String userName = request.getParameter("enroll_name");
		String email = request.getParameter("enroll_email");
		String phone = request.getParameter("enroll_phone");
		
		Users newUser = new Users(userId,password,userName,UserService.USER_ROLE_USER,email,phone,null);
		
//		System.out.println("newUser="+newUser);
		
		int result = new UserService().insertUser(newUser);
//		System.out.println("result@userEnrollServlet="+result);
		
		String msg = result > 0 ? "회원 가입 성공!" : "회원 가입 실패!";
		String loc = "/";
		
		request.setAttribute("msg", msg);
		request.setAttribute("loc", loc);
		
		request.getRequestDispatcher("/WEB-INF/views/common/popup.jsp")
			   .forward(request, response);
	}

}
