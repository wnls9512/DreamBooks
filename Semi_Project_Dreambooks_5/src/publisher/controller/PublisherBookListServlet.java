package publisher.controller;

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
 * Servlet implementation class PublisherBookListServlet
 */
@WebServlet("/publisher/bookListView")
public class PublisherBookListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public PublisherBookListServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//0. 사용자 입력값
		
		int numPerPage = 10;
		int cPage = 1;
		String userId = request.getParameter("userId");
//		System.out.println("pubId@servlet="+userId);

		
		try {
			cPage = Integer.parseInt(request.getParameter("cPage"));
		} catch (NumberFormatException e) {
			
		}
		
		// 1. 업무로직 : 도서목록
		// 컨텐츠 영역에 대한 쿼리 요청
		List<BookExtends> list = new BookService().selectPubBook(cPage, numPerPage, userId);
//		System.out.println("list@servlet = "+list);
		
		//페이지 바 영역 html
		String url = request.getRequestURI() + "?userId="+userId+"&" ;
		int totalContents = new BookService().selectTotalPublisherBook(userId);
		String pageBar = Utils.getPageBarHtml(cPage, numPerPage, totalContents, url);
		
		request.setAttribute("list", list);
		request.setAttribute("userId", userId);
		request.setAttribute("cPage", cPage);
		request.setAttribute("pageBar", pageBar);
		
		request.getRequestDispatcher("/WEB-INF/views/publisher/PublisherBookListView.jsp")
		   .forward(request, response);
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
