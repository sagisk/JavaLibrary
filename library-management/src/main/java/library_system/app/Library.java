package library_system.app;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.SortedSet;
import java.util.TreeMap;

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

    static EnumMap<GENRES, TreeMap<String, AuthorCatalog>> libraryCatalog = new EnumMap<>(GENRES.class);

    public static EnumMap<GENRES, TreeMap<String, AuthorCatalog>> getCatalog() {
        return libraryCatalog;
    }


    private boolean removeBook(Book b) {
        GENRES genre = b.getGenre();
        String author = b.getAuthor();
        boolean res = true;

        if (libraryCatalog.containsKey(genre)) {
            if (libraryCatalog.get(genre).containsKey(author)) {
                AuthorCatalog ac = libraryCatalog.get(genre).get(author);
                res = ac.remove(b); // check if there is such a book
            } else {
                System.out.println("There is no such author in our library catalog.");
                res = false;
            }
        } else {
            res = true;
        }
        return res;
    }

    public boolean bookBook(Book b) {
        return removeBook(b);
    }

    public boolean searchBook(Book b) {
        AuthorCatalog ac = libraryCatalog.get(b.getGenre()).get(b.getAuthor());
        if (!ac.isBookInLibraryCatalog(b))
            return false;
        
        if (!ac.isBookAvailable(b))
            return false;

        // Book library_book = ac.getBook(b);
        // if (b.equals(library_book))
        //     return true;
        return true;
    }
}
