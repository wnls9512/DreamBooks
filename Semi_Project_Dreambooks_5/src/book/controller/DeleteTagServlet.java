package book.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import book.model.service.BookService;

/**
 * Servlet implementation class DeleteTagServlet
 */
@WebServlet("/book/deleteTag")
public class DeleteTagServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DeleteTagServlet() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String tagCode = request.getParameter("tagCode");
		String bookNo = request.getParameter("book_no");
		
		int result = new BookService().deleteTag(tagCode);
		
		String msg = result > 0 ? "태그 삭제 성공" : "태그 삭제 실패";
		String loc = "/book/bookView?bookNo=" + bookNo;
				
		request.setAttribute("msg", msg);
		request.setAttribute("loc", loc);
		
		request.getRequestDispatcher("/WEB-INF/views/common/popup.jsp")
				.forward(request, response);
		
	}
}
