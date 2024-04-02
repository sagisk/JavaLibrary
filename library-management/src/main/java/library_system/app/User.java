package library_system.app;

import java.util.Random;
import java.util.List;

public class User {
    private String firstName;
    private String lastName;
    private int ID;
    private Random rdn;
    private int numOfBooksBorrowed;
    private List<Book> booksBorrowed;

    public User() {
        rdn = new Random();
    }

    public User(String n, String s) {
        firstName = n;
        lastName = s;
        ID = new Random().nextInt(100_000_000);
        numOfBooksBorrowed = 0;
    }

    public User(String n, String s, int id) {
        firstName = n;
        lastName = s;
        ID = id;
        numOfBooksBorrowed = 0;
    }

    public void setFirstName(String f) {
        firstName = f;
    }

    public void setLastName(String l) {
        lastName = l;
    }

    public String getFirstName() {
        return firstName;
     }

    public String getLastName() {
        return lastName;
    }

    public int getID() {
        return ID;
    }

    public void addBookBorrowed(Book book) {
        numOfBooksBorrowed += 1;
        booksBorrowed.add(book);
    }

    public boolean requestBookBorrow(Book b) {
        Library lib = new Library();
        boolean book_present = lib.searchBook(b);
        return book_present;
    }

    public boolean returnBook(Book book) {
        numOfBooksBorrowed -= 1;
        // delete from library should be added.
        booksBorrowed.remove(book);
        Admin a = new Admin();
        a.addBook(book);
    }

    public void getBookDetails(Book b) {
        System.out.println(b);
    }
}
