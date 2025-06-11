
public class Book {
	private String title;
	private String author;
	private String isbn;

	public Book() {

	}

	public Book(String title, String author, String isbn) {
		this.title = title;
		this.author = author;
		this.isbn = isbn;
	}

	public boolean matches(String searchTerm) {
		return title.toLowerCase().contains(searchTerm) ||
				author.toLowerCase().contains(searchTerm) ||
				isbn.toLowerCase().contains(searchTerm);
	}
	public String toFileString() {
		return title + "|" + author + "|" + isbn;
	}

	@Override
	public String toString() {
		return "本: " + title +
				"\n著作: " + author +
				"\nISBN: " + isbn +
				"\n-------------------";
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

}
