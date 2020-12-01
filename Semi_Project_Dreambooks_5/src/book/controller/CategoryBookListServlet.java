package book.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import book.model.service.BookService;
import book.model.vo.Book;
import book.model.vo.BookExtends;
import common.util.Utils;

/**
 * Servlet implementation class CategoryEssayListServlet
 */
@WebServlet("/book/category")
public class CategoryBookListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CategoryBookListServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int numPerPage = 5;
		int cPage = 1;
		
		try {
			cPage = Integer.parseInt(request.getParameter("cPage"));
		}catch(NumberFormatException e) {
			
		}
		
		String category = request.getParameter("category");
//		System.out.println(category);
		
		List<BookExtends> bookList = new BookService().selectBookList(cPage, numPerPage, category);
		
		String url = request.getRequestURI() + "?category=" + category + "&";
		int totalContents = new BookService().selectTotalContents(category);
		String pageBar = Utils.getPageBarHtml(cPage, numPerPage, totalContents, url);
	
		request.setAttribute("category", category);
		request.setAttribute("cPage", cPage);
		request.setAttribute("list", bookList);
		request.setAttribute("pageBar", pageBar);
		request.getRequestDispatcher("/WEB-INF/views/customer/CategoryBookList.jsp")
				.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
