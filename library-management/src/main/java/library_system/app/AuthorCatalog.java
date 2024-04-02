package library_system.app;

import java.util.TreeSet;

public class AuthorCatalog {
    TreeSet<Book> books;
    
    public AuthorCatalog() {
        books = new TreeSet<>();
    }

    public void add(Book b) {
        books.add(b);
    }

    public boolean isBookInLibraryCatalog(Book b) {
        return books.contains(b);
    }

    public boolean isBookAvailable(Book b) {
        return b.getNumOfBooks() > 0;
    }

    public boolean isBookPresent(Book b) {
        boolean isLibrary = isBookInLibraryCatalog(b);
        boolean isAvailable = isBookAvailable(b);
        return isLibrary && isAvailable;
    }

    public boolean remove(Book b) {
        if (isBookPresent(b)) {
            books.remove(b);
            return true;
        }
        return false;
    }

    public boolean update(Book b, String action) {
       boolean res = remove(b);
       if (res)
            if (action == "addToUser") {
                b.setNumOfBooks(b.getNumOfBooks() - 1);
                add(b);
            } else if (action == "return") {
                b.setNumOfBooks(b.getNumOfBooks() + 1);
                add(b);
            } else {
                System.out.println("Undefined action");
            }
       return res;
    }

    public Book getBook(Book b) {
        Book foundObject = books.ceiling(b);
        return foundObject;
    }
}
