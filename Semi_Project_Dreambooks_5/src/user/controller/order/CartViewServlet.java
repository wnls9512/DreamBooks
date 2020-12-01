package user.controller.order;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import user.model.service.UserService;
import user.model.vo.CartExtends;

/**
 * Servlet implementation class CartViewServlet
 */
@WebServlet("/user/cartView")
public class CartViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public CartViewServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId = request.getParameter("userId");
		
		List<CartExtends> list = new UserService().selectCartList(userId);
		
		request.setAttribute("list", list);
		request.getRequestDispatcher("/WEB-INF/views/customer/Cart.jsp").forward(request, response);
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
