package user.controller.mypage;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import book.model.service.BookService;
import book.model.vo.BookExtends;
import book.model.vo.BookOrdered;
import common.util.Utils;

/**
 * Servlet implementation class MypageViewServlet
 */
@WebServlet("/user/mypage")
public class MypageViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MypageViewServlet() {
        super();
     }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 사용자 입력값 받기
		String userId = request.getParameter("userId");
		
		Cookie[] cookies = request.getCookies(); 
		String bookCookieVal = "";  
		List<BookExtends> list = null;
		
		int numPerPage = 5;
		int cPage = 1;
		
		try {
		cPage = Integer.parseInt(request.getParameter("cPage"));
		} catch (NumberFormatException e) {
			
		}
			
		List<BookOrdered> list1 = new BookService().bookOrdered(userId,cPage,numPerPage);
//		System.out.println("list1 = " + list1);
		
		//페이지바
		int totalContents = new BookService().selectTotalBookOrderedContents(userId);
//		System.out.println("토탈 = " + totalContents);
		String url = request.getRequestURI() + "?userId="+ userId +"&";
		String pageBar = Utils.getPageBarHtml(cPage, numPerPage, totalContents, url);
				
		if(cookies != null ) { 
			for(Cookie c : cookies) { 
				String name = c.getName();
				String value = c.getValue();
				
				if("bookCookie".equals(name)) { 
					bookCookieVal = value;
//					System.out.println("cookie:" + bookCookieVal);
					list = new BookService().cookieBookInfo(bookCookieVal);
//					System.out.println("book="+b);
				}
			}
		}
		
		request.setAttribute("list", list);
		request.setAttribute("userId", userId);
		request.setAttribute("list1", list1);
		request.setAttribute("pageBar", pageBar);
		request.getRequestDispatcher("/WEB-INF/views/customer/mypageView.jsp")
				.forward(request, response);
	}

}
