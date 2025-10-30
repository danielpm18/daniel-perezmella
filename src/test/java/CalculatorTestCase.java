import org.junit.jupiter.api.Test;

import com.example.model.Calculator;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class CalculatorTestCase {

    private final Calculator calculator = new Calculator();

    // multiply
    @Test
    void testMultiplyNormal() {
        assertEquals(6, calculator.multiply(2, 3));
    }

    @Test
    void testMultiplyByZero() {
        assertEquals(0, calculator.multiply(5, 0));
    }

    @Test
    void testMultiplyNegativeNumbers() {
        assertEquals(-15, calculator.multiply(-3, 5));
        assertEquals(9, calculator.multiply(-3, -3));
    }

    //concat
    @Test
    void testConcatNormal() {
        assertEquals("HolaMundo", calculator.concat("Hola", "Mundo"));
    }

    @Test
    void testConcatWithNull() {
        assertEquals(Calculator.EMPTY, calculator.concat("Hola", null));
        assertEquals(Calculator.EMPTY, calculator.concat(null, "Mundo"));
    }

    // ---- sum ----
    @Test
    void testSumNormal() {
        assertEquals(7.5, calculator.sum(3.5, 4.0));
    }

    @Test
    void testSumWithNegatives() {
        assertEquals(-1.5, calculator.sum(3.5, -5.0));
    }

    // ---- discount ----
    @Test
    void testDiscountValid() {
        assertEquals(80.0, calculator.discount(100.0, 20.0));
    }

    @Test
    void testDiscountZeroAndHundred() {
        assertEquals(100.0, calculator.discount(100.0, 0.0));
        assertEquals(0.0, calculator.discount(100.0, 100.0));
    }

    @Test
    void testDiscountInvalidPercent() {
        assertThrows(IllegalArgumentException.class, () -> calculator.discount(100.0, -5.0));
        assertThrows(IllegalArgumentException.class, () -> calculator.discount(100.0, 150.0));
    }

    // ---- calculateTotal ----
    @Test
    void testCalculateTotalNormal() {
        List<Double> list = Arrays.asList(10.0, 20.5, 30.0);
        assertEquals(60.5, calculator.calculateTotal(list));
    }

    @Test
    void testCalculateTotalEmptyList() {
        List<Double> list = Collections.emptyList();
        assertEquals(0.0, calculator.calculateTotal(list));
    }
}
