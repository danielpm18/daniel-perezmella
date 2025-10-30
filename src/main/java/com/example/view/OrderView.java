package com.example.view;

import javax.swing.*;
import java.awt.*;

public class OrderView extends JFrame {

    private JTextField searchField = new JTextField(10);
    private JButton searchButton = new JButton("Search");
    private JTextArea resultArea = new JTextArea(15, 45);

    public OrderView() {
        setTitle("Order Management System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        add(new JLabel("Order ID:"));
        add(searchField);
        add(searchButton);

        resultArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultArea);
        add(scrollPane);

        pack();
        setLocationRelativeTo(null); // centrar ventana
        setVisible(true);
    }

    public String getSearchId() {
        return searchField.getText();
    }

    public JButton getSearchButton() {
        return searchButton;
    }

    public void displayOrder(String details) {
        resultArea.setText(details);
    }

    public void displayMessage(String message) {
        resultArea.setText(message);
    }
}
