package user.controller.mypage;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import user.model.service.UserService;

/**
 * Servlet implementation class UserDeleteServlet
 */
@WebServlet("/user/deleteuser")
public class UserDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UserDeleteServlet() {
        super();
     }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 아이디 가져오기
		String userId = request.getParameter("userId");
//		System.out.println("userId@servlet = " + userId);
		String userLoggedInRole = request.getParameter("userLoggedInRole");
//		System.out.println(userLoggedInRole);
		
		//2. 업무 응답
		int result = new UserService().deleteUser(userId);
		
		//3. 뷰 처리
		String msg = "성공적으로 탈퇴되었습니다.";
		String loc = "";
		
		
		if(userLoggedInRole.equals("A")) {
			loc = "/admin/userListView";
		} else {
			loc = "/";
		}

		request.setAttribute("msg", msg);
		request.setAttribute("loc", loc);			

		request.getRequestDispatcher("/WEB-INF/views/common/popup.jsp").forward(request, response);
	}

}
