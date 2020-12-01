package user.controller.order;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import user.model.service.UserService;
import user.model.vo.Cart;

/**
 * Servlet implementation class CartInsertServlet
 */
@WebServlet("/user/cartInsert")
public class CartInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public CartInsertServlet() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String book_no = request.getParameter("book_no");
		String user_id = request.getParameter("user_id");
		String price = request.getParameter("price");
		
		Cart c = new UserService().checkCart(book_no,user_id);
		
		if(c!=null) {
			String msg="이미 담겨진 책입니다.";
			request.setAttribute("msg", msg);
		}else {
			int result = new UserService().insertCart(book_no,user_id,price);
			String msg = result>0 ? "카트에 담겼습니다.":"카트 등록 실패";
			request.setAttribute("msg", msg);
		}
		
		String loc = "/book/bookView?bookNo=" + book_no;
		request.setAttribute("loc", loc);
		
		request.getRequestDispatcher("/WEB-INF/views/common/popup.jsp")
			   .forward(request, response);
		
	}

}
