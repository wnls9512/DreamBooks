package admin.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import book.model.service.BookService;

/**
 * Servlet implementation class PublisherDeleteBookServlet
 */
@WebServlet("/book/deleteBook")
public class DeleteBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public DeleteBookServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String bookNo = request.getParameter("bookNo");
		String userLoggedInRole = request.getParameter("userLoggedInRole");
		String userId = request.getParameter("userLoggedInId");
		
//		System.out.println("deleteBookNo@servlet="+bookNo);
		
		int result = new BookService().deleteBook(bookNo);
//		System.out.println("Result@servlet="+result);

		String view = "/WEB-INF/views/common/popup.jsp";
		String msg = result > 0 ? "도서 삭제 성공!" : "도서 삭제 실패ㅠㅠ";
		String loc = userLoggedInRole.equals("A")? "/admin/allBookList" : "/publisher/bookListView?userId="+userId;
		
		request.setAttribute("msg", msg);
		request.setAttribute("loc", loc);
		request.getRequestDispatcher(view)
				.forward(request, response);
		
	
	}

}
