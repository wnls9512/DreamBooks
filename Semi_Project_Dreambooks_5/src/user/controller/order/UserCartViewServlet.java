package user.controller.order;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UserCardViewServlet
 */
@WebServlet("/user/cart")
public class UserCartViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UserCartViewServlet() {
        super();
     }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		request.getRequestDispatcher("/WEB-INF/views/customer/Cart.jsp")
			   .forward(request, response);
	}


}
