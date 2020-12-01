package publisher.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import book.model.service.BookService;
import book.model.vo.Book;

/**
 * Servlet implementation class PublisherBookInfoViewServlet
 */
@WebServlet("/book/bookInfoView")
public class PublisherBookInfoViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public PublisherBookInfoViewServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String bookNo = request.getParameter("bookNo");
//		System.out.println("bookNo@servlet="+bookNo);
		
		Book b = new BookService().selectOne(bookNo);
//		System.out.println("book@servlet="+b);
		
		request.setAttribute("book", b);
		request.setAttribute("bookNo", bookNo);
		
		request.getRequestDispatcher("/WEB-INF/views/publisher/BookInfoView.jsp")
				.forward(request, response);	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
