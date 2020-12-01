package book.model.vo;

import java.io.Serializable;

public class Book implements Serializable {
	
	private String bookNo;
	private String userId;
	private String bookTitle;
	private String price;
	private String bookContent;
	private String authorCode;
	private String categoryCode;
	private String bookTagCode;
	private String bookImgOriginalFileName;
	private String bookImgRenameFileName;
	private int saleCount;
	private String displayBook;
	private String bookFileOriginalName;
	private String bookFileRenamedName;
	
	public Book() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Book(String bookNo, String userId, String bookTitle, String price, String bookContent, String authorCode,
			String categoryCode, String bookTagCode, String bookImgOriginalFileName, String bookImgRenameFileName,
			int saleCount, String displayBook, String bookFileOriginalName, String bookFileRenamedName) {
		super();
		this.bookNo = bookNo;
		this.userId = userId;
		this.bookTitle = bookTitle;
		this.price = price;
		this.bookContent = bookContent;
		this.authorCode = authorCode;
		this.categoryCode = categoryCode;
		this.bookTagCode = bookTagCode;
		this.bookImgOriginalFileName = bookImgOriginalFileName;
		this.bookImgRenameFileName = bookImgRenameFileName;
		this.saleCount = saleCount;
		this.displayBook = displayBook;
		this.bookFileOriginalName = bookFileOriginalName;
		this.bookFileRenamedName = bookFileRenamedName;
	}
	
	public String getBookNo() {
		return bookNo;
	}
	public void setBookNo(String bookNo) {
		this.bookNo = bookNo;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getBookTitle() {
		return bookTitle;
	}
	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getBookContent() {
		return bookContent;
	}
	public void setBookContent(String bookContent) {
		this.bookContent = bookContent;
	}
	public String getAuthorCode() {
		return authorCode;
	}
	public void setAuthorCode(String authorCode) {
		this.authorCode = authorCode;
	}
	public String getCategoryCode() {
		return categoryCode;
	}
	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}
	public String getBookTagCode() {
		return bookTagCode;
	}
	public void setBookTagCode(String bookTagCode) {
		this.bookTagCode = bookTagCode;
	}
	public String getBookImgOriginalFileName() {
		return bookImgOriginalFileName;
	}
	public void setBookImgOriginalFileName(String bookImgOriginalFileName) {
		this.bookImgOriginalFileName = bookImgOriginalFileName;
	}
	public String getBookImgRenameFileName() {
		return bookImgRenameFileName;
	}
	public void setBookImgRenameFileName(String bookImgRenameFileName) {
		this.bookImgRenameFileName = bookImgRenameFileName;
	}
	public int getSaleCount() {
		return saleCount;
	}
	public void setSaleCount(int saleCount) {
		this.saleCount = saleCount;
	}
	public String getDisplayBook() {
		return displayBook;
	}
	public void setDisplayBook(String displayBook) {
		this.displayBook = displayBook;
	}
	
	public String getBookFileOriginalName() {
		return bookFileOriginalName;
	}

	public void setBookFileOriginalName(String bookFileOriginalName) {
		this.bookFileOriginalName = bookFileOriginalName;
	}

	public String getBookFileRenamedName() {
		return bookFileRenamedName;
	}

	public void setBookFileRenamedName(String bookFileRenamedName) {
		this.bookFileRenamedName = bookFileRenamedName;
	}

	@Override
	public String toString() {
		return "Book [bookNo=" + bookNo + ", userId=" + userId + ", bookTitle=" + bookTitle + ", price=" + price
				+ ", bookContent=" + bookContent + ", authorCode=" + authorCode + ", categoryCode=" + categoryCode
				+ ", bookTagCode=" + bookTagCode + ", bookImgOriginalFileName=" + bookImgOriginalFileName
				+ ", bookImgRenameFileName=" + bookImgRenameFileName + ", saleCount=" + saleCount + ", displayBook="
				+ displayBook + ", bookFileOriginalName=" + bookFileOriginalName + ", bookFileRenamedName="
				+ bookFileRenamedName + "]";
	}



}
