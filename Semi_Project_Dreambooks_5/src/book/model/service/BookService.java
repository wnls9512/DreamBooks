package book.model.service;

import static common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.List;

import book.model.dao.BookDAO;
import book.model.vo.Book;
import book.model.vo.BookExtends;
import book.model.vo.BookOrdered;
import book.model.vo.Comments;
import book.model.vo.Tag;
import user.model.vo.SaledBook;

public class BookService {

	private BookDAO bookDAO = new BookDAO();

	public List<BookExtends> selectBoardList(String category) {
		Connection conn = getConnection();
		List<BookExtends> list = bookDAO.selectBookList(conn, category);
		close(conn);
		return list;
	}

	public List<BookExtends> selectBookList(int cPage, int numPerPage, String category) {
		Connection conn = getConnection();
		List<BookExtends> list = bookDAO.selectBookList(conn, cPage, numPerPage, category);
		close(conn);
		return list;
	}

	public int selectTotalContents(String category) {
		Connection conn = getConnection();
		int totalContents = bookDAO.selectTotalContents(conn, category);
		close(conn);
		return totalContents;
	}

	public List<BookExtends> selectPubBook(int cPage, int numPerPage, String pubId) {
		Connection conn = getConnection();
		List<BookExtends> list = bookDAO.selectPubBook(conn, cPage, numPerPage, pubId);
		close(conn);

		return list;
	}

	public Book selectOne(String bookNo) {
		Connection conn = getConnection();
		Book b = bookDAO.selectOne(conn, bookNo);
		close(conn);
		
//		System.out.println(b);
		return b;
	}
	public List<BookExtends> searchBook(String searchKeyword) {
		Connection conn = getConnection();
		List<BookExtends> list = bookDAO.searchBook(conn,searchKeyword);
//		System.out.println(list);
		close(conn);
		return list;
	}

	public BookExtends selectBookInfoOne(String bookNo) {
		Connection conn = getConnection();
		BookExtends book = bookDAO.selectBookInfoOne(conn, bookNo);
		close(conn);
		return book;
	}

	public List<Comments> selectCommentList(String bookNo) {
		Connection conn = getConnection();
		List<Comments> commentList = bookDAO.selectCommentList(conn,bookNo);
		close(conn);
		return commentList;
	}

