package library_system.app;

import library_system.app.Book;

public class App  {
    public static void main( String[] args ) {
        Book my_book = new Book("Goodfather", "Coppalla", "crime");
        
        System.out.println(my_book);
        // System.out.println( "Hello World!" );
    }
}
