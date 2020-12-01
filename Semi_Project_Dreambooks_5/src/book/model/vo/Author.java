package book.model.vo;

import java.io.Serializable;

public class Author implements Serializable {

	private String authorCode;
	private String authorName;
	private String authorContent;
	public Author() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Author(String authorCode, String authorName, String authorContent) {
		super();
		this.authorCode = authorCode;
		this.authorName = authorName;
		this.authorContent = authorContent;
	}
	public String getAuthorCode() {
		return authorCode;
	}
	public void setAuthorCode(String authorCode) {
		this.authorCode = authorCode;
	}
	public String getAuthorName() {
		return authorName;
	}
	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}
	public String getAuthorContent() {
		return authorContent;
	}
	public void setAuthorContent(String authorContent) {
		this.authorContent = authorContent;
	}
	@Override
	public String toString() {
		return "Author [authorCode=" + authorCode + ", authorName=" + authorName + ", authorContent=" + authorContent
				+ "]";
	}
	
	
	
	
}
