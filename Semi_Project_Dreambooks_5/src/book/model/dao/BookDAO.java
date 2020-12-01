package book.model.dao;

import static common.JDBCTemplate.close;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import book.model.vo.Book;
import book.model.vo.BookExtends;
import book.model.vo.BookOrdered;
import book.model.vo.Comments;
import book.model.vo.Tag;
import user.model.vo.SaledBook;

public class BookDAO {
	private Properties prop = new Properties();
	
	public BookDAO() {
		String fileName 
			= BookDAO.class
					  .getResource("/sql/book/book-query.properties")
					  .getPath();
		
		try {
			prop.load(new FileReader(fileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	public List<BookExtends> selectBookList(Connection conn, String category) {
		List<BookExtends> list = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectBookList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, category);
			
			rset = pstmt.executeQuery();
			
			list = new ArrayList<BookExtends>();
			while(rset.next()) {
				BookExtends b = new BookExtends();
				b.setBookNo(rset.getString("book_no"));
				b.setUserId(rset.getString("user_id"));
				b.setBookTitle(rset.getString("book_title"));
				b.setPrice(rset.getString("price"));
				b.setBookContent(rset.getString("book_content"));
				b.setAuthorCode(rset.getString("author_code"));
				b.setCategoryCode(rset.getString("category_code"));
				b.setBookTagCode(rset.getString("book_tag_code"));
				b.setBookImgOriginalFileName(rset.getString("book_img_original_filename"));
				b.setBookImgRenameFileName(rset.getString("book_img_rename_filename"));
				b.setSaleCount(rset.getInt("sale_count"));
				b.setDisplayBook(rset.getString("display_book"));
				b.setUserName(rset.getString("user_name"));
				b.setAuthorName(rset.getString("author_name"));
				b.setCategoryName(rset.getString("category_name"));

				list.add(b);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}

	public List<BookExtends> selectBookList(Connection conn, int cPage, int numPerPage, String category) {
		List<BookExtends> list = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql ="";
		if(category.equals("소설")||category.equals("에세이/시")||category.equals("컴퓨터/IT")||category.equals("교재/수험서")) {
			sql=prop.getProperty("selectBookListRefPage");
		}else {
			sql = prop.getProperty("selectBookListPage");
		}
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, category);
			pstmt.setInt(2, (cPage-1)*numPerPage+1);
			pstmt.setInt(3, cPage*numPerPage);
			
			rset = pstmt.executeQuery();
			
			list = new ArrayList<BookExtends>();
			while(rset.next()) {
				BookExtends b = new BookExtends();
				b.setBookNo(rset.getString("book_no"));
				b.setUserId(rset.getString("user_id"));
				b.setBookTitle(rset.getString("book_title"));
				b.setPrice(rset.getString("price"));
				b.setBookContent(rset.getString("book_content"));
				b.setAuthorCode(rset.getString("author_code"));
				b.setCategoryCode(rset.getString("category_code"));
				b.setBookTagCode(rset.getString("book_tag_code"));
				b.setBookImgOriginalFileName(rset.getString("book_img_original_filename"));
				b.setBookImgRenameFileName(rset.getString("book_img_rename_filename"));
				b.setSaleCount(rset.getInt("sale_count"));
				b.setDisplayBook(rset.getString("display_book"));
				b.setUserName(rset.getString("user_name"));
				b.setAuthorName(rset.getString("author_name"));
				b.setCategoryName(rset.getString("category_name"));

				list.add(b);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}

	public int selectTotalContents(Connection conn, String category) {
		int totalContents = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql="";
		if(category.equals("소설")||category.equals("에세이/시")||category.equals("컴퓨터/IT")||category.equals("교재/수험서")) {
			sql = prop.getProperty("selectBookTotalContents2");
		}else {
			sql = prop.getProperty("selectBookTotalContents");
		}
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, category);
			rset = pstmt.executeQuery();
			if(rset.next()) 
				totalContents = rset.getInt("TOTAL_CONTENTS");
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return totalContents;
	}
	
	public List<BookExtends> selectPubBook(Connection conn, int cPage, int numPerPage, String publisherId) {
		List<BookExtends> list = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectPubBook");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, publisherId);
			pstmt.setInt(2, (cPage-1)*numPerPage+1);
			pstmt.setInt(3, cPage*numPerPage);
			
			rset = pstmt.executeQuery();
			
			list = new ArrayList<BookExtends>();
			while(rset.next()) {
				BookExtends b = new BookExtends();
				b.setBookNo(rset.getString("book_no"));
				b.setUserId(rset.getString("user_id"));
				b.setBookTitle(rset.getString("book_title"));
				b.setPrice(rset.getString("price"));
				b.setBookContent(rset.getString("book_content"));
				b.setAuthorCode(rset.getString("author_code"));
				b.setCategoryCode(rset.getString("category_code"));
				b.setBookTagCode(rset.getString("book_tag_code"));
				b.setBookImgOriginalFileName(rset.getString("book_img_original_filename"));
				b.setBookImgRenameFileName(rset.getString("book_img_rename_filename"));
				b.setSaleCount(rset.getInt("sale_count"));
				b.setDisplayBook(rset.getString("display_book"));
				b.setUserName(rset.getString("user_name"));
				b.setAuthorName(rset.getString("author_name"));
				b.setCategoryName(rset.getString("category_name"));

				list.add(b);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}

	public Book selectOne(Connection conn, String bookNo) {
		Book b = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectOne");
	
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bookNo);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				b = new Book();
				
				b.setBookNo(rset.getString("book_no"));
				b.setUserId(rset.getString("user_name"));
				b.setBookTitle(rset.getString("book_title"));
				b.setPrice(rset.getString("price"));
				b.setBookContent(rset.getString("book_content"));
				b.setAuthorCode(rset.getString("author_name"));
				b.setCategoryCode(rset.getString("category_code"));
				b.setBookTagCode(rset.getString("book_tag_code"));
				b.setBookImgOriginalFileName(rset.getString("book_img_original_filename"));
				b.setBookImgRenameFileName(rset.getString("book_img_rename_filename"));
				b.setSaleCount(rset.getInt("sale_count"));
				b.setDisplayBook(rset.getString("display_book"));
				b.setBookFileOriginalName(rset.getString("book_file_original_name"));
				b.setBookFileRenamedName(rset.getString("book_file_renamed_name"));
				
				
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
//		System.out.println("book@dao = "+ b);
		
		return b;
	}
	public List<BookExtends> searchBook(Connection conn, String searchKeyword) {
		List<BookExtends> list = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("searchBookList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+searchKeyword+"%");
			pstmt.setString(2, "%"+searchKeyword+"%");
			pstmt.setString(3, "%"+searchKeyword+"%");

			rset = pstmt.executeQuery();
			
			list = new ArrayList<BookExtends>();
			while(rset.next()) {
				
				BookExtends b = new BookExtends();
				b.setBookNo(rset.getString("book_no"));
				b.setUserId(rset.getString("user_id"));
				b.setBookTitle(rset.getString("book_title"));
				b.setPrice(rset.getString("price"));
				b.setBookContent(rset.getString("book_content"));
				b.setAuthorCode(rset.getString("author_code"));
				b.setCategoryCode(rset.getString("category_code"));
				b.setBookImgOriginalFileName(rset.getString("book_img_original_filename"));
				b.setBookImgRenameFileName(rset.getString("book_img_rename_filename"));
				b.setSaleCount(rset.getInt("sale_count"));
				b.setDisplayBook(rset.getString("display_book"));
				b.setUserName(rset.getString("user_name"));
				b.setAuthorName(rset.getString("author_name"));
				b.setCategoryName(rset.getString("category_name"));
				list.add(b);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}

	public BookExtends selectBookInfoOne(Connection conn, String bookNo) {
		BookExtends b = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectBookInfoOne");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bookNo);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				b = new BookExtends();
				b.setBookNo(rset.getString("book_no"));
				b.setUserId(rset.getString("user_id"));
				b.setBookTitle(rset.getString("book_title"));
				b.setPrice(rset.getString("price"));
				b.setBookContent(rset.getString("book_content"));
				b.setAuthorCode(rset.getString("author_code"));
				b.setAuthorContent(rset.getString("author_content"));
				b.setCategoryCode(rset.getString("category_code"));
				b.setBookTagCode(rset.getString("book_tag_code"));
				b.setBookImgOriginalFileName(rset.getString("book_img_original_filename"));
				b.setBookImgRenameFileName(rset.getString("book_img_rename_filename"));
				b.setSaleCount(rset.getInt("sale_count"));
				b.setDisplayBook(rset.getString("display_book"));
				b.setUserName(rset.getString("user_name"));
				b.setAuthorName(rset.getString("author_name"));
				b.setCategoryName(rset.getString("category_name"));

			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return b;
	}

	public List<Comments> selectCommentList(Connection conn, String bookNo) {
		List<Comments> commentList = null;
		Comments c = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectCommentList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bookNo);
			
			rset = pstmt.executeQuery();
			
			commentList = new ArrayList<>();
			while(rset.next()) {
				c = new Comments();
				c.setCommentNo(rset.getInt("comment_no"));
				c.setCommentContent(rset.getString("comment_content"));
				c.setCommentDate(rset.getDate("comment_date"));
				c.setBookNo(rset.getString("book_no"));
				c.setUserId(rset.getString("user_id"));
				
				commentList.add(c);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
				
		return commentList;
	}

	public int insertComment(Connection conn, Comments c) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertComment");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, c.getCommentContent());
			pstmt.setString(2, c.getBookNo());
			pstmt.setString(3, c.getUserId());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
				
//		System.out.println("result@dao="+result);
		return result;
	}

	public List<Tag> selectTagList(Connection conn, String bookNo) {
		List<Tag> tagList = null;
		Tag t = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectTagList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bookNo);
			
			rset = pstmt.executeQuery();
			
			tagList = new ArrayList<>();
			while(rset.next()) {
				t = new Tag();
				t.setTagCode(rset.getString("tag_code"));
				t.setTagName(rset.getString("tag_name"));
				t.setBookNo(rset.getString("book_no"));
				t.setUserId(rset.getString("user_id"));				
				
				tagList.add(t);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
				
		return tagList;
	}

	public int insertTag(Connection conn, Tag tag) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertTag");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, tag.getTagName());
			pstmt.setString(2, tag.getBookNo());
			pstmt.setString(3, tag.getUserId());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
				
//		System.out.println("result@dao="+result);
		return result;
	}

	public List<Book> selectRankBoardList(Connection conn) {
		List<Book> list = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectRankBoardList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			rset = pstmt.executeQuery();
			
			list = new ArrayList<Book>();
			while(rset.next()) {
				Book b = new Book();
				b.setBookNo(rset.getString("book_no"));
				b.setUserId(rset.getString("user_id"));
				b.setBookTitle(rset.getString("book_title"));
				b.setPrice(rset.getString("price"));
				b.setBookContent(rset.getString("book_content"));
				b.setAuthorCode(rset.getString("author_code"));
				b.setCategoryCode(rset.getString("category_code"));
				b.setBookTagCode(rset.getString("book_tag_code"));
				b.setBookImgOriginalFileName(rset.getString("book_img_original_filename"));
				b.setBookImgRenameFileName(rset.getString("book_img_rename_filename"));
				b.setSaleCount(rset.getInt("sale_count"));
				b.setDisplayBook(rset.getString("display_book"));

				list.add(b);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}
	
	
	public String returnAuthorCode(Connection conn, String bookAuthor) {
		String authorCode="";
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("returnAuthorCode");
		ResultSet rset = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bookAuthor);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				authorCode = rset.getString("author_code");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return authorCode;
	}

	public int insertBook(Connection conn, Book book) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertBook");
				
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, book.getBookNo());
			pstmt.setString(2, book.getUserId());
			pstmt.setString(3, book.getBookTitle());
			pstmt.setString(4, book.getPrice());
			pstmt.setString(5, book.getBookContent());
			pstmt.setString(6, book.getAuthorCode());
			pstmt.setString(7, book.getCategoryCode());
			pstmt.setString(8, book.getBookTagCode());
			pstmt.setString(9, book.getBookImgOriginalFileName());
			pstmt.setString(10, book.getBookImgRenameFileName());
			pstmt.setInt(11, book.getSaleCount());
			pstmt.setString(12, book.getDisplayBook());
			pstmt.setString(13, book.getBookFileOriginalName());
			pstmt.setString(14, book.getBookFileRenamedName());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}
	
	public List<BookExtends> displayBookList(Connection conn) {
		List<BookExtends> list = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;		
		String sql = prop.getProperty("displayBookList");
		
		try {
			pstmt = conn.prepareStatement(sql);		
			rset = pstmt.executeQuery();
			
			list = new ArrayList<BookExtends>();
			while(rset.next()) {
				BookExtends b = new BookExtends();
				b.setBookNo(rset.getString("book_no"));
				b.setUserId(rset.getString("user_id"));
				b.setBookTitle(rset.getString("book_title"));
				b.setPrice(rset.getString("price"));
				b.setBookContent(rset.getString("book_content"));
				b.setAuthorCode(rset.getString("author_code"));
				b.setCategoryCode(rset.getString("category_code"));
				b.setBookTagCode(rset.getString("book_tag_code"));
				b.setBookImgOriginalFileName(rset.getString("book_img_original_filename"));
				b.setBookImgRenameFileName(rset.getString("book_img_rename_filename"));
				b.setSaleCount(rset.getInt("sale_count"));
				b.setDisplayBook(rset.getString("display_book"));
				b.setUserName(rset.getString("user_name"));
				b.setAuthorName(rset.getString("author_name"));
				b.setCategoryName(rset.getString("category_name"));

				list.add(b);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}		
//		System.out.println(list);
		return list;
	}

	public List<BookExtends> allBookList(Connection conn) {
		List<BookExtends> list = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;		
		String sql = prop.getProperty("allBookList");
		
		try {
			pstmt = conn.prepareStatement(sql);		
			rset = pstmt.executeQuery();
			
			list = new ArrayList<BookExtends>();
			while(rset.next()) {
				BookExtends b = new BookExtends();
				b.setBookNo(rset.getString("book_no"));
				b.setUserId(rset.getString("user_id"));
				b.setBookTitle(rset.getString("book_title"));
				b.setPrice(rset.getString("price"));
				b.setBookContent(rset.getString("book_content"));
				b.setAuthorCode(rset.getString("author_code"));
				b.setCategoryCode(rset.getString("category_code"));
				b.setBookTagCode(rset.getString("book_tag_code"));
				b.setBookImgOriginalFileName(rset.getString("book_img_original_filename"));
				b.setBookImgRenameFileName(rset.getString("book_img_rename_filename"));
				b.setSaleCount(rset.getInt("sale_count"));
				b.setDisplayBook(rset.getString("display_book"));
				b.setUserName(rset.getString("user_name"));
				b.setAuthorName(rset.getString("author_name"));
				b.setCategoryName(rset.getString("category_name"));

				list.add(b);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}		
//		System.out.println(list);
		return list;
	}

	public List<BookExtends> selectAllBookList(Connection conn) {
		List<BookExtends> list = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectAllBookList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			list = new ArrayList<>();
			
			while(rset.next()) {
				
				BookExtends pb = new BookExtends();
				
				pb.setBookNo(rset.getString("book_no"));
				pb.setUserName(rset.getString("user_name"));
				pb.setAuthorName(rset.getString("author_name"));
				pb.setBookTitle(rset.getString("book_title"));
				pb.setDisplayBook(rset.getString("display_book"));
				
				list.add(pb);
				
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
//		System.out.println("DAO@list = "+list);

		return list;
	}

	public int deleteBookComment(Connection conn, int bookCommentNo) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("deleteBookComment");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bookCommentNo);
	
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
				
//		System.out.println("result@dao="+result);
		return result;
	}

	public int updateComment(Connection conn, int commentNo, String bookCommentContent) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("updateComment");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bookCommentContent);
			pstmt.setInt(2, commentNo);
	
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
				
//		System.out.println("result@dao="+result);
		return result;
	}

