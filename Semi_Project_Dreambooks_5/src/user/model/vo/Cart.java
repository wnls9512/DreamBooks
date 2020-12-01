package user.model.vo;

import java.io.Serializable;

public class Cart implements Serializable{

	private String cartCode;
	private String bookNo;
	private String userId;
	private String cartTotalPrice;
	public Cart() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Cart(String cartCode, String bookNo, String userId, String cartTotalPrice) {
		super();
		this.cartCode = cartCode;
		this.bookNo = bookNo;
		this.userId = userId;
		this.cartTotalPrice = cartTotalPrice;
	}
	public String getCartCode() {
		return cartCode;
	}
	public void setCartCode(String cartCode) {
		this.cartCode = cartCode;
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
	public String getCartTotalPrice() {
		return cartTotalPrice;
	}
	public void setCartTotalPrice(String cartTotalPrice) {
		this.cartTotalPrice = cartTotalPrice;
	}
	@Override
	public String toString() {
		return "Cart [cartCode=" + cartCode + ", bookNo=" + bookNo + ", userId=" + userId + ", cartTotalPrice="
				+ cartTotalPrice + "]";
	}
	
	
}
