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
 * Servlet implementation class AdminUserFinderServlet
 */
@WebServlet("/admin/userFinder")
public class AdminUserFinderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AdminUserFinderServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 사용자 입력값 처리
		String searchType = request.getParameter("searchType");
		String searchKeyword = request.getParameter("searchKeyword");
//		System.out.println("searchType = " + searchType);
//		System.out.println("searchKeyword = " + searchKeyword);
		
		int numPerPage = 10;
		int cPage = 1;
		try {
			cPage = Integer.parseInt(request.getParameter("cPage"));
		} catch(NumberFormatException e) {
			//예외가 던져진 경우, cPage = 1 로 유지
		}
		//2. 업무로직
		List<Users> list = null;
		String loc = "/";
		if(searchKeyword.equals("T")) {
			list = new UserService().selectAllUserList(cPage, numPerPage);
			System.out.println("리스트 서블릿 1 = " + list);
			//3. 뷰단 처리
			loc = "/WEB-INF/views/admin/AllUserListView.jsp";
		}
		else {
			list = new UserService().searchUser(searchType, searchKeyword, cPage, numPerPage);			
//			System.out.println("리스트 서블릿 2 = "+ list);
			//3. 뷰단 처리
			loc = "/WEB-INF/views/admin/AllUserListView.jsp";
		}
		
		//pageBar영역
		int totalContents = new UserService().selectTotalContents(searchType, searchKeyword);
		String url = request.getRequestURI() + "?searchType="+ searchType + "&searchKeyword="+ searchKeyword+ "&";
//		System.out.println("토탈컨텥츠 = " + totalContents);
		String pageBar = Utils.getPageBarHtml(cPage, numPerPage, totalContents, url);
		
		//전체 veiw처리
		request.setAttribute("loc", loc);
		request.setAttribute("list", list);
		request.setAttribute("pageBar", pageBar);
		request.getRequestDispatcher(loc).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
