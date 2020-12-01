package book.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import book.model.service.BookService;
import book.model.vo.BookExtends;
import common.util.Utils;

/**
 * Servlet implementation class CategoryBookFinderServlet
 */
@WebServlet("/category/bookFinder")
public class CategoryBookFinderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
      public CategoryBookFinderServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 사용자 입력값
		String category = request.getParameter("category");
		String order = request.getParameter("order");
//		System.out.println(category);
//		System.out.println(order); //selling/ name
		
		int numPerPage = 5;
		int cPage = 1;
		
		try {
			cPage = Integer.parseInt(request.getParameter("cPage"));
		}catch(NumberFormatException e) {
			
		}
		
		String loc = "/";
		//2. 업무로직
		List<BookExtends> bookList = new BookService().searchCategoryBook(cPage, numPerPage, order, category);
//		System.out.println("bookList = " + bookList);
		
		String url = request.getRequestURI() + "?category=" + category + "&order=" +  order + "&" ;
		int totalContents = new BookService().selectTotalContents(category);
		String pageBar = Utils.getPageBarHtml(cPage, numPerPage, totalContents, url);

		loc = "/WEB-INF/views/customer/CategoryBookList.jsp";
		
		//3. 뷰단
		request.setAttribute("category", category);
		request.setAttribute("loc", loc);
		request.setAttribute("order", order);
		request.setAttribute("list", bookList);
		request.setAttribute("cPage", cPage);
		request.setAttribute("pageBar", pageBar);
		request.getRequestDispatcher(loc).forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
