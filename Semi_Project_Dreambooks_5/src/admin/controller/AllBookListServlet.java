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

/**
 * Servlet implementation class AllBookListServlet
 */
@WebServlet("/admin/allBookList")
public class AllBookListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AllBookListServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int numPerPage = 10;
		int cPage = 1;
		
		try {
		cPage = Integer.parseInt(request.getParameter("cPage"));
		} catch (NumberFormatException e) {
			
		}
		
		// 1. 업무로직 : 도서목록
		// 컨텐츠 영역에 대한 쿼리 요청
		List<BookExtends> list = new BookService().selectAllBookList(cPage, numPerPage);
//		System.out.println("list@servlet = "+list);

		// 페이지 바
		String url = request.getRequestURI() + "?";
		int totalContents = new BookService().selectTotalAllBookList();
		String pageBar = Utils.getPageBarHtml(cPage, numPerPage, totalContents, url);
				
		request.setAttribute("list", list);
		request.setAttribute("pageBar", pageBar);		
		
		request.getRequestDispatcher("/WEB-INF/views/admin/AllBookListView.jsp")
				.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
