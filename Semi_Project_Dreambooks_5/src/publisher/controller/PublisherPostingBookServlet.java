package publisher.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.FileRenamePolicy;

import book.model.service.BookService;
import book.model.vo.Book;
import common.MvcFileRenamePolicy;
import user.model.exception.BookException;

/**
 * Servlet implementation class PublisherPostingBookServlet
 */
@WebServlet("/publisher/postingBook")
public class PublisherPostingBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public PublisherPostingBookServlet() {
        super();
     }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(!ServletFileUpload.isMultipartContent(request))
			throw new BookException("파일 업로드 enctype 속성 미적용! : "
										+request.getRequestURI());
	
		String saveDirectory = getServletContext().getRealPath("/") + "/images/";
	
		int maxPostSize = 1024 * 1024 * 10; //10MB
		
		FileRenamePolicy policy = new MvcFileRenamePolicy();
		
		MultipartRequest multipartRequest
			= new MultipartRequest(request,
									saveDirectory,
									maxPostSize,
									"utf-8",
									policy);
		
		String isbn1 = multipartRequest.getParameter("isbn1");
		String isbn2 = multipartRequest.getParameter("isbn2");
		String isbn3 = multipartRequest.getParameter("isbn3");
		String isbn = isbn1 + isbn2 + isbn3;
				
		String bookTitle = multipartRequest.getParameter("bookTitle");
		String bookAuthor = multipartRequest.getParameter("bookAuthor");
		String price = multipartRequest.getParameter("price");
		String bookContent = multipartRequest.getParameter("bookContent");
		String category = multipartRequest.getParameter("category");
		String userId = multipartRequest.getParameter("userId");
		
		bookContent = bookContent.replaceAll("<", "&lt;")
								 .replaceAll(">", "&gt;");
		
		String bookOriginalFileName
				= multipartRequest.getOriginalFileName("upFile");
		String bookRenamedFileName
				= multipartRequest.getFilesystemName("upFile");
		
		String bookFileOriginalName
				= multipartRequest.getOriginalFileName("bookFile");
		String bookFileRenamedName
				= multipartRequest.getFilesystemName("bookFile");
		
		//저자이름으로 authorCode 가져오기
		String authorCode = new BookService().returnAuthorCode(bookAuthor);
		
		Book book 
			= new Book(isbn, userId, bookTitle, price, 
						bookContent, authorCode, category, 
						null, bookOriginalFileName, 
						bookRenamedFileName, 0, 
						"N", bookFileOriginalName, bookFileRenamedName);
		
		System.out.println("book@servlet="+book);
		
		int result = new BookService().insertBook(book);
		
		String view = "/WEB-INF/views/common/popup.jsp";
		String msg = result > 0? "책 등록 성공!" : "ISBN이 중복되어 책 등록에 실패하였습니다.";
		String loc = result > 0? "/book/bookInfoView?bookNo=" + book.getBookNo() : "/publisher/postingBookFrm";	
		
//		System.out.println("loc");
		request.setAttribute("msg", msg);
		request.setAttribute("loc", loc);
		request.getRequestDispatcher(view).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
