import datastructures.BookLinkedList;
import datastructures.BorrowQueue;
import datastructures.TransactionStack;
import models.Book;
import models.Member;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    // ── Data structures ────────────────────────────────────────────
    static BookLinkedList bookList       = new BookLinkedList();
    static ArrayList<Member> members     = new ArrayList<>();
    static BorrowQueue borrowQueue       = new BorrowQueue();
    static TransactionStack txHistory    = new TransactionStack();
    static Scanner scanner               = new Scanner(System.in);
    static int nextBookId                = 1;
    static int nextMemberId              = 1;

    public static void main(String[] args) {
        loadSampleData();
        int choice;
        do {
            printMainMenu();
            choice = readInt("Enter choice: ");
            switch (choice) {
                case 1 -> bookMenu();
                case 2 -> memberMenu();
                case 3 -> borrowReturnMenu();
                case 4 -> showTransactionHistory();
                case 5 -> sortBooks();
                case 0 -> System.out.println("\nGoodbye!");
                default -> System.out.println("Invalid choice. Try again.");
            }
        } while (choice != 0);
        scanner.close();
    }

    // ══════════════════════════════════════════════════════════════
    //  MENUS
    // ══════════════════════════════════════════════════════════════

    static void printMainMenu() {
        System.out.println("\n╔══════════════════════════════════════╗");
        System.out.println("║   LIBRARY MANAGEMENT SYSTEM          ║");
        System.out.println("╠══════════════════════════════════════╣");
        System.out.println("║  1. Book Management                  ║");
        System.out.println("║  2. Member Management                ║");
        System.out.println("║  3. Borrow / Return                  ║");
        System.out.println("║  4. Transaction History  [Stack]     ║");
        System.out.println("║  5. Sort Books                       ║");
        System.out.println("║  0. Exit                             ║");
        System.out.println("╚══════════════════════════════════════╝");
    }

    // ── Book sub-menu ──────────────────────────────────────────────
    static void bookMenu() {
        int choice;
        do {
            System.out.println("\n── Book Management ──");
            System.out.println("  1. Add book");
            System.out.println("  2. Remove book");
            System.out.println("  3. View all books");
            System.out.println("  4. Search by title");
            System.out.println("  5. Search by author");
            System.out.println("  6. Search by ID");
            System.out.println("  0. Back");
            choice = readInt("Choice: ");
            switch (choice) {
                case 1 -> addBook();
                case 2 -> removeBook();
                case 3 -> bookList.displayAll();
                case 4 -> searchByTitle();
                case 5 -> searchByAuthor();
                case 6 -> searchById();
                case 0 -> {}
                default -> System.out.println("Invalid choice.");
            }
        } while (choice != 0);
    }

    // ── Member sub-menu ────────────────────────────────────────────
    static void memberMenu() {
        int choice;
        do {
            System.out.println("\n── Member Management ──");
            System.out.println("  1. Register member");
            System.out.println("  2. View all members");
            System.out.println("  3. Remove member");
            System.out.println("  0. Back");
            choice = readInt("Choice: ");
            switch (choice) {
                case 1 -> registerMember();
                case 2 -> displayMembers();
                case 3 -> removeMember();
                case 0 -> {}
                default -> System.out.println("Invalid choice.");
            }
        } while (choice != 0);
    }

    // ── Borrow/Return sub-menu ─────────────────────────────────────
    static void borrowReturnMenu() {
        int choice;
        do {
            System.out.println("\n── Borrow / Return ──");
            System.out.println("  1. Borrow a book");
            System.out.println("  2. Return a book");
            System.out.println("  3. View borrow waitlist  [Queue]");
            System.out.println("  0. Back");
            choice = readInt("Choice: ");
            switch (choice) {
                case 1 -> borrowBook();
                case 2 -> returnBook();
                case 3 -> borrowQueue.displayQueue();
                case 0 -> {}
                default -> System.out.println("Invalid choice.");
            }
        } while (choice != 0);
    }

    // ══════════════════════════════════════════════════════════════
    //  BOOK OPERATIONS
    // ══════════════════════════════════════════════════════════════

    static void addBook() {
        System.out.println("\n── Add New Book ──");
        String title  = readString("Title: ");
        String author = readString("Author: ");
        String isbn   = readString("ISBN: ");
        String genre  = readString("Genre: ");
        int copies    = readInt("Available copies: ");

        Book book = new Book(nextBookId++, title, author, isbn, genre, copies);
        bookList.addBook(book);
        System.out.println("Book added with ID: " + book.getBookId());
    }

    static void removeBook() {
        bookList.displayAll();
        int id = readInt("Enter Book ID to remove: ");
        if (bookList.removeBook(id)) {
            System.out.println("Book removed successfully.");
        } else {
            System.out.println("Book not found.");
        }
    }

    static void searchByTitle() {
        String title = readString("Enter title: ");
        Book book = bookList.searchByTitle(title);
        System.out.println(book != null ? "Found: " + book : "Book not found.");
    }

    static void searchByAuthor() {
        String author = readString("Enter author: ");
        Book book = bookList.searchByAuthor(author);
        System.out.println(book != null ? "Found: " + book : "No book by that author found.");
    }

    static void searchById() {
        int id = readInt("Enter Book ID: ");
        Book book = bookList.searchById(id);
        System.out.println(book != null ? "Found: " + book : "Book not found.");
    }

    static void sortBooks() {
        bookList.sortByTitle();
        bookList.displayAll();
    }

    // ══════════════════════════════════════════════════════════════
    //  MEMBER OPERATIONS
    // ══════════════════════════════════════════════════════════════

    static void registerMember() {
        System.out.println("\n── Register New Member ──");
        String name  = readString("Name: ");
        String email = readString("Email: ");
        Member member = new Member(nextMemberId++, name, email);
        members.add(member);
        System.out.println("Member registered with ID: " + member.getMemberId());
    }

    static void displayMembers() {
        if (members.isEmpty()) {
            System.out.println("No members registered.");
            return;
        }
        System.out.println("\n========== MEMBERS ==========");
        for (Member m : members) {
            System.out.println(m);
        }
    }

    static void removeMember() {
        displayMembers();
        int id = readInt("Enter Member ID to remove: ");
        boolean removed = members.removeIf(m -> m.getMemberId() == id);
        System.out.println(removed ? "Member removed." : "Member not found.");
    }

    // ══════════════════════════════════════════════════════════════
    //  BORROW / RETURN OPERATIONS
    // ══════════════════════════════════════════════════════════════

    static void borrowBook() {
        displayMembers();
        int memberId = readInt("Enter Member ID: ");
        Member member = findMember(memberId);
        if (member == null) { System.out.println("Member not found."); return; }

        bookList.displayAll();
        int bookId = readInt("Enter Book ID to borrow: ");
        Book book = bookList.searchById(bookId);
        if (book == null) { System.out.println("Book not found."); return; }

        if (book.isAvailable()) {
            book.setAvailableCopies(book.getAvailableCopies() - 1);
            member.borrowBook(book.getTitle());
            // Push to transaction history Stack
            txHistory.push("BORROW | Member: " + member.getName()
                + " | Book: " + book.getTitle());
            System.out.println("Success! \"" + book.getTitle()
                + "\" borrowed by " + member.getName() + ".");
        } else {
            // Book unavailable — add to waitlist Queue
            borrowQueue.enqueue(member.getName() + " waiting for \"" + book.getTitle() + "\"");
            System.out.println("No copies available. Added to waitlist.");
        }
    }

    static void returnBook() {
        displayMembers();
        int memberId = readInt("Enter Member ID: ");
        Member member = findMember(memberId);
        if (member == null) { System.out.println("Member not found."); return; }

        System.out.println("Books borrowed by " + member.getName() + ": "
            + member.getBorrowedBooks());
        String title = readString("Enter book title to return: ");

        if (member.returnBook(title)) {
            Book book = bookList.searchByTitle(title);
            if (book != null) {
                book.setAvailableCopies(book.getAvailableCopies() + 1);
            }
            // Push to transaction history Stack
            txHistory.push("RETURN | Member: " + member.getName()
                + " | Book: " + title);
            System.out.println("\"" + title + "\" returned. Thank you!");

            // Notify next person in waitlist Queue
            String next = borrowQueue.dequeue();
            if (next != null) {
                System.out.println("Notifying next in waitlist: " + next);
            }
        } else {
            System.out.println("This book is not in the member's borrowed list.");
        }
    }

    // ══════════════════════════════════════════════════════════════
    //  TRANSACTION HISTORY  (Stack)
    // ══════════════════════════════════════════════════════════════

    static void showTransactionHistory() {
        txHistory.displayAll();
    }

    // ══════════════════════════════════════════════════════════════
    //  HELPERS
    // ══════════════════════════════════════════════════════════════

    static Member findMember(int id) {
        for (Member m : members) {
            if (m.getMemberId() == id) return m;
        }
        return null;
    }

    static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }

    static String readString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    // ── Sample data so the app isn't empty on first run ───────────
    static void loadSampleData() {
        bookList.addBook(new Book(nextBookId++, "Clean Code",
            "Robert Martin", "978-0132350884", "Programming", 3));
        bookList.addBook(new Book(nextBookId++, "Introduction to Algorithms",
            "Thomas Cormen", "978-0262033848", "CS Theory", 2));
        bookList.addBook(new Book(nextBookId++, "The Pragmatic Programmer",
            "David Thomas", "978-0135957059", "Programming", 1));

        members.add(new Member(nextMemberId++, "Amal Perera", "amal@email.com"));
        members.add(new Member(nextMemberId++, "Nimasha Silva", "nimasha@email.com"));

        System.out.println("Sample data loaded.\n");
    }
}