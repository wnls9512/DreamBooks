package user.controller.order;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import book.model.service.BookService;
import book.model.vo.Book;
import user.model.service.UserService;
import user.model.vo.Users;

/**
 * Servlet implementation class CartPayViewServlet
 */
@WebServlet("/user/cartpay")
public class CartPayViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CartPayViewServlet() {
        super();
     }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//사용자 입력값
		String[] bookNo2 =request.getParameterValues("bookNo");
		String userId = request.getParameter("userId");
		int sum = Integer.parseInt(request.getParameter("sum"));
		
		//배열 지정
		Book[] b = new Book[bookNo2.length];
		
		for(int i=0;i<bookNo2.length;i++) {
			b[i] = new BookService().selectOne(bookNo2[i]);
		}
			
		Users u = new UserService().selectOne(userId);
		
		request.setAttribute("sum", sum);
		request.setAttribute("book", b);
		request.setAttribute("size", bookNo2.length);
		request.setAttribute("user", u);		
		request.getRequestDispatcher("/WEB-INF/views/customer/kakaopay3.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
