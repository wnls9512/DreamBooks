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
 * Servlet implementation class PublisherBookSearchServlet
 */
@WebServlet("/book/bookSearchPub")
public class PublisherBookSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public PublisherBookSearchServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int numPerPage = 10;
		int cPage = 1;
		
		try {
			cPage = Integer.parseInt(request.getParameter("cPage"));
		} catch (NumberFormatException e) {
			
		}
		
		String userId = request.getParameter("userId");
//		System.out.println(userId);
		String option = request.getParameter("select-search");
//		System.out.println(option);
		String keyword = request.getParameter("search-text");
//		System.out.println(keyword);
		
		List<BookExtends> list = new BookService().selectPubBook(cPage, numPerPage, userId, option, keyword);
//		System.out.println(list);
		
		String url = request.getRequestURI() + "&";
		int totalContents = new BookService().selectTotalPublisherBook(userId, option, keyword);
		String pageBar = Utils.getPageBarHtml(cPage, numPerPage, totalContents, url);

		request.setAttribute("list", list);
		request.setAttribute("option", option);
		request.setAttribute("total", totalContents);
		request.setAttribute("userId", userId);
		request.setAttribute("cPage", cPage);
		request.setAttribute("pageBar", pageBar);
		
		request.getRequestDispatcher("/WEB-INF/views/publisher/PublisherBookListView.jsp")
				.forward(request, response);
		
		
	}

}
