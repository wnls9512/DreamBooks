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
 * Servlet implementation class UpdatePasswordServlet
 */
@WebServlet("/mypage/updatePassword")
public class UpdatePasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UpdatePasswordServlet() {
        super();
     }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/customer/updatePassword.jsp")
			   .forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//1. 사용자 입력값 처리
		String userId = request.getParameter("userId");
		String password = Utils.getEncryptedPassword(request.getParameter("password"));
		String newPassword = Utils.getEncryptedPassword(request.getParameter("newPassword"));
		
//		System.out.println(userId);
//		System.out.println(password);
//		System.out.println(newPassword);
		
		//2. 업무로직
			//기존 비밀번호 검사
		Users u = new UserService().selectOne(userId);
		String msg = "";
		String loc="";
//		System.out.println("updatePwd@servlet="+u);
		
		if(u != null && password.equals(u.getPassword())) {
			//새 비밀번호로 변경
			int result = new UserService().updatePassword(userId, newPassword);
			msg = result > 0? "비밀번호 변경 성공!" : "비밀번호 변경 실패!";
			loc = "/user/mypage?userId="+userId;
			
			request.setAttribute("msg", msg);
			request.setAttribute("loc", loc);
			request.getRequestDispatcher("/WEB-INF/views/common/popup.jsp")
			.forward(request, response);
			
		}
		else {
			//비밀번호 재입력
			msg = "비밀번호를 잘못 입력하셨습니다.";
			loc = "/mypage/updatePassword?userId="+userId;
			//3. view단 처리
			request.setAttribute("msg", msg);
			request.setAttribute("loc", loc);
			request.getRequestDispatcher("/WEB-INF/views/common/popup.jsp").forward(request, response);
		}
		
		
		
	}

}
