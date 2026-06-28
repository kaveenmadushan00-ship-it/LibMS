package datastructures;

import models.Book;

public class BookLinkedList {

    private BookNode head;
    private int size;

    public BookLinkedList() {
        head = null;
        size = 0;
    }

    // ── Add a book at the end ──────────────────────────────────────
    public void addBook(Book book) {
        BookNode newNode = new BookNode(book);
        if (head == null) {
            head = newNode;
        } else {
            BookNode current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        size++;
        System.out.println("Book added: " + book.getTitle());
    }

    // ── Remove a book by ID ────────────────────────────────────────
    public boolean removeBook(int bookId) {
        if (head == null) return false;

        // If head node is the one to remove
        if (head.book.getBookId() == bookId) {
            head = head.next;
            size--;
            return true;
        }

        // Traverse to find the node before the target
        BookNode current = head;
        while (current.next != null) {
            if (current.next.book.getBookId() == bookId) {
                current.next = current.next.next;  // skip over the deleted node
                size--;
                return true;
            }
            current = current.next;
        }
        return false; // not found
    }

    // ── Search by book ID ──────────────────────────────────────────
    public Book searchById(int bookId) {
        BookNode current = head;
        while (current != null) {
            if (current.book.getBookId() == bookId) {
                return current.book;
            }
            current = current.next;
        }
        return null; // not found
    }

    // ── Linear search by title (case-insensitive) ──────────────────
    public Book searchByTitle(String title) {
        BookNode current = head;
        while (current != null) {
            if (current.book.getTitle().equalsIgnoreCase(title)) {
                return current.book;
            }
            current = current.next;
        }
        return null;
    }

    // ── Search by author (returns first match) ─────────────────────
    public Book searchByAuthor(String author) {
        BookNode current = head;
        while (current != null) {
            if (current.book.getAuthor().equalsIgnoreCase(author)) {
                return current.book;
            }
            current = current.next;
        }
        return null;
    }

    // ── Update available copies ────────────────────────────────────
    public boolean updateCopies(int bookId, int newCount) {
        Book book = searchById(bookId);
        if (book != null) {
            book.setAvailableCopies(newCount);
            return true;
        }
        return false;
    }

    // ── Display all books ──────────────────────────────────────────
    public void displayAll() {
        if (head == null) {
            System.out.println("No books in the library.");
            return;
        }
        System.out.println("\n========== ALL BOOKS ==========");
        BookNode current = head;
        int index = 1;
        while (current != null) {
            System.out.println(index + ". " + current.book);
            current = current.next;
            index++;
        }
        System.out.println("Total books: " + size);
    }

    // ── Bubble sort by title (demonstrates sorting algorithm) ──────
    public void sortByTitle() {
        if (head == null || head.next == null) return;

        boolean swapped;
        do {
            swapped = false;
            BookNode current = head;
            while (current.next != null) {
                // Compare titles alphabetically
                if (current.book.getTitle().compareToIgnoreCase(current.next.book.getTitle()) > 0) {
                    // Swap the Book objects (not the nodes)
                    Book temp = current.book;
                    current.book = current.next.book;
                    current.next.book = temp;
                    swapped = true;
                }
                current = current.next;
            }
        } while (swapped);

        System.out.println("Books sorted by title.");
    }

    // ── Getters ────────────────────────────────────────────────────
    public int getSize() { return size; }

    public boolean isEmpty() { return head == null; }
}