package user.controller.mypage;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import user.model.service.UserService;
import user.model.vo.OrderBook;

/**
 * Servlet implementation class OrderCancelServlet
 */
@WebServlet("/order/cancelOrder")
public class OrderCancelServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public OrderCancelServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String orderNo = request.getParameter("orderNo");
		String total = request.getParameter("total");
		String userId = request.getParameter("userId");
		
		List<OrderBook> ob = new UserService().selectOrderInfo(orderNo);
		int result = new UserService().cancelOrder(orderNo);
		
		String view = "/WEB-INF/views/common/popup.jsp";
		String msg = result > 0? "주문 취소되었습니다." : "주문 취소 실패 다시 시도해주세요.";
		String loc =  "/mypage/orderList?userId=" + userId;

		request.setAttribute("msg", msg);
		request.setAttribute("loc", loc);
		request.getRequestDispatcher(view).forward(request, response);
	
	}

}
