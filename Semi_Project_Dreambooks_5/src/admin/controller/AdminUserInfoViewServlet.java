package admin.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import user.model.service.UserService;
import user.model.vo.Users;

/**
 * Servlet implementation class AdminUserInfoViewServlet
 */
@WebServlet("/admin/userInfo")
public class AdminUserInfoViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
     public AdminUserInfoViewServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String userId = request.getParameter("userId");
		
		Users user = new UserService().selectOne(userId);
		
		request.setAttribute("user", user);
		
		request.getRequestDispatcher("/WEB-INF/views/admin/AdminUserInfoView.jsp")
				.forward(request, response);
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
