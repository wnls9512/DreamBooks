package admin.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import book.model.service.BookService;

/**
 * Servlet implementation class AdminDisplayBookChangeServlet
 */
@WebServlet("/admin/displayBookUpdate")
public class AdminDisplayBookUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AdminDisplayBookUpdateServlet() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String bookno = request.getParameter("bookno");
		String display_yn = request.getParameter("display_yn");
		
//		System.out.println("displayChgBookNo@servlet=" + bookno);
//		System.out.println("displayChgDisplay@servlet=" + display_yn);

		int result = new BookService().updateBookDisplay(bookno, display_yn);
		
//		System.out.println("displayUpdateResult@servlet=" + result);
		
		String view ="/WEB-INF/views/common/popup.jsp";
		String msg = result > 0 ? "수정하였습니다 ^0^" : "수정에 실패했습니다 ㅠㅠ";
		String loc = "/admin/allBookList";
		
		request.setAttribute("msg", msg);
		request.setAttribute("loc", loc);
		
		request.getRequestDispatcher(view).forward(request, response);
		
	}

}
