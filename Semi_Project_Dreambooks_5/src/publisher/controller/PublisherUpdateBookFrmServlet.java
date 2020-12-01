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
 * Servlet implementation class PublisherUpdateBookServlet
 */
@WebServlet("/book/updateBookFrm")
public class PublisherUpdateBookFrmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public PublisherUpdateBookFrmServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String bookNo = request.getParameter("bookNo");
		
		Book book = new BookService().selectOne(bookNo);
		
		request.setAttribute("book", book);
		
		request.getRequestDispatcher("/WEB-INF/views/publisher/BookUpdateView.jsp")
				.forward(request, response);
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
