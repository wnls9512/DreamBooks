package book.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import book.model.service.BookService;
import book.model.vo.Comments;

/**
 * Servlet implementation class InsertCommentServlet
 */
@WebServlet("/book/insertcomment")
public class InsertCommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public InsertCommentServlet() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String commentContent = (String) request.getParameter("bookComment");
		String bookNo = (String) request.getParameter("bookno");
		String userId = (String) request.getParameter("commentWriter");
//		System.out.println(commentContent + bookNo + userId);
		
		String[] str = {"바보","멍청이","똥개"};
		int j=0;
		for(int i=0;i<str.length;i++) {
		if(commentContent.indexOf(str[i])!=-1) {
				j=1;
			}
			
		}
		if(j==1) {
			String msg="바른말을 사용해주세요.";
			request.setAttribute("msg", msg);
		}else {
		Comments c = new Comments(0, commentContent, null, bookNo, userId);	

		int result = new BookService().insertComment(c);
		
		String msg = result > 0 ? "댓글 등록 성공" : "댓글 등록 실패" ;
		request.setAttribute("msg", msg);
		}
		String loc = "/book/bookView?bookNo=" + bookNo;
		request.setAttribute("loc", loc);
		
		request.getRequestDispatcher("/WEB-INF/views/common/popup.jsp")
			   .forward(request, response);
	
	}


}