	public int deleteTag(Connection conn, String tagCode) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("deleteTag");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, tagCode);
	
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
				
		return result;
	}

	public String selectCategoryTop(Connection conn, String categoryName) {
		String category_top="";
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("selectCategoryTop");
		ResultSet rset = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, categoryName);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				category_top = rset.getString("category_name");
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return category_top;
	}

	public int deleteBook(Connection conn, String bookNo) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("deleteBook");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bookNo);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
//		System.out.println("deleteBookNo@DAO="+bookNo);
//		System.out.println("Result@DAO="+result);
		return result;
	}

	public int selectTotalPublisherBook(Connection conn, String userId) {
		int selectTotalPublisherBook = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectTotalPublisherBook");

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			rset = pstmt.executeQuery();
			if(rset.next()) 
				selectTotalPublisherBook = rset.getInt("total_contents");
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return  selectTotalPublisherBook;
	}

	public List<BookExtends> selectPubBook(Connection conn, int cPage, int numPerPage, 
										  String userId, String option, String keyword) {
		
		List<BookExtends> list = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String key = "book_no".equals(option) ? "bookNoPubBookSearch" : 
			("book_title".equals(option) ? "bookTitlePubBookSearch" : "bookAuthorPubBookSearch");

		
		String sql = prop.getProperty(key);
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			pstmt.setString(2, "%"+keyword+"%");
			pstmt.setInt(3, (cPage-1)*numPerPage+1);
			pstmt.setInt(4, cPage*numPerPage);
			//cPage = 1, numPerPage = 10
			//1 - 10 
			//11 - 20
			//21 - 30
			
			rset = pstmt.executeQuery();
			
			list = new ArrayList<BookExtends>();
			while(rset.next()) {
				BookExtends b = new BookExtends();
				b.setBookNo(rset.getString("book_no"));
				b.setUserId(rset.getString("user_id"));
				b.setBookTitle(rset.getString("book_title"));
				b.setPrice(rset.getString("price"));
				b.setBookContent(rset.getString("book_content"));
				b.setAuthorCode(rset.getString("author_code"));
				b.setCategoryCode(rset.getString("category_code"));
				b.setBookTagCode(rset.getString("book_tag_code"));
				b.setBookImgOriginalFileName(rset.getString("book_img_original_filename"));
				b.setBookImgRenameFileName(rset.getString("book_img_rename_filename"));
				b.setSaleCount(rset.getInt("sale_count"));
				b.setDisplayBook(rset.getString("display_book"));
				b.setUserName(rset.getString("user_name"));
				b.setAuthorName(rset.getString("author_name"));
				b.setCategoryName(rset.getString("category_name"));

				list.add(b);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}

	public int selectTotalPublisherBook(Connection conn, String userId, String option, String keyword) {
		int total = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String key = "book_no".equals(option) ? "totalSearchbookNo" : 
												("book_title".equals(option) ? "totalSearchTitle" : "totalAuthorName");

		String sql = prop.getProperty(key);
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, userId);
//			System.out.println(userId);
			pstmt.setString(2, "%"+keyword+"%");
//			System.out.println(keyword);
			
			rset = pstmt.executeQuery();
			if(rset.next())
				total = rset.getInt("total_contents");
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}

