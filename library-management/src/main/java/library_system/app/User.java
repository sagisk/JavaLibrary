package library_system.app;

import java.util.ArrayList;
import java.util.List;

public class User {
    // Library lib = new Library();
    private String firstName;
    private String lastName;
    private int numOfBooksBorrowed;
    private List<Book> booksBorrowed;

    public User(String n, String s) {
        firstName = n;
        lastName = s;
        numOfBooksBorrowed = 0;
        booksBorrowed = new ArrayList<>();
    }

    public void setFirstName(String f) {
        firstName = f;
    }

    public void setLastName(String l) {
        lastName = l;
    }

    public void setNumOfBooksBorrowed(int n) {
        this.numOfBooksBorrowed = n;
    }

    public String getFirstName() {
        return firstName;
     }

    public String getLastName() {
        return lastName;
    }

    public int getNumOfBooksBorrowed() {
        return numOfBooksBorrowed;
    }

    public void addBook(Book book) {
        booksBorrowed.add(book);
    }

    public void returnBook(Book book) {
        numOfBooksBorrowed -= 1;
        // delete from library should be added.
        booksBorrowed.remove(book);
        // lib.returnBook(book);
    }

    public void getBookDetails(Book b) {
        System.out.println(b);
    }

    @Override
    public String toString() {
        String message = (
            "User " + firstName + " " + lastName +
            " has borrowed " + numOfBooksBorrowed +
            " books."
        );
        return message;
    }
}
