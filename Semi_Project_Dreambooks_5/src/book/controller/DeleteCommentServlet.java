package book.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import book.model.service.BookService;

/**
 * Servlet implementation class DeleteCommentServlet
 */
@WebServlet("/book/bookCommentDelete")
public class DeleteCommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DeleteCommentServlet() {
        super();
     }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int bookCommentNo = Integer.parseInt(request.getParameter("bookCommentNo"));
		String bookNo = request.getParameter("bookNo");
//		System.out.println(bookCommentNo);
		
		int result = new BookService().deleteBookComment(bookCommentNo);
		
		String msg = result > 0 ? "댓글 삭제 성공" : "댓글 삭제 실패";
		String loc = "/book/bookView?bookNo=" + bookNo;
				
		request.setAttribute("msg", msg);
		request.setAttribute("loc", loc);
		
		request.getRequestDispatcher("/WEB-INF/views/common/popup.jsp")
				.forward(request, response);
	}

}
