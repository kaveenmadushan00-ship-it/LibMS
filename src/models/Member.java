package models;

import java.util.ArrayList;

public class Member {
    private int memberId;
    private String name;
    private String email;
    private ArrayList<String> borrowedBooks;

    public Member(int memberId, String name, String email) {
        this.memberId = memberId;
        this.name = name;
        this.email = email;
        this.borrowedBooks = new ArrayList<>();
    }

    public int getMemberId()                    { return memberId; }
    public String getName()                     { return name; }
    public String getEmail()                    { return email; }
    public ArrayList<String> getBorrowedBooks() { return borrowedBooks; }

    public void borrowBook(String title)  { borrowedBooks.add(title); }

    public boolean returnBook(String title) {
        return borrowedBooks.remove(title);
    }

    @Override
    public String toString() {
        return String.format("[ID: %d] %-20s | Email: %-25s | Books borrowed: %d",
            memberId, name, email, borrowedBooks.size());
    }
}