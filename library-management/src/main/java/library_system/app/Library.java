package library_system.app;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

// Requirements:
// Easy add books
// Easy remove books
// Denote if book is booked
// Easy search book by title - group by genres
// Easy search book by author - group by authors

// To adhere all these requirements we can create a map by genres
// and for each genre as a value we can store a sorted data structure.
// However, we should be able to easily remove and add from / to this
// sorted data structure. Therefore, it should not have a fixed size.
// To comply we will use TreeMap - with the assumption that the titles
// of the books are unique. Then we can easily find the book by key (here title)
// and then check if it is available by checking its value.
// To provide an easy search by author we will create another map by authour.
// then as a value we will use another TreeMap to see if the book is there.

// genre - HashMap
// author - in SortedSet, author booksCatalog
// title
// num_of_books

// ----------
// I want user to be able to book a book. But not be able to add a book
// I want admin to be able to add and remove a book
// Want books to have number on the shelfs of the given genre rack.
//  - can't let book to have a variable with shelf number since then there is 
//      no constraints that they can't be collisions of shelf numbers for different books. 
//  - Can I hash the author to determine shelf number?

public class Library {
    enum GENRES {Fiction, Mystery, SciFi, Kids,
                 Textbooks, History, Fantasy,
                 Horror, Drama, Comedy
    };

    private static List<User> users;
    private Admin admin;
    public User user;
    private int totalNumOfBooks;
    private int totalNumOfBooksBorrowed;

    static EnumMap<GENRES, TreeMap<String, AuthorCatalog>> libraryCatalog;

    public Library() {
        admin = new Admin();
        libraryCatalog = new EnumMap<>(GENRES.class);
        users = new ArrayList<>();
        totalNumOfBooks = 0;
        totalNumOfBooksBorrowed = 0;
    }

    public static EnumMap<GENRES, TreeMap<String, AuthorCatalog>> getCatalog() {
        return libraryCatalog;
    }

    // static EnumMap<GENRES, TreeSet<AuthorCatalogg>> library_Catalog = new EnumMap<>(GENRES.class);

    private class Admin {

        public Admin () {
            users = new ArrayList<>();
        }

        public void addAuthor(String author, Book b) {
            // String genre = b.getGenre();
            // String author = b.getAuthor();
            AuthorCatalog authorCatalog = new AuthorCatalog();
            authorCatalog.add(b);
    
            TreeMap<String, AuthorCatalog> authorToCatalog = new TreeMap<>();
            authorToCatalog.put(author, authorCatalog);
            libraryCatalog.put(b.getGenre(), authorToCatalog);
        }

        public boolean addBookToLibrary(Book b) {
            GENRES genre = b.getGenre();
            String author = b.getAuthor();
            boolean res = true;
    
            if (isGenreInLibrary(genre)) {
                if (isAuthorInLibrary(author, genre)) {
                    AuthorCatalog ac = getAuthorCatalog(author, genre);
                    if (ac.isBookPresent(b))
                        ac.update(b, "return");
                    else    
                        ac.add(b);
                } else {
                    addAuthor(author, b);
                }
                totalNumOfBooks ++;
                res = true;
            } else {
                System.out.println("Our library catalog doesn't have that genre. Please, refine the genre choice.");
                res = false;
            }
            return res;
        }

        public boolean removeBookFromLibrary(Book b) {
            GENRES genre = b.getGenre();
            String author = b.getAuthor();
            boolean res = true;
    
            if (isGenreInLibrary(genre)) {
                if (isAuthorInLibrary(author, genre)) {
                    AuthorCatalog ac = getAuthorCatalog(author, genre);
                    if (ac.isBookPresent(b))
                        res = ac.remove(b);
                        totalNumOfBooks --;
                } else {
                    System.out.println("There is no such author in our library catalog.");
                    res = false;
                }
            } else {
                res = true;
            }
            return res;
        }

        public void assignBookToUser(Book book) {
            GENRES genre = book.getGenre();
            String author = book.getAuthor();

            if (isGenreInLibrary(genre) && isAuthorInLibrary(author, genre)) {
                AuthorCatalog ac = libraryCatalog.get(genre).get(author);
                ac.update(book, "addToUser");
                totalNumOfBooksBorrowed --;  
            }
        }
    }

    private boolean isGenreInLibrary(GENRES genre) {
        return libraryCatalog.containsKey(genre);
    }

    private boolean isAuthorInLibrary(String author, GENRES genre) {
        return libraryCatalog.get(genre).containsKey(author);
    }

    private AuthorCatalog getAuthorCatalog(String author, GENRES genre) {
        AuthorCatalog ac = libraryCatalog.get(genre).get(author);
        return ac;
    }

    public void returnBook(Book book) {
        int userBooksQuantity = user.getNumOfBooksBorrowed();
        if (userBooksQuantity <= 0) {
            System.out.println("No more books to return");
            return;
        }
        // admin = new Admin();
        admin.addBookToLibrary(book);
        user.setNumOfBooksBorrowed(userBooksQuantity - 1);
        user.addBook(book);
        totalNumOfBooksBorrowed --;
    }
    
    public void assignBookToUser(Book book) {
        GENRES genre = book.getGenre();
        String author = book.getAuthor();

        if (!isGenreInLibrary(genre)) {
            System.out.println("No books of the specified genre are available");
            return;
        }

        if (!isAuthorInLibrary(author, genre)) {
            System.out.println("No books of the specified author are available");
            return;
        }

        AuthorCatalog ac = getAuthorCatalog(author, genre);

        if (!ac.isBookPresent(book)) {
            System.out.println("The author's this specific book is not available");
            return;
        }
        // bookAssigned = true;
        admin.assignBookToUser(book);
        int userBooksQuantity = user.getNumOfBooksBorrowed();
        user.setNumOfBooksBorrowed(userBooksQuantity + 1);
        user.addBook(book);
        // bookAssigned = false;
    }

    public Book searchBook(Book b) {
        AuthorCatalog ac = libraryCatalog.get(b.getGenre()).get(b.getAuthor());
        if (!ac.isAuthorBook(b))
            return null;
        
        if (!ac.isBookAvailable(b))
            return null;

        return ac.getBook(b);
    }

    public void deleteUser(User user) {
        users.remove(user);
    }

    public User createUser(String firstName, String lastName) {
        User user = new User(firstName, lastName);//, numOfBooksBorrowed, booksBorrowed);
        users.add(user);
        return user;
    }

    public void generateUserReport(User u) {
        System.out.println(u);
    }

    public String generateLibraryReport() {
        String message = (
            "Library has: \n" + 
            "   - " + users.size() + " users \n" +
            "   - " + totalNumOfBooks + " books in total \n" +
            "   - From which " + totalNumOfBooksBorrowed + " are borrowed."
        );
        return message;
    }
}

// https://chat.openai.com/c/c7f4698f-aee6-41d0-a787-a0a6bacc34bb