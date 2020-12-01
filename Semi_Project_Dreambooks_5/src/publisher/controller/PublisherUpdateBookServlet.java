package publisher.controller;

import java.io.File;
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
 * Servlet implementation class PublisherUpdateBookServlet
 */
@WebServlet("/publisher/updateBook")
public class PublisherUpdateBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
     public PublisherUpdateBookServlet() {
        super();
     }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 0. enctype = "multipart/form-data" 여부 확인
		if(!ServletFileUpload.isMultipartContent(request)) {
			throw new BookException("enctype오류!");
		}
		
		// 1. MultipartRequest(cos.jar) 객체
		String saveDirectory = getServletContext().getRealPath("/") + "/images/";
		int maxPostSize = 1024*1024*10; // 10MB
		String encoding = "utf-8";
		FileRenamePolicy policy = new MvcFileRenamePolicy();
		MultipartRequest multipartReq
				= new MultipartRequest(request, saveDirectory, maxPostSize, "utf-8", policy);
		
		// 2. 사용자 입력값, 저장된 파일명, 업로드한 파일명
		String originalIsbn = multipartReq.getParameter("bookNo");
		String isbn1 = multipartReq.getParameter("isbn1");
		String isbn2 = multipartReq.getParameter("isbn2");
		String isbn3 = multipartReq.getParameter("isbn3");
		String isbn = isbn1 + isbn2 + isbn3;
		
		String bookTitle = multipartReq.getParameter("bookTitle");
		String bookAuthor = multipartReq.getParameter("bookAuthor");
		String price = multipartReq.getParameter("price");
		String bookContent = multipartReq.getParameter("bookContent");
		String category = multipartReq.getParameter("category");
		String userId = multipartReq.getParameter("userId");
		String delFile = multipartReq.getParameter("delFile");
		
		//img 파일
		String bookOriginalImgName = multipartReq.getOriginalFileName("upFile");
		String bookRenamedImgName = multipartReq.getFilesystemName("upFile");
		
		String oldOriginalImgName = multipartReq.getParameter("oldOriginalFileName");
		String oldRenamedImgName = multipartReq.getParameter("oldRenamedFileName");
		
		
		//pdf 파일
		String OriginalBookFileName = multipartReq.getOriginalFileName("bookFile");
		String RenamedBookFileName = multipartReq.getFilesystemName("bookFile");
		
		String oldOriginalFileName = multipartReq.getParameter("oldOriginalBookFileName");
		String oldRenamedFileName = multipartReq.getParameter("oldRenamedBookFileName");
		
		
		//저자 이름으로 authorCode 가져오기
		String authorCode = new BookService().returnAuthorCode(bookAuthor);
		
		// 3. 업무로직
		Book book
			= new Book(isbn, userId, bookTitle, price, 
						bookContent, authorCode, category, null, bookOriginalImgName, bookRenamedImgName, 
						0, "N", OriginalBookFileName, RenamedBookFileName);
		
		// 기존 첨부 파일이 있는 경우 처리
		if(!"".equals(oldOriginalImgName)) {
			//기존 첨부파일이 있고, 수정에서 새로 업로드하지 않은 경우
			if(bookOriginalImgName == null && delFile == null) {
				book.setBookImgOriginalFileName(oldOriginalImgName);
				book.setBookImgRenameFileName(oldRenamedImgName);
			} else {
					//기존 첨부파일도 있고, 수정에서 새로 업로드한 파일이 있는 경우
				//기존 파일 삭제
				File f = new File(saveDirectory, oldRenamedImgName);
				f.delete();
//				System.out.println("[ " + oldOriginalImgName + " ] 파일 삭제!");
			}
		}
		
//		System.out.println("bookUpdate@servlet = "+ book);
		int result = new BookService().updateBook(book, originalIsbn);
		
		// 4. view단 처리
		String msg = result>0 ? "도서 수정 성공!" : "도서 수정 실패";
		String loc = "/book/bookInfoView?bookNo="+book.getBookNo();
		request.setAttribute("msg", msg);
		request.setAttribute("loc", loc);
		request.getRequestDispatcher("/WEB-INF/views/common/popup.jsp")
					.forward(request, response);
		
		
	}

}
