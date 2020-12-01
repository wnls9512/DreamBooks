package book.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import book.model.service.BookService;
import book.model.vo.Comments;
import book.model.vo.Tag;
import user.model.service.UserService;
import user.model.vo.Users;

/**
 * Servlet implementation class InsertTagServlet
 */
@WebServlet("/book/insertTag")
public class InsertTagServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public InsertTagServlet() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String tagName_ = (String) request.getParameter("tagname");
		String bookNo = (String) request.getParameter("tagbookno");
		String userId = (String) request.getParameter("tagWriter");
		
		String tagName =tagName_.replaceAll(" ", "");
		int j=0;
		String[] str = {"바보","멍청이"};
		for(int i=0;i<str.length;i++) {
		if(tagName.indexOf(str[i])!=-1) {
				j=1;
				break;
			}
		
		}
		if(j==1) {
			String msg="바른말을 사용해주세요";
			request.setAttribute("msg", msg);
		}else {
		Tag t  = new BookService().checkTag(tagName, bookNo);
		if(t!=null) {
			String msg = "중복된 태그 입니다.";
			request.setAttribute("msg", msg);
		}else {
			
			Tag tag = new Tag(null, tagName, bookNo, userId);
			int result = new BookService().insertTag(tag);
			
			String msg = result > 0 ? "태그 등록 성공" : "태그 등록 실패" ;
			request.setAttribute("msg", msg);
			}
		}

		String loc = "/book/bookView?bookNo=" + bookNo;
		request.setAttribute("loc", loc);
		
		request.getRequestDispatcher("/WEB-INF/views/common/popup.jsp")
			   .forward(request, response);
	}

}
