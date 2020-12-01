package user.controller.mypage;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import user.model.service.UserService;
import user.model.vo.OrdersExtends;

/**
 * Servlet implementation class OrderListViewServlet
 */
@WebServlet("/mypage/orderList")
public class OrderListViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;      

    public OrderListViewServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String userId = request.getParameter("userId");
//		System.out.println(userId);
		
		List<OrdersExtends> list = new UserService().selectOrderList(userId);
		
//		System.out.println(list);
		
		request.setAttribute("list", list);
		request.getRequestDispatcher("/WEB-INF/views/customer/MyOrderListView.jsp")
		   	   .forward(request, response);
	}

}
