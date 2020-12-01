package admin.controller;

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
 * Servlet implementation class AdminUpdateUserServlet
 */
@WebServlet("/admin/updateUser")
public class AdminUpdateUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
     public AdminUpdateUserServlet() {
        super();
      }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String userId = request.getParameter("userId");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String userRole = request.getParameter("userRole");
		
		Users user = new Users(userId, null, null, userRole, email, phone, null);
//		System.out.println("updateUser@servlet = "+user);
		
		int result = new UserService().adminUpdateUser(user);
//		System.out.println("updaetUserResult@servlet = "+result);
		
		HttpSession session = request.getSession();
		Users userLoggedIn = (Users)session.getAttribute("userLoggedIn");
		
		if(result > 0 && userId.equals(userLoggedIn.getUserId())) {
			userLoggedIn.setEmail(email);
			userLoggedIn.setPhone(phone);
			userLoggedIn.setUserRole(userRole);
		}
		
		String view = "/WEB-INF/views/common/popup.jsp";
		String msg = result > 0 ? "회원 정보 수정 성공^0^!" : "회원 정보 수정 실패ㅠㅠ";
		String loc = "/admin/userInfo?userId="+userId;
		
		request.setAttribute("msg", msg);
		request.setAttribute("loc", loc);
		request.getRequestDispatcher(view)
				.forward(request, response);
		
	}

}
