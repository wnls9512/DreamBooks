package admin.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.util.Utils;
import user.model.service.UserService;
import user.model.vo.Users;

/**
 * Servlet implementation class UserListServlet
 */
@WebServlet("/admin/userListView")
public class UserListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public UserListServlet() {
        super();
     }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int numPerPage = 10;
		int cPage = 1;
		try {
			cPage = Integer.parseInt(request.getParameter("cPage"));
		}catch(NumberFormatException e) {
			//예외가 던져진 경우, cPage = 1로 유지
		}
		List<Users> list = new UserService().selectAllUserList(cPage,numPerPage);
		
		//페이지바 영역 html
		String url = request.getRequestURI()+"?";
		int totalContents = new UserService().selectTotalContents();
		System.out.println("totalContents = " + totalContents);
		String pageBar = Utils.getPageBarHtml(cPage, numPerPage, totalContents, url);
		
		// 뷰단 위임
		request.setAttribute("list", list);
		request.setAttribute("pageBar", pageBar);
		request.getRequestDispatcher("/WEB-INF/views/admin/AllUserListView.jsp")
				.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
