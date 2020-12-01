package user.model.vo;

import java.io.Serializable;
import java.sql.Date;

public class OrderBook implements Serializable {

	private String orderBookNo;
	private String orderNo;
	private String userId;
	private String bookTitle;
	private String price;
	private Date orderDate;
	private String originalFileName;
	private String renamedFileName;
	
	public OrderBook() {
		super();
		// TODO Auto-generated constructor stub
	}

	public OrderBook(String orderBookNo, String orderNo, String userId, String bookTitle, String price,
			Date orderDate, String originalFileName, String renamedFileName) {
		super();
		this.orderBookNo = orderBookNo;
		this.orderNo = orderNo;
		this.userId = userId;
		this.bookTitle = bookTitle;
		this.price = price;
		this.orderDate = orderDate;
		this.originalFileName = originalFileName;
		this.renamedFileName = renamedFileName;
	}

	public String getOrderBookNo() {
		return orderBookNo;
	}

	public void setOrderBookNo(String orderBookNo) {
		this.orderBookNo = orderBookNo;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
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

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	
	public String getOriginalFileName() {
		return originalFileName;
	}

	public void setOriginalFileName(String originalFileName) {
		this.originalFileName = originalFileName;
	}

	public String getRenamedFileName() {
		return renamedFileName;
	}

	public void setRenamedFileName(String renamedFileName) {
		this.renamedFileName = renamedFileName;
	}

	@Override
	public String toString() {
		return "OrderBook [orderBookNo=" + orderBookNo + ", orderNo=" + orderNo + ", userId=" + userId + ", bookTitle="
				+ bookTitle + ", price=" + price + ", orderDate=" + orderDate + ", originalFileName=" + originalFileName
				+ ", renamedFileName=" + renamedFileName + "]";
	}


	
	
	
}
