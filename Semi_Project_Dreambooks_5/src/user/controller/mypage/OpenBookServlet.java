package user.controller.mypage;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import book.model.service.BookService;

/**
 * Servlet implementation class OpenBookServlet
 */
@WebServlet("/book/openBook")
public class OpenBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public OpenBookServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String bookTitle = request.getParameter("bookTitle");
		
		String fileName = new BookService().returnOriginalFile(bookTitle);
//		System.out.println(fileName);
		request.setAttribute("fileName", fileName);
		request.getRequestDispatcher("/WEB-INF/views/customer/BookFile.jsp").forward(request, response);
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
