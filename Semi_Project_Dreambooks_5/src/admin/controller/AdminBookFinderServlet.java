package admin.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import book.model.service.BookService;
import book.model.vo.BookExtends;
import common.util.Utils;
import user.model.service.UserService;

/**
 * Servlet implementation class AdminBookFinderServlet
 */
@WebServlet("/admin/bookFinder")
public class AdminBookFinderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public AdminBookFinderServlet() {
        super();
     }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 사용자 입력값 처리
		String searchType = request.getParameter("searchType");
		String searchKeyword = request.getParameter("searchKeyword");
		
		int numPerPage = 10;
		int cPage = 1;
		
		try {
		cPage = Integer.parseInt(request.getParameter("cPage"));
		} catch (NumberFormatException e) {
			
		}
		//2. 업무로직
		List<BookExtends> list = null;
		list = new BookService().searchBook(searchType, searchKeyword, cPage, numPerPage);
//		System.out.println("list리스트 = " + list);

		//페이지바
		int totalContents = new BookService().selectTotalBookContents(searchType, searchKeyword);
		String url = request.getRequestURI() + "?searchType="+ searchType + "&searchKeyword="+ searchKeyword+ "&";
		String pageBar = Utils.getPageBarHtml(cPage, numPerPage, totalContents, url);
		
		//3. 뷰단 처리
		String loc = "/WEB-INF/views/admin/AllBookListView.jsp";
		
		request.setAttribute("loc", loc);
		request.setAttribute("list", list);
		request.setAttribute("pageBar", pageBar);
		request.getRequestDispatcher(loc).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
