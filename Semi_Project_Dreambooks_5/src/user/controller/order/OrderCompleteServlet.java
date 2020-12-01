package user.controller.order;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import user.model.service.UserService;

/**
 * Servlet implementation class OrderCompleteServlet
 */
@WebServlet("/user/orderComplete")
public class OrderCompleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public OrderCompleteServlet() {
        super();
     }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId = request.getParameter("userId");
		String[] bookNo = request.getParameterValues("bookNo");
		int listSize =bookNo.length;
		
//		System.out.println("orderComplete : userId"+userId);
		
		int result =0;
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		String orderNum = sdf.format(new Date());
		
		result =new UserService().InsertOrder(orderNum, listSize, bookNo, userId);
		
		request.setAttribute("userId", userId);
		request.setAttribute("bookNo", bookNo);		
		request.getRequestDispatcher("/WEB-INF/views/customer/OrderComplete.jsp").forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
