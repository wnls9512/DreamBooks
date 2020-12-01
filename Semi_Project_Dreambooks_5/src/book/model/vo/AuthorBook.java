package book.model.vo;

import java.io.Serializable;

public class AuthorBook implements Serializable {
	
	private String bookNo;
	private String authorCode;
	public AuthorBook() {
		super();
		// TODO Auto-generated constructor stub
	}
	public AuthorBook(String bookNo, String authorCode) {
		super();
		this.bookNo = bookNo;
		this.authorCode = authorCode;
	}
	public String getBookNo() {
		return bookNo;
	}
	public void setBookNo(String bookNo) {
		this.bookNo = bookNo;
	}
	public String getAuthorCode() {
		return authorCode;
	}
	public void setAuthorCode(String authorCode) {
		this.authorCode = authorCode;
	}
	@Override
	public String toString() {
		return "AuthorBook [bookNo=" + bookNo + ", authorCode=" + authorCode + "]";
	}
	
	
}
