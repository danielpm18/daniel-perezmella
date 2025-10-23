package model;

import java.util.ArrayList;
import java.util.List;
import com.example.Calculator;

public class Order {

    private String id;
    private List<Article> articles;
    private Calculator calculator;

    public Order() {
        this.calculator = new Calculator();
        this.articles = new ArrayList<>();
    }

    public Order(String id, List<Article> articles) {
        this.id = id;
        this.articles = articles != null ? articles : new ArrayList<>();
        this.calculator = new Calculator();
    }

    //Getters y Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public List<Article> getArticles() { return articles; }
    public void setArticles(List<Article> articles) { this.articles = articles; }

    // Métodos principales
    public double getGrossTotal() {
        List<Double> amounts = new ArrayList<>();
        for (Article a : articles) {
            amounts.add(a.getGrossAmount());
        }
        return calculator.calculateTotal(amounts);
    }

    public double getDiscountedTotal() {
        List<Double> amounts = new ArrayList<>();
        for (Article a : articles) {
            amounts.add(a.getDiscountedAmount());
        }
        return calculator.calculateTotal(amounts);
    }

    @Override
    public String toString() {
        return "Order{id='" + id + "', articles=" + articles + "}";
    }
}
