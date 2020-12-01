package user.controller.order;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import user.model.service.UserService;

/**
 * Servlet implementation class CartDeleteServlet
 */
@WebServlet("/user/cartDelete")
public class CartDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public CartDeleteServlet() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String book_no = request.getParameter("book_no");
		String user_id = request.getParameter("user_id");
		
		int result = new UserService().deleteCart(book_no,user_id);
		
		String msg = result>0 ? "삭제되었습니다":"삭제실패하셨습니다.";
		String loc = "/user/cartView?userId=" + user_id;
		request.setAttribute("msg", msg);
		request.setAttribute("loc", loc);
		
		request.getRequestDispatcher("/WEB-INF/views/common/popup.jsp")
			   .forward(request, response);
	}

}
