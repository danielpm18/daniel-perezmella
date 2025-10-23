package com.example;

import java.util.List;

public class Calculator {

    public static final String EMPTY = "empty";

    // Ejemplo de configuración futura
    private String currency;
    private int roundingPrecision;

    // Constructor con configuración opcional
    public Calculator() {
        this.currency = "USD";        // Valor por defecto
        this.roundingPrecision = 2;   // Decimales por defecto
    }

    public Calculator(String currency, int roundingPrecision) {
        this.currency = currency;
        this.roundingPrecision = roundingPrecision;
    }

    // Getters y setters para configuración
    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public int getRoundingPrecision() {
        return roundingPrecision;
    }

    public void setRoundingPrecision(int roundingPrecision) {
        this.roundingPrecision = roundingPrecision;
    }

    /**
     * Multiplica dos enteros.
     */
    public int multiply(int a, int b) {
        return a * b;
    }

    /**
     * Concatena dos cadenas.
     */
    public String concat(String a, String b) {
        if (a != null && b != null) {
            return a + b;
        }
        return EMPTY;
    }

    /**
     * Suma dos valores double.
     */
    public double sum(double a, double b) {
        return a + b;
    }

    /**
     * Aplica un descuento porcentual.
     */
    public double discount(double amount, double percent) {
        if (percent < 0 || percent > 100) {
            throw new IllegalArgumentException("Percentage must be between 0 and 100");
        }
        double discounted = amount - (amount * percent / 100.0);
        return round(discounted);
    }

    /**
     * Calcula el total de una lista de montos.
     */
    public double calculateTotal(List<Double> amounts) {
        double total = amounts.stream()
                              .mapToDouble(Double::doubleValue)
                              .sum();
        return round(total);
    }

    /**
     * Método privado para redondear según la configuración.
     */
    private double round(double value) {
        double scale = Math.pow(10, roundingPrecision);
        return Math.round(value * scale) / scale;
    }
}
