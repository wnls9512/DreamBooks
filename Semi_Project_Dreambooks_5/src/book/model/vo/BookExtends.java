package book.model.vo;

public class BookExtends extends Book{
	
	private String userName;
	private String authorName;
	private String authorContent;
	private String categoryName;
	private String tagName;

	public BookExtends() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BookExtends(String bookNo, String userId, String bookTitle, String price, String bookContent,
			String authorCode, String categoryCode, String bookTagCode, String bookImgOriginalFileName,
			String bookImgRenameFileName, int saleCount, String displayBook, String bookFileOriginalName, String bookFileRenamedName) {
		super(bookNo, userId, bookTitle, price, bookContent, authorCode, categoryCode, bookTagCode, bookImgOriginalFileName,
				bookImgRenameFileName, saleCount, displayBook, bookFileOriginalName, bookFileRenamedName);
		// TODO Auto-generated constructor stub
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getTagName() {
		return tagName;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}

	@Override
	public String toString() {
		return "BookExtends [userName=" + userName + ", authorName=" + authorName + ", authorContent=" + authorContent
				+ ", categoryName=" + categoryName + ", tagName=" + tagName + "]";
	}

	
}
