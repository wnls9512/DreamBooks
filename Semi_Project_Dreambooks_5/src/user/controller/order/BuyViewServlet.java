package user.controller.order;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import book.model.service.BookService;
import book.model.vo.Book;
import user.model.service.UserService;
import user.model.vo.CartExtends;

/**
 * Servlet implementation class BuyViewServlet
 */
@WebServlet("/user/BuyView")
public class BuyViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public BuyViewServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String[] check = request.getParameterValues("check");
		String bookNo = request.getParameter("book_no");
	
		if(check!=null) {
			List<CartExtends> list = new UserService().buyCartList(check);
			int sum=0;
		
			for(CartExtends c : list) {
				sum += Integer.parseInt(c.getCartTotalPrice());
			}
			
//			System.out.println(sum);
			request.setAttribute("list", list);
			request.setAttribute("sum", sum);
//			System.out.println(list);
			request.getRequestDispatcher("/WEB-INF/views/customer/CartOrder.jsp").forward(request, response);
		
		}else {
			Book book = new BookService().selectOne(bookNo);
			request.setAttribute("book", book);
			request.getRequestDispatcher("/WEB-INF/views/customer/BookOrder.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}