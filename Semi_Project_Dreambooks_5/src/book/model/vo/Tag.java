package book.model.vo;

import java.io.Serializable;

public class Tag implements Serializable {

	private String tagCode;
	private String tagName;
	private String bookNo;
	private String userId;
	public Tag() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Tag(String tagCode, String tagName, String bookNo, String userId) {
		super();
		this.tagCode = tagCode;
		this.tagName = tagName;
		this.bookNo = bookNo;
		this.userId = userId;
	}
	public String getTagCode() {
		return tagCode;
	}
	public void setTagCode(String tagCode) {
		this.tagCode = tagCode;
	}
	public String getTagName() {
		return tagName;
	}
	public void setTagName(String tagName) {
		this.tagName = tagName;
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
		return "Tag [tagCode=" + tagCode + ", tagName=" + tagName + ", bookNo=" + bookNo + ", userId=" + userId + "]";
	}
	
	
}
