package models;

public class Book {
    private int bookId;
    private String title;
    private String author;
    private String isbn;
    private String genre;
    private int availableCopies;

    public Book(int bookId, String title, String author, String isbn, String genre, int availableCopies) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.genre = genre;
        this.availableCopies = availableCopies;
    }

    // Getters
    public int getBookId()           { return bookId; }
    public String getTitle()         { return title; }
    public String getAuthor()        { return author; }
    public String getIsbn()          { return isbn; }
    public String getGenre()         { return genre; }
    public int getAvailableCopies()  { return availableCopies; }

    // Setters
    public void setTitle(String title)                   { this.title = title; }
    public void setAuthor(String author)                 { this.author = author; }
    public void setAvailableCopies(int availableCopies)  { this.availableCopies = availableCopies; }

    public boolean isAvailable() {
        return availableCopies > 0;
    }

    @Override
    public String toString() {
        return String.format(
            "[ID: %d] %-30s | Author: %-20s | Genre: %-15s | Copies: %d",
            bookId, title, author, genre, availableCopies
        );
    }
}