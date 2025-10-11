import org.junit.jupiter.api.Test;

import model.Article;
import model.Order;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest {

    @Test
    void testGetGrossTotal() {
        List<Article> items = Arrays.asList(
                new Article("Teclado", 2, 25.0, 10),
                new Article("Ratón", 1, 15.0, 0)
        );
        Order order = new Order("ORD001", items);
        assertEquals(65.0, order.getGrossTotal(), 0.001);
    }

    @Test
    void testGetDiscountedTotal() {
        List<Article> items = Arrays.asList(
                new Article("Teclado", 2, 25.0, 10), // 45.0
                new Article("Ratón", 1, 15.0, 0)     // 15.0
        );
        Order order = new Order("ORD002", items);
        assertEquals(60.0, order.getDiscountedTotal(), 0.001);
    }

    @Test
    void testEmptyOrder() {
        Order order = new Order("EMPTY", List.of());
        assertEquals(0.0, order.getGrossTotal());
        assertEquals(0.0, order.getDiscountedTotal());
    }

    @Test
    void testSettersAndToString() {
        Order order = new Order("O1", List.of());
        order.setId("O2");
        assertEquals("O2", order.getId());
        assertTrue(order.toString().contains("O2"));
    }

    @Test
    void testNullArticlesHandled() {
        Order order = new Order("NULL", null);
        assertNotNull(order.getArticles());
        assertEquals(0.0, order.getGrossTotal());
    }
}
