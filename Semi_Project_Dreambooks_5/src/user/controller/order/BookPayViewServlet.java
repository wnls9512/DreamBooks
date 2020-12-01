package user.controller.order;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import book.model.service.BookService;
import book.model.vo.Book;
import user.model.service.UserService;
import user.model.vo.Users;

/**
 * Servlet implementation class PayViewServlet
 */
@WebServlet("/user/pay")
public class BookPayViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public BookPayViewServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String bookNo = request.getParameter("bookNo");
		String userId = request.getParameter("userId");

		Book b = new BookService().selectOne(bookNo);
		request.setAttribute("book", b);
		
		Users u = new UserService().selectOne(userId);
		
		request.setAttribute("user", u);
		request.getRequestDispatcher("/WEB-INF/views/customer/kakaopay.jsp").forward(request, response);
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
