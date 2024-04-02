package library_system.app;

import library_system.app.Library.GENRES;;

public class Book implements Comparable<Book> {

    private String title;
    private String authour;
    private GENRES genre;
    private int num_of_books;
    
    public Book (String t, String a, GENRES g) {
        title = t;
        authour = a;
        genre = g;
    }

    public void setTitle(String t) {
        title = t;
    }
    
    public void setAuthor(String a) {
        authour = a;
    }

    public void setGenre(GENRES g) {
        genre = g;
    }

    public void setNumOfBooks(int n) {
        num_of_books = n;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return authour;
    }

    public GENRES getGenre() {
        return genre;
    }

    public int getNumOfBooks() {
        return num_of_books;
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof Book) {
            Book otherBook = (Book) other;
            return (this.getTitle() == otherBook.getTitle() && 
                    this.getAuthor() == otherBook.getAuthor() &&
                    this.getGenre() == otherBook.getGenre());
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return "The book " + title + " by " + authour + " is in " + genre + " genre";
    }

    @Override
    public int compareTo(Book other) {
        return this.title.compareTo(other.getTitle());
    }
}