	public int insertComment(Comments c) {
		Connection conn = getConnection();
		int result = bookDAO.insertComment(conn, c);
		if(result>0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;	
	}

	public List<Tag> selectTagList(String bookNo) {
		Connection conn = getConnection();
		List<Tag> commentList = bookDAO.selectTagList(conn,bookNo);
		close(conn);
		return commentList;
	}

	public int insertTag(Tag tag) {
		Connection conn = getConnection();
		int result = bookDAO.insertTag(conn, tag);
		if(result>0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;	
	}

	public List<Book> selectRankBoardList() {
		Connection conn = getConnection();
		List<Book> list = bookDAO.selectRankBoardList(conn);
		close(conn);
		return list;
	}

	public String returnAuthorCode(String bookAuthor) {
		Connection conn = getConnection();
		String authorCode = bookDAO.returnAuthorCode(conn, bookAuthor);
		close(conn);
		return authorCode;
	}

	public int insertBook(Book book) {
		Connection conn = getConnection();
		int result = bookDAO.insertBook(conn, book);
		
		if(result>0) commit(conn);
		else rollback(conn);
		
		close(conn);
		
		return result;
	}

	public List<BookExtends> selectAllBookList() {
		Connection conn = getConnection();
		List<BookExtends> list = bookDAO.selectAllBookList(conn);
		close(conn);
//		System.out.println("service@list = "+list);

		return list;
	}

	public int deleteBookComment(int bookCommentNo) {
		Connection conn = getConnection();
		int result = bookDAO.deleteBookComment(conn, bookCommentNo);
		
		if(result>0) commit(conn);
		else rollback(conn);
		
		close(conn);
		
		return result;
	}

	public int updateComment(int commentNo, String bookCommentContent) {
		Connection conn = getConnection();
		int result = bookDAO.updateComment(conn, commentNo, bookCommentContent);
		
		if(result>0) commit(conn);
		else rollback(conn);
		
		close(conn);
		
		return result;
	}

	public int deleteTag(String tagCode) {
		Connection conn = getConnection();
		int result = bookDAO.deleteTag(conn, tagCode);
		
		if(result>0) commit(conn);
		else rollback(conn);
		
		close(conn);
		
		return result;
	}

	public String selectCategoryTop(String categoryName) {
		Connection conn = getConnection();
		String category_top = bookDAO.selectCategoryTop(conn, categoryName);
		return category_top;
	}

	public int deleteBook(String bookNo) {
		Connection conn = getConnection();
		int result = bookDAO.deleteBook(conn, bookNo);
		if(result>0) commit(conn);
		else rollback(conn);
		close(conn);

		return result;
	}

	public int selectTotalPublisherBook(String userId) {
		Connection conn = getConnection();
		int selectTotalPublisherBook = bookDAO.selectTotalPublisherBook(conn, userId);
		close(conn);
		return selectTotalPublisherBook;
	}

	public List<BookExtends> selectPubBook(int cPage, int numPerPage, String userId, String option, String keyword) {
		Connection conn = getConnection();
		List<BookExtends> list = bookDAO.selectPubBook(conn, cPage, numPerPage, userId, option, keyword);
		close(conn);
				
		return list;
	}

	public int selectTotalPublisherBook(String userId, String option, String keyword) {
		Connection conn = getConnection();
		int selectTotalPublisherBook = bookDAO.selectTotalPublisherBook(conn, userId, option, keyword);
		close(conn);
		return selectTotalPublisherBook;
	}

	public List<BookExtends> cookieBookInfo(String bookCookieVal) {
		Connection conn = getConnection();
		List<BookExtends> list = bookDAO.cookieBookInfo(conn,bookCookieVal);
		close(conn);
		return list;
	}
	
	public int updateBook(Book book, String originalIsbn) {
		Connection conn = getConnection();
		int result = bookDAO.updateBook(conn, book, originalIsbn);
		if(result>0) commit(conn);
		else rollback(conn);
		close(conn);
		
		return result;
	}
	
	public List<BookExtends> displayBookList() {
		Connection conn = getConnection();
		List<BookExtends> list = bookDAO.displayBookList(conn);
		close(conn);
		return list;
	}

	public List<BookExtends> allBookList() {
		Connection conn = getConnection();
		List<BookExtends> list = bookDAO.allBookList(conn);
		close(conn);
		return list;
	}

	public Tag checkTag(String tagName, String bookNo) {
		Connection conn = getConnection();
		Tag t = bookDAO.checkTag(conn, tagName,bookNo);
		close(conn);
		
//		System.out.println(t);
		return t;
	}
	
	public List<BookExtends> selectAllBookList(int cPage, int numPerPage){
		Connection conn = getConnection();
		List<BookExtends> list = bookDAO.selectAllBookList(conn, cPage, numPerPage);
		close(conn);
		return list;
	}
	
	public int selectTotalAllBookList() {
		Connection conn = getConnection();
		int selectTotalAllBookList = bookDAO.selectTotalAllBookList(conn);
		close(conn);
		return selectTotalAllBookList;
	}
	
	public int updateBookDisplay(String bookno, String display_yn) {
		Connection conn = getConnection();
		int result = bookDAO.updateBookDisplay(conn, bookno, display_yn);
		
		if(result > 0) commit(conn);
		else rollback(conn);
		
		close(conn);
		
		return result;
	}
	
	public List<BookExtends> searchBook(String searchType, String searchKeyword, int cPage, int numPerPage) {
		Connection conn = getConnection();
		List<BookExtends> list = bookDAO.searchBook(conn,searchType,searchKeyword, cPage, numPerPage);
		close(conn);
		return list;
	}

	public int selectTotalBookContents(String searchType, String searchKeyword) {
		Connection conn = getConnection();
		int totalContents = bookDAO.selectTotalBookContents(searchType,searchKeyword,conn);
		close(conn);
		return totalContents;
	}
	
	public List<BookExtends> searchCategoryBook(int cPage, int numPerPage, String order, String category) {
		Connection conn = getConnection();
		List<BookExtends> list = bookDAO.searchCategoryBook(conn,order,category,cPage, numPerPage);
		close(conn);
		return list;
	}

	public String returnOriginalFile(String bookTitle) {
		Connection conn = getConnection();
		String fileName = bookDAO.returnOriginalFile(conn, bookTitle);
		close(conn);
//		System.out.println(fileName);
		return fileName;
	}

	public List<BookExtends> searchTag(String searchKeyword) {
		Connection conn = getConnection();
		List<BookExtends> list = bookDAO.searchTag(conn,searchKeyword);
		close(conn);
		return list;
	}

	public List<BookOrdered> bookOrdered(String userId, int cPage, int numPerPage) {
		Connection conn = getConnection();
		List<BookOrdered> list1 = bookDAO.bookOrdered(conn,userId,cPage,numPerPage);
		close(conn);
		return list1;
	}

	public int selectTotalBookOrderedContents(String userId) {
		Connection conn = getConnection();
		int totalContents = bookDAO.selectTotalBookOrderedContents(conn,userId);
		close(conn);
		return totalContents;
	}
	
	public List<SaledBook> saledBook() {
		Connection conn = getConnection();
		List<SaledBook> list = bookDAO.saledBook(conn);
		close(conn);
		return list;
	}
	
	
	
}
