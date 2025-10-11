package model;

import com.example.Calculator;

public class Article {

    private String name;
    private int quantity;
    private double price;
    private double discount;

    public Article(String name, int quantity, double price, double discount) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.discount = discount;
    }

    // --- Getters y Setters ---
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public double getDiscount() { return discount; }
    public void setDiscount(double discount) { this.discount = discount; }

    // --- Métodos principales ---
    public double getGrossAmount() {
        return Calculator.multiply(quantity, (int) price);
    }

    public double getDiscountedAmount() {
        double gross = getGrossAmount();
        return Calculator.discount(gross, discount);
    }

    @Override
    public String toString() {
        return String.format("%s x%d (%.2f€ - %.1f%% desc)", name, quantity, price, discount);
    }
}
