// package library_system.app;

// import java.util.ArrayList;
// import java.util.EnumMap;
// import java.util.List;
// import java.util.TreeMap;

// // import library_system.app.Library.GENRES;
// import library_system.app.Library.*;

// public class Admin {
//     private List<User> users;

//     public Admin () {
//         users = new ArrayList<>();
//     }

//     public void addAuthor(String author, Book b) {
//         // String genre = b.getGenre();
//         // String author = b.getAuthor();
//         AuthorCatalog authorCatalog = new AuthorCatalog();
//         authorCatalog.add(b);

//         TreeMap<String, AuthorCatalog> authorToCatalog = new TreeMap<>();
//         authorToCatalog.put(author, authorCatalog);
//         Library.getCatalog().put(b.getGenre(), authorToCatalog);
//     }

//     public boolean addBook(Book b) {
//         EnumMap<GENRES, TreeMap<String, AuthorCatalog>> libraryCatalog = Library.getCatalog();

//         GENRES genre = b.getGenre();
//         String author = b.getAuthor();
//         boolean res = true;

//         if (libraryCatalog.containsKey(genre)) {
//             if (libraryCatalog.get(genre).containsKey(author)) {
//                 AuthorCatalog ac = libraryCatalog.get(genre).get(author);
//                 if (ac.isBookPresent(b))
//                     ac.update(b, "return");
//                 else    
//                     ac.add(b);
//             } else {
//                 addAuthor(author, b);
//             }
//             res = true;
//         } else {
//             System.out.println("Our library catalog doesn't have that genre. Please, refine the genre choice.");
//             res = false;
//         }
//         return res;
//     }

//     private boolean removeBook(Book b) {
//         EnumMap<GENRES, TreeMap<String, AuthorCatalog>> libraryCatalog = Library.getCatalog();
//         GENRES genre = b.getGenre();
//         String author = b.getAuthor();
//         boolean res = true;

//         if (libraryCatalog.containsKey(genre)) {
//             if (libraryCatalog.get(genre).containsKey(author)) {
//                 AuthorCatalog ac = libraryCatalog.get(genre).get(author);
//                 if (ac.isBookPresent(b))
//                     res = ac.remove(b); // check if there is such a book
//             } else {
//                 System.out.println("There is no such author in our library catalog.");
//                 res = false;
//             }
//         } else {
//             res = true;
//         }
//         return res;
//     }

//     public User createUser(String firstName, String lastName, int ID) {
//         User user = new User(firstName, lastName, ID);//, numOfBooksBorrowed, booksBorrowed);
//         users.add(user);
//         return user;
//     }

//     public void deleteUser(User user) {
//         users.remove(user);
//     }

//     private void assignBookToUser(User user, Book book) {
//         book.setNumOfBooks(book.getNumOfBooks() - 1); // check that they are enough books
//         user.addBookBorrowed(book);
//     }

//     public void generateUserReport(User u) {
//         System.out.println(u);
//     }
// }
