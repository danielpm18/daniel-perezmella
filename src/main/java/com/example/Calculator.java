package com.example;

import java.util.List;

public class Calculator {

    public static final String EMPTY = "empty";

    /**
     * Multiplica dos enteros.
     */
    public static int multiply(int a, int b) {
        return a * b;
    }

    /**
     * Concatena dos cadenas.
     */
    public static String concat(String a, String b) {
        if (a != null && b != null) {
            return a + b;
        }
        return EMPTY;
    }

    /**
     * Suma dos valores double.
     */
    public static double sum(double a, double b) {
        return a + b;
    }

    /**
     * Aplica un descuento porcentual.
     */
    public static double discount(double amount, double percent) {
        if (percent < 0 || percent > 100) {
            throw new IllegalArgumentException("Percentage must be between 0 and 100");
        }
        return amount - (amount * percent / 100.0);
    }

    /**
     * Calcula el total de una lista de montos.
     */
    public static double calculateTotal(List<Double> amounts) {
        return amounts.stream()
                      .mapToDouble(Double::doubleValue)
                      .sum();
    }
}
