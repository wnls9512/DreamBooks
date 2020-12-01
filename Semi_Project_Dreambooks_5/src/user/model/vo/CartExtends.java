package user.model.vo;

public class CartExtends extends Cart{

	private String bookTitle;
	private String bookImgOriginalFileName;
	private String bookImgRenameFileName;
	public CartExtends() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CartExtends(String cartCode, String bookNo, String userId, String cartTotalPrice) {
		super(cartCode, bookNo, userId, cartTotalPrice);
		// TODO Auto-generated constructor stub
	}
	public String getBookTitle() {
		return bookTitle;
	}
	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}
	public String getBookImgOriginalFileName() {
		return bookImgOriginalFileName;
	}
	public void setBookImgOriginalFileName(String bookImgOriginalFileName) {
		this.bookImgOriginalFileName = bookImgOriginalFileName;
	}
	public String getBookImgRenameFileName() {
		return bookImgRenameFileName;
	}
	public void setBookImgRenameFileName(String bookImgRenameFileName) {
		this.bookImgRenameFileName = bookImgRenameFileName;
	}
	@Override
	public String toString() {
		return "CartExtends [bookTitle=" + bookTitle + ", bookImgOriginalFileName=" + bookImgOriginalFileName
				+ ", bookImgRenameFileName=" + bookImgRenameFileName + "]";
	}
	
	
	
	
	
}
