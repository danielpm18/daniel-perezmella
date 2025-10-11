package model;

import java.util.ArrayList;
import java.util.List;

import com.example.Calculator;

public class Order {

    private String id;
    private List<Article> articles;

    public Order(String id, List<Article> articles) {
        this.id = id;
        this.articles = articles != null ? articles : new ArrayList<>();
    }

    // --- Getters y Setters ---
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public List<Article> getArticles() { return articles; }
    public void setArticles(List<Article> articles) { this.articles = articles; }

    // --- Métodos principales ---
    public double getGrossTotal() {
        List<Double> amounts = new ArrayList<>();
        for (Article a : articles) {
            amounts.add(a.getGrossAmount());
        }
        return Calculator.calculateTotal(amounts);
    }

    public double getDiscountedTotal() {
        List<Double> amounts = new ArrayList<>();
        for (Article a : articles) {
            amounts.add(a.getDiscountedAmount());
        }
        return Calculator.calculateTotal(amounts);
    }

    @Override
    public String toString() {
        return "Order{id='" + id + "', articles=" + articles + "}";
    }
}
