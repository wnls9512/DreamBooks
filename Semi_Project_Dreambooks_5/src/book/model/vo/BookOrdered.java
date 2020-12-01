package book.model.vo;

import java.util.Date;

public class BookOrdered extends BookExtends {
	
	private Date orderDate;
	private String publisherName;
	public BookOrdered() {
		super();
		// TODO Auto-generated constructor stub
	}
	public BookOrdered(String bookNo, String userId, String bookTitle, String price, String bookContent,
			String authorCode, String categoryCode, String bookTagCode, String bookImgOriginalFileName,
			String bookImgRenameFileName, int saleCount, String displayBook, String bookFileOriginalName,
			String bookFileRenamedName) {
		super(bookNo, userId, bookTitle, price, bookContent, authorCode, categoryCode, bookTagCode, bookImgOriginalFileName,
				bookImgRenameFileName, saleCount, displayBook, bookFileOriginalName, bookFileRenamedName);
		// TODO Auto-generated constructor stub
	}
	public BookOrdered(Date orderDate, String publisherName) {
		super();
		this.orderDate = orderDate;
		this.publisherName = publisherName;
	}
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	public String getPublisherName() {
		return publisherName;
	}
	public void setPublisherName(String publisherName) {
		this.publisherName = publisherName;
	}
	@Override
	public String toString() {
		return "BookOrdered [orderDate=" + orderDate + ", publisherName=" + publisherName + ", getAuthorName()=" + getAuthorName() + ", getUserId()=" + getUserId()
				+ ", getBookTitle()=" + getBookTitle()+"]";
	}
	
	
	
	
}
