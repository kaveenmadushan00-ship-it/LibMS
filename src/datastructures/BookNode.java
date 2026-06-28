package datastructures;

import models.Book;

public class BookNode {
    Book book;       // the data
    BookNode next;   // pointer to next node

    public BookNode(Book book) {
        this.book = book;
        this.next = null;
    }
}