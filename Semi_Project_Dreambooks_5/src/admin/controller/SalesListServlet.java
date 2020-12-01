package admin.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import book.model.service.BookService;
import user.model.vo.SaledBook;

/**
 * Servlet implementation class SalesListServlet
 */
@WebServlet("/admin/SalesListView")
public class SalesListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public SalesListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<SaledBook> list = new BookService().saledBook();
//		System.out.println("리스트닷 = " + list);
		
		request.setAttribute("list", list);
		request.getRequestDispatcher("/WEB-INF/views/admin/AllSalesListView.jsp")
			   .forward(request, response);
	}

}
