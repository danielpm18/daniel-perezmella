package model;

import com.example.Calculator;

public class Article {

    private String name;
    private int quantity;
    private double unitPrice;
    private double discount;

    private Calculator calculator;

    public Article() {
        this.calculator = new Calculator();
    }

    public Article(String name, int quantity, double unitPrice, double discount) {
        this.name = name;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.discount = discount;
        this.calculator = new Calculator();
    }

    //Getters y Setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public double getUnitPrice() { return unitPrice; }
    public void setUnitPrice(double unitPrice) { this.unitPrice = unitPrice; }

    public double getDiscount() { return discount; }
    public void setDiscount(double discount) { this.discount = discount; }

    //Métodos principales
    public double getGrossAmount() {
        return calculator.multiply(quantity, (int) unitPrice);
    }

    public double getDiscountedAmount() {
        double gross = getGrossAmount();
        return calculator.discount(gross, discount);
    }

    @Override
    public String toString() {
        return String.format("%s x%d (%.2f€ - %.1f%% desc)", name, quantity, unitPrice, discount);
    }
}
