package com.example.view;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

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

        // Se llama desde el constructor del controlador
        // loadAppIcon(); 
        
        pack();
        setLocationRelativeTo(null); // centrar ventana
        setVisible(true);
    }
    
    /**
     * Carga y establece el icono 'app.png' para la ventana (Punto 1).
     */
    public void loadAppIcon() {
        // Carga el recurso desde la carpeta 'resources'
        URL iconURL = getClass().getClassLoader().getResource("app.png");
        if (iconURL != null) {
            ImageIcon icon = new ImageIcon(iconURL);
            setIconImage(icon.getImage());
        } else {
            System.err.println("App icon 'app.png' not found in resources folder.");
        }
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