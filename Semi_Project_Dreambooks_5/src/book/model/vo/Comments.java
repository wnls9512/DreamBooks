package book.model.vo;

import java.io.Serializable;
import java.sql.Date;

public class Comments implements Serializable {

	private int commentNo;
	private String commentContent;
	private Date commentDate;
	private String bookNo;
	private String userId;
	public Comments() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Comments(int commentNo, String commentContent, Date commentDate, String bookNo, String userId) {
		super();
		this.commentNo = commentNo;
		this.commentContent = commentContent;
		this.commentDate = commentDate;
		this.bookNo = bookNo;
		this.userId = userId;
	}
	public int getCommentNo() {
		return commentNo;
	}
	public void setCommentNo(int commentNo) {
		this.commentNo = commentNo;
	}
	public String getCommentContent() {
		return commentContent;
	}
	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}
	public Date getCommentDate() {
		return commentDate;
	}
	public void setCommentDate(Date commentDate) {
		this.commentDate = commentDate;
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
	@Override
	public String toString() {
		return "Comments [commentNo=" + commentNo + ", commentContent=" + commentContent + ", commentDate="
				+ commentDate + ", bookNo=" + bookNo + ", userId=" + userId + "]";
	}

	
	
}
