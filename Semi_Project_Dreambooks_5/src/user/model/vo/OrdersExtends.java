package user.model.vo;

import java.io.Serializable;
import java.sql.Date;

public class OrdersExtends extends Orders{

	private String bookTitle;
	private String countBookNo;

	public OrdersExtends() {
		super();
		// TODO Auto-generated constructor stub
	}

	public OrdersExtends(String orderNo, String userId, Date orderDate, String cancelYN, String total) {
		super(orderNo, userId, orderDate, cancelYN, total);
		// TODO Auto-generated constructor stub
	}

	public OrdersExtends(String bookTitle, String countBookNo) {
		super();
		this.bookTitle = bookTitle;
		this.countBookNo = countBookNo;
	}

	public String getBookTitle() {
		return bookTitle;
	}

	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}

	public String getCountBookNo() {
		return countBookNo;
	}

	public void setCountBookNo(String countBookNo) {
		this.countBookNo = countBookNo;
	}

	@Override
	public String toString() {
		return "OrdersExtends [bookTitle=" + bookTitle + ", countBookNo=" + countBookNo + "]";
	}
	

	
	
	
	
	
}
