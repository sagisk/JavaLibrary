package library_system.app;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import library_system.app.Library.GENRES;

public class BookTest {
    
    @Test
    public void variablesInitialization() {
        Book b = new Book("All the Light We Cannot See", "Anthony Doerr", GENRES.Fiction);
        assertEquals("All the Light We Cannot See", b.getTitle());
        assertEquals("Anthony Doerr", b.getAuthor());
        assertEquals(GENRES.Fiction, b.getGenre());
    }
}
