package book.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import book.model.service.BookService;
import book.model.vo.Book;
import book.model.vo.BookExtends;
import book.model.vo.Comments;
import book.model.vo.Tag;

/**
 * Servlet implementation class BookViewServlet
 */
@WebServlet("/book/bookView")
public class BookViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public BookViewServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			String bookNo = request.getParameter("bookNo");
			
			BookExtends book = new BookService().selectBookInfoOne(bookNo);
			List<BookExtends> bookList = new BookService().selectBoardList(book.getCategoryName());
			List<Book> rankBookList = new BookService().selectRankBoardList();
			List<Comments> commentList = new BookService().selectCommentList(bookNo);
			List<Tag> tagList = new BookService().selectTagList(bookNo);
			String category_top = new BookService().selectCategoryTop(book.getCategoryName());

			
			//쿠키 가져오기
			Cookie[] cookies = request.getCookies(); 
			String bookCookieVal = ""; //쿠키 value 보관용
			boolean hasRead = false; //읽었니 안읽었니?
			
			if(cookies != null) { //nullpointException 방지
				for(Cookie c : cookies) {
					//쿠키 돌아보다가
					String name = c.getName();
					String value = c.getValue();
					
					//bookCookie를 찾으면!
					if("bookCookie".equals(name)) {
						bookCookieVal = value;
						
//						//이번 게시글을 이미 읽었다면! true로 바꾸기
//						if(value.contains(bookNo))
//							hasRead = true;
					}
				}
			}
			
			//처음 읽었을 때, hasRead가 false일때
			//쿠키 새로 만들기
			if(!hasRead) {
				//bookCookie 생성
				Cookie bookCookie = new Cookie("bookCookie", bookNo + "/" + bookCookieVal); 
																//누적//덮어쓰기않게
				
				//쿠키 적용할 경로
				bookCookie.setPath(request.getContextPath());
				
				//브라우져가 종료되면 쿠키 삭제
				bookCookie.setMaxAge(-1);
//				bookCookie.setMaxAge(60*60*24);
				
				response.addCookie(bookCookie);			
			}		
			
//		System.out.println(rankBookList);
			request.setAttribute("list", bookList);
			request.setAttribute("rankList", rankBookList);
			request.setAttribute("commentList", commentList);
			request.setAttribute("tagList", tagList);
			request.setAttribute("book", book);
			request.setAttribute("category_top", category_top);
			request.getRequestDispatcher("/WEB-INF/views/customer/BookInfoView.jsp")
			.forward(request, response);
			
		}catch(Exception e) {
			e.printStackTrace();
			throw e; //was(웹 컨테이너)에게 오류 던지기
		}
		
	}

}
