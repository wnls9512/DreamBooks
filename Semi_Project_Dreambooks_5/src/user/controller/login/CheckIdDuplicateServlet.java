package user.controller.login;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import user.model.service.UserService;
import user.model.vo.Users;

/**
 * Servlet implementation class CheckIdDuplicateServlet
 */
@WebServlet("/user/checkIdDuplicate")
public class CheckIdDuplicateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CheckIdDuplicateServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 인코딩시작
		request.setCharacterEncoding("utf-8");
		
		//2. 사용자입력값처리 
		String enroll_id = request.getParameter("enroll_id");
//		System.out.println("userId@servlet="+enroll_id);
		
		//3. 업무로직 요청
		Users u = new UserService().selectOne(enroll_id);
		boolean isIdUsable = u == null ? true : false;
		
		request.setAttribute("isIdUsable", isIdUsable);
	
		//4. 사용자응답처리
		request.getRequestDispatcher("/WEB-INF/views/customer/checkIdDuplicate.jsp")
			   .forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
