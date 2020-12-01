package user.model.vo;

import java.io.Serializable;
import java.sql.Date;

public class Orders implements Serializable {

	private String orderNo;
	private String userId;
	private Date orderDate;
	private String cancelYN;
	private String total;
	public Orders() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Orders(String orderNo, String userId, Date orderDate, String cancelYN, String total) {
		super();
		this.orderNo = orderNo;
		this.userId = userId;
		this.orderDate = orderDate;
		this.cancelYN = cancelYN;
		this.total = total;
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
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	public String getCancelYN() {
		return cancelYN;
	}
	public void setCancelYN(String cancelYN) {
		this.cancelYN = cancelYN;
	}
	public String getTotal() {
		return total;
	}
	public void setTotal(String total) {
		this.total = total;
	}
	@Override
	public String toString() {
		return "Orders [orderNo=" + orderNo + ", userId=" + userId + ", orderDate=" + orderDate + ", cancelYN="
				+ cancelYN + ", total=" + total + "]";
	}
	
}