//		System.out.println("total="+total);
		return total;
	}

	public List<BookExtends> cookieBookInfo(Connection conn, String bookCookieVal) {
		List<BookExtends> list = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectCookie");
		
//		System.out.println("bookcookie="+bookCookieVal);
		
		String[] arr = {null, null, null, null, null};
		String[] cookieArr = bookCookieVal.split("/");
		
		int size = cookieArr.length;
//		System.out.println("size="+size);
		
		if(size > 5 ) {
			for(int i=0 ; i<5; i++) {
				arr[i] = cookieArr[i];
//				System.out.println(arr[i]);
			}			
		}else {
			for(int i=0; i<size; i++) {
				arr[i] = cookieArr[i];
			}
		}
		
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, arr[0] != null ? arr[0] : "null");
			pstmt.setString(2, arr[1] != null ? arr[1] : "null");
			pstmt.setString(3, arr[2] != null ? arr[2] : "null");
			pstmt.setString(4, arr[3] != null ? arr[3] : "null");
			pstmt.setString(5, arr[4] != null ? arr[4] : "null");	
			
			rset = pstmt.executeQuery();
			
			list = new ArrayList<BookExtends>();
			while(rset.next()) {
				BookExtends b = new BookExtends();
				b.setBookNo(rset.getString("book_no"));
				b.setUserId(rset.getString("user_id"));
				b.setBookTitle(rset.getString("book_title"));
				b.setPrice(rset.getString("price"));
				b.setBookContent(rset.getString("book_content"));
				b.setAuthorCode(rset.getString("author_code"));
				b.setCategoryCode(rset.getString("category_code"));
				b.setBookTagCode(rset.getString("book_tag_code"));
				b.setBookImgOriginalFileName(rset.getString("book_img_original_filename"));
				b.setBookImgRenameFileName(rset.getString("book_img_rename_filename"));
				b.setSaleCount(rset.getInt("sale_count"));
				b.setDisplayBook(rset.getString("display_book"));
				b.setUserName(rset.getString("user_name"));
				b.setAuthorName(rset.getString("author_name"));
				b.setCategoryName(rset.getString("category_name"));

				list.add(b);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
//		System.out.println(list);
		return list;
	}
	
	

	public int updateBook(Connection conn, Book book, String originalIsbn) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("updateBook");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, book.getBookNo());
			pstmt.setString(2, book.getBookTitle());
			pstmt.setString(3, book.getPrice());
			pstmt.setString(4, book.getBookContent());
			pstmt.setString(5, book.getAuthorCode());
			pstmt.setString(6, book.getCategoryCode());
			pstmt.setString(7, book.getBookImgOriginalFileName());
			pstmt.setString(8, book.getBookImgRenameFileName());
			pstmt.setString(9, originalIsbn);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		

		return result;
	}

	public Tag checkTag(Connection conn, String tagName, String bookNo) {
		Tag t = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("checkTag");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bookNo);
			pstmt.setString(2, tagName);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				t = new Tag();
				t.setBookNo(rset.getString("book_no"));
				t.setUserId(rset.getString("user_id"));
				t.setTagCode(rset.getString("tag_code"));
				t.setTagName(rset.getString("tag_name"));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close(rset);
				close(pstmt);
			}
			
			return t;
				
	}
	
	public List<BookExtends> selectAllBookList(Connection conn, int cPage, int numPerPage) {
		List<BookExtends> list = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectAllBookList");

		try{
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, (cPage-1)*numPerPage+1);
			pstmt.setInt(2, cPage*numPerPage);

			rset = pstmt.executeQuery();

			list = new ArrayList<BookExtends>();

			while(rset.next()) {
				BookExtends b = new BookExtends();
					b.setBookNo(rset.getString("book_no"));
					b.setUserId(rset.getString("user_id"));
					b.setBookTitle(rset.getString("book_title"));
					b.setPrice(rset.getString("price"));
					b.setBookContent(rset.getString("book_content"));
					b.setAuthorCode(rset.getString("author_code"));
					b.setCategoryCode(rset.getString("category_code"));
					b.setBookTagCode(rset.getString("book_tag_code"));
					b.setBookImgOriginalFileName(rset.getString("book_img_original_filename"));
					b.setBookImgRenameFileName(rset.getString("book_img_rename_filename"));
					b.setSaleCount(rset.getInt("sale_count"));
					b.setDisplayBook(rset.getString("display_book"));
					b.setUserName(rset.getString("user_name"));
					b.setAuthorName(rset.getString("author_name"));
					b.setCategoryName(rset.getString("category_name"));

					list.add(b);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
				
		return list;
	}

	public int selectTotalAllBookList(Connection conn) {
		int selectTotalAllBookList = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectTotalAllBookList");

		try {
			pstmt = conn.prepareStatement(sql);

			rset = pstmt.executeQuery();
			if(rset.next()) 
				selectTotalAllBookList = rset.getInt("total_contents");
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return selectTotalAllBookList;
	}

	public int updateBookDisplay(Connection conn, String bookno, String display_yn) {
		int result=0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("updateBookDisplay");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, display_yn);
			pstmt.setString(2, bookno);
			
			result = pstmt.executeUpdate();
		
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
//		System.out.println("result@DAO = " +result);
		return result;
	}

	public static int updateSaleCount(Connection conn, int listSize, String[] bookNo) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = "update book set sale_count = sale_count+1 where book_no= ? ";
		
		try {
			for(int i=0; i<listSize; i++) {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, bookNo[i]);
				
				result = pstmt.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	

	public List<BookExtends> searchBook(Connection conn, String searchType, String searchKeyword, int cPage,
			int numPerPage) {
		List<BookExtends> list = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("searchBookPaging");

		String columnName = "";
		switch (searchType) {
		case "userName":
			columnName = "user_name";
			break;
		case "bookNo":
			columnName = "book_no";
			break;
		}
		sql = sql.replace("#", columnName);
//		System.out.println("sql@디에이오 = " + sql);

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + searchKeyword + "%");
			pstmt.setInt(2, (cPage - 1) * numPerPage + 1);
			pstmt.setInt(3, cPage * numPerPage);

			rset = pstmt.executeQuery();
			list = new ArrayList<BookExtends>();

			while (rset.next()) {
				BookExtends b = new BookExtends();
				b.setBookNo(rset.getString("book_no"));
				b.setUserId(rset.getString("user_id"));
				b.setBookTitle(rset.getString("book_title"));
				b.setPrice(rset.getString("price"));
				b.setBookContent(rset.getString("book_content"));
				b.setAuthorCode(rset.getString("author_code"));
				b.setCategoryCode(rset.getString("category_code"));
				b.setBookTagCode(rset.getString("book_tag_code"));
				b.setBookImgOriginalFileName(rset.getString("book_img_original_filename"));
				b.setBookImgRenameFileName(rset.getString("book_img_rename_filename"));
				b.setSaleCount(rset.getInt("sale_count"));
				b.setDisplayBook(rset.getString("display_book"));
				b.setUserName(rset.getString("user_name"));
				b.setAuthorName(rset.getString("author_name"));
				b.setCategoryName(rset.getString("category_name"));

				list.add(b);

//				System.out.println("리스트 = " + list);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}

	public int selectTotalBookContents(String searchType, String searchKeyword, Connection conn) {
		int totalContents = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectFinderTotalBookContents");
		
		String columnName = "";
		switch(searchType) {
		case "bookNo": columnName = "book_no"; break;
		case "userName": columnName = "user_name"; break;
		}
		sql = sql.replace("#", columnName);
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+searchKeyword+"%");
			
			rset = pstmt.executeQuery();
			if(rset.next()) {
				totalContents = rset.getInt("total_contents");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		//System.out.println("토탈 디에오 = " + totalContents);
		return totalContents;
	}

	public List<BookExtends> searchCategoryBook(Connection conn, String order, String category, int cPage, int numPerPage) {
		List <BookExtends> list = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = "";
		if(category.equals("소설")||category.equals("에세이/시")||category.equals("컴퓨터/IT")||category.equals("교재/수험서")) {
			sql = prop.getProperty("searchCategoryBook2");
		}else {
			sql = prop.getProperty("searchCategoryBook");
		}
		
		String columnName = "";
		switch(order) {
		case "selling" : columnName = "sale_count"; break; 
		case "name" : columnName = "author_name"; break;
		}
		sql = sql.replace("#", columnName);
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, category);
			pstmt.setInt(2, (cPage - 1) * numPerPage + 1);
			pstmt.setInt(3, cPage * numPerPage);
			
			rset = pstmt.executeQuery();
			list = new ArrayList<BookExtends>();
			
			while(rset.next()) {
				BookExtends b = new BookExtends();
				b.setBookNo(rset.getString("book_no"));
				b.setUserId(rset.getString("user_id"));
				b.setBookTitle(rset.getString("book_title"));
				b.setPrice(rset.getString("price"));
				b.setBookContent(rset.getString("book_content"));
				b.setAuthorCode(rset.getString("author_code"));
				b.setCategoryCode(rset.getString("category_code"));
				b.setBookTagCode(rset.getString("book_tag_code"));
				b.setBookImgOriginalFileName(rset.getString("book_img_original_filename"));
				b.setBookImgRenameFileName(rset.getString("book_img_rename_filename"));
				b.setSaleCount(rset.getInt("sale_count"));
				b.setDisplayBook(rset.getString("display_book"));
				b.setUserName(rset.getString("user_name"));
				b.setAuthorName(rset.getString("author_name"));
				b.setCategoryName(rset.getString("category_name"));

				list.add(b);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}

	public String returnOriginalFile(Connection conn, String bookTitle) {
		String fileName = "";
		
		String originalFileName = "";
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = "select book_file_original_name from book where book_title=?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bookTitle);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				originalFileName = rset.getString("book_file_original_name");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		sql = "select book_file_renamed_name from book where book_title=?";
		String renamedFileName = "";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bookTitle);
			rset = pstmt.executeQuery();

			while(rset.next()) {
				renamedFileName = rset.getString("book_file_renamed_name");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		fileName = renamedFileName == null ? originalFileName : renamedFileName;
		
		return fileName;
	}

	public List<BookExtends> searchTag(Connection conn, String searchKeyword) {
		List<BookExtends> list = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("searchTagList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+searchKeyword+"%");

			rset = pstmt.executeQuery();
			
			list = new ArrayList<BookExtends>();
			while(rset.next()) {
				
				BookExtends b = new BookExtends();
				b.setBookNo(rset.getString("book_no"));
				b.setUserId(rset.getString("user_id"));
				b.setBookTitle(rset.getString("book_title"));
				b.setPrice(rset.getString("price"));
				b.setBookContent(rset.getString("book_content"));
				b.setAuthorCode(rset.getString("author_code"));
				b.setCategoryCode(rset.getString("category_code"));
				b.setBookTagCode(rset.getString("book_tag_code"));
				b.setTagName(rset.getString("tag_name"));
				b.setBookImgOriginalFileName(rset.getString("book_img_original_filename"));
				b.setBookImgRenameFileName(rset.getString("book_img_rename_filename"));
				b.setSaleCount(rset.getInt("sale_count"));
				b.setDisplayBook(rset.getString("display_book"));
				b.setUserName(rset.getString("user_name"));
				b.setAuthorName(rset.getString("author_name"));
				b.setCategoryName(rset.getString("category_name"));
				list.add(b);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}

	public List<BookOrdered> bookOrdered(Connection conn, String userId, int cPage, int numPerPage) {
		List<BookOrdered> list1 = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("bookOrdered1");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			pstmt.setInt(2, (cPage - 1) * numPerPage + 1);
			pstmt.setInt(3, cPage * numPerPage);
			
			rset = pstmt.executeQuery();
			list1 = new ArrayList<BookOrdered>();
			
			while(rset.next()) {
				BookOrdered ob = new BookOrdered();
				ob.setBookTitle(rset.getString("book_title")); //
				ob.setAuthorName(rset.getString("author_name")); //
				ob.setPublisherName(rset.getString("publisher_name")); //
				ob.setOrderDate(rset.getDate("order_date")); //
				ob.setUserId(rset.getString("user_id")); //
				
				list1.add(ob);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		//System.out.println("리스트1 = " + list1);
		return list1;
	}

	public int selectTotalBookOrderedContents(Connection conn, String userId) {
		int totalContents = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectTotalBookOrderedContents");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			
			rset = pstmt.executeQuery();
			if(rset.next()) {
				totalContents = rset.getInt("total_contents");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return totalContents;
	}


	public List<SaledBook> saledBook(Connection conn) {
		List<SaledBook> list = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("saledBook");
//		System.out.println(sql);

		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			list = new ArrayList<SaledBook>();
			
			while (rset.next()) {
				SaledBook sb = new SaledBook();
				sb.setDateOrder(rset.getString("order_date"));
				sb.setDateBookCount(rset.getInt("date_book_count"));
				sb.setDateSaledPrice(rset.getInt("date_total_price"));
				
				list.add(sb);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
//		System.out.println("리스트닷 = " + list);
		return list;
	}
	
	
	
}
