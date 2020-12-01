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

/**
 * Servlet implementation class SearchBookListServlet
 */
@WebServlet("/book/search")
public class SearchBookListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public SearchBookListServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String searchKeyword = request.getParameter("searchKeyword");
//		System.out.println("searchkeyword@servlet = " + searchKeyword);
//		System.out.println(searchKeyword.substring(0, 1));
		
		List<BookExtends> list =null;
		if(searchKeyword.charAt(0)=='#') {
			searchKeyword = searchKeyword.substring(1);
			searchKeyword = searchKeyword.replaceAll(" ", "");
			list =new BookService().searchTag(searchKeyword);
		}else {
			list = new BookService().searchBook(searchKeyword);
//			System.out.println(list.size());
//			System.out.println("list"+list);
			
		}
		
		if(list.size()!=0) {
		String category = list.get(0).getCategoryName();
		List<BookExtends> list2 = new BookService().selectBoardList(category);
//		System.out.println("list2"+list2);
		request.setAttribute("list2",list2);
		}
		
		String url 
		= request.getRequestURI() 
		+ "?searchKeyword=" + searchKeyword 
		+ "&";
		
		request.setAttribute("list", list);
		request.getRequestDispatcher("/WEB-INF/views/customer/SearchBookList.jsp").forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
