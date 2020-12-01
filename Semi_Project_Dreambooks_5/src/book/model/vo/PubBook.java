package book.model.vo;

public class PubBook extends Book{

	private	int boardNo;
	private String bookNo;
	private String bookTitle;
	private String bookAuthor;
	private String publisher;
	
	public PubBook() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PubBook(int boardNo, String bookNo, String bookTitle, String bookAuthor, String publisher) {
		super();
		this.boardNo = boardNo;
		this.bookNo = bookNo;
		this.bookTitle = bookTitle;
		this.bookAuthor = bookAuthor;
		this.publisher = publisher;
	}

	
	
	public int getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}

	public String getBookNo() {
		return bookNo;
	}

	public void setBookNo(String bookNo) {
		this.bookNo = bookNo;
	}

	public String getBookTitle() {
		return bookTitle;
	}

	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}

	public String getBookAuthor() {
		return bookAuthor;
	}

	public void setBookAuthor(String bookAuthor) {
		this.bookAuthor = bookAuthor;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	
	
	
}
