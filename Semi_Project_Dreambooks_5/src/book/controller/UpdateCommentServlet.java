package book.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import book.model.service.BookService;

/**
 * Servlet implementation class UpdateCommentServlet
 */
@WebServlet("/book/bookCommentUpdate")
public class UpdateCommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
     public UpdateCommentServlet() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String bookNo = (String) request.getParameter("book_no");
		int commentNo = Integer.parseInt(request.getParameter("bookCommentNo"));
		String bookCommentContent = request.getParameter("bookCommentContent");
		
//		System.out.println(bookNo);
//		System.out.println(commentNo);
//		System.out.println(bookCommentContent);
		
		int result = new BookService().updateComment(commentNo, bookCommentContent);
		
		String msg = result > 0 ? "댓글 수정 성공" : "댓글 수정 실패" ;
		String loc = "/book/bookView?bookNo=" + bookNo;
		request.setAttribute("msg", msg);
		request.setAttribute("loc", loc);
		
		request.getRequestDispatcher("/WEB-INF/views/common/popup.jsp")
			   .forward(request, response);
	}

}
