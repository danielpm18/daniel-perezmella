import org.junit.jupiter.api.Test;

import model.Article;

import static org.junit.jupiter.api.Assertions.*;

class ArticleTest {

    @Test
    void testGetGrossAmount() {
        Article a = new Article("Teclado", 2, 25.0, 10);
        // 2 * 25 = 50
        assertEquals(50.0, a.getGrossAmount(), 0.001);
    }

    @Test
    void testGetDiscountedAmount() {
        Article a = new Article("Ratón", 3, 10.0, 20);
        // 3 * 10 = 30 → 20% descuento → 24.0
        assertEquals(24.0, a.getDiscountedAmount(), 0.001);
    }

    @Test
    void testSettersAndToString() {
        Article a = new Article("Monitor", 1, 100.0, 5);
        a.setName("Pantalla");
        a.setQuantity(2);
        a.setPrice(50.0);
        a.setDiscount(10.0);

        assertEquals("Pantalla", a.getName());
        assertEquals(2, a.getQuantity());
        assertEquals(50.0, a.getPrice());
        assertEquals(10.0, a.getDiscount());
        assertTrue(a.toString().contains("Pantalla"));
    }

    @Test
    void testZeroDiscount() {
        Article a = new Article("Cable", 5, 2.0, 0);
        assertEquals(10.0, a.getDiscountedAmount(), 0.001);
    }
}
