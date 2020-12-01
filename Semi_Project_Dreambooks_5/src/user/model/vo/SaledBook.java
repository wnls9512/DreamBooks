package user.model.vo;

import java.sql.Date;

public class SaledBook extends OrdersExtends {

	
	private int DateBookCount;
	private int DateSaledPrice;
	private String DateOrder;

	public SaledBook() {
		super();
		// TODO Auto-generated constructor stub
	}



	public SaledBook(String orderNo, String userId, Date orderDate, String cancelYN, String total) {
		super(orderNo, userId, orderDate, cancelYN, total);
		// TODO Auto-generated constructor stub
	}



	public SaledBook(String bookTitle, String countBookNo) {
		super(bookTitle, countBookNo);
		// TODO Auto-generated constructor stub
	}


	

	public SaledBook(int dateBookCount, int dateSaledPrice, String dateOrder) {
		super();
		DateBookCount = dateBookCount;
		DateSaledPrice = dateSaledPrice;
		DateOrder = dateOrder;
	}


	

	public int getDateBookCount() {
		return DateBookCount;
	}



	public void setDateBookCount(int dateBookCount) {
		DateBookCount = dateBookCount;
	}



	public int getDateSaledPrice() {
		return DateSaledPrice;
	}



	public void setDateSaledPrice(int dateSaledPrice) {
		DateSaledPrice = dateSaledPrice;
	}



	public String getDateOrder() {
		return DateOrder;
	}



	public void setDateOrder(String dateOrder) {
		DateOrder = dateOrder;
	}



	@Override
	public String toString() {
		return "SaledBook [DateBookCount=" + DateBookCount + ", DateSaledPrice=" + DateSaledPrice + ", DateOrder="
				+ DateOrder + "]";
	}


	
	
	
	
	
	
	
	
	
	
	
	
}
