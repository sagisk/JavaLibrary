package library_system.app;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import library_system.app.Library.GENRES;

public class AuthorCatalogTest {
    
    @Test
    public void checkOrder() {
        Book a = new Book("A", "A", GENRES.Comedy);
        Book b = new Book("B", "A", GENRES.Comedy);
        Book c = new Book("C", "A", GENRES.Comedy);

        AuthorCatalog ac = new AuthorCatalog();
        ac.add(b);
        ac.add(a);
        ac.add(c);

        assertEquals(3, ac.books.size());
        assertTrue(ac.books.contains(a));
        assertTrue(ac.books.contains(b));
        assertTrue(ac.books.contains(c));

        Book[] source_array = new Book[]{a, b, c};
        Book[] target_array = ac.books.toArray(new Book[0]);

        assertArrayEquals(source_array, target_array);
    }
}
