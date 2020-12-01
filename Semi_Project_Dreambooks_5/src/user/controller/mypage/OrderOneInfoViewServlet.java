package user.controller.mypage;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import user.model.service.UserService;
import user.model.vo.OrderBook;

/**
 * Servlet implementation class OrderOneInfoViewServlet
 */
@WebServlet("/mypage/orderInfo")
public class OrderOneInfoViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public OrderOneInfoViewServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String total = request.getParameter("total");
		String orderNo = request.getParameter("orderNo");
		
		List<OrderBook> ob = new UserService().selectOrderInfo(orderNo);
		int result = new UserService().deleteOkDday(ob.get(0).getOrderDate());
		String cancelYN = new UserService().cancelYN(orderNo);
		
		request.setAttribute("total", total);
		request.setAttribute("cancelYN", cancelYN);
		request.setAttribute("result", result);
		request.setAttribute("list", ob);
		request.getRequestDispatcher("/WEB-INF/views/customer/customerOrderOne.jsp")
				.forward(request, response);
		
	}


}
