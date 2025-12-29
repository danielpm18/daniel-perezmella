package com.example.view;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class OrderView extends JFrame {

    private JTextField searchField = new JTextField(10);
    private JButton searchButton = new JButton("Buscar");
    private JButton deleteButton = new JButton("Borrar");
    private JButton createButton = new JButton("Nuevo Pedido");
    private JButton editButton = new JButton("Editar Artículos"); // OPCIONAL
    private JTextArea resultArea = new JTextArea(15, 50);
    private JLabel idListLabel = new JLabel("IDs disponibles: ");

    public OrderView() {
        setTitle("Order Management System - Evolutivo v2.0");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // Panel Superior: Lista de IDs
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.setBorder(BorderFactory.createTitledBorder("Inventario Actual"));
        topPanel.add(idListLabel);
        add(topPanel, BorderLayout.NORTH);

        // Panel Central: Resultados
        resultArea.setEditable(false);
        resultArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane scrollPane = new JScrollPane(resultArea);
        add(scrollPane, BorderLayout.CENTER);

        // Panel Inferior: Controles
        JPanel controlPanel = new JPanel(new FlowLayout());
        controlPanel.add(new JLabel("ID Pedido:"));
        controlPanel.add(searchField);
        controlPanel.add(searchButton);
        controlPanel.add(deleteButton);
        controlPanel.add(createButton);
        controlPanel.add(editButton); // Botón para el caso de uso opcional
        add(controlPanel, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void loadAppIcon() {
        URL iconURL = getClass().getClassLoader().getResource("app.png");
        if (iconURL != null) {
            setIconImage(new ImageIcon(iconURL).getImage());
        }
    }

    // Getters para el controlador
    public String getSearchId() { return searchField.getText(); }
    public JButton getSearchButton() { return searchButton; }
    public JButton getDeleteButton() { return deleteButton; }
    public JButton getCreateButton() { return createButton; }
    public JButton getEditButton() { return editButton; }
    
    public void setIdListText(String text) { idListLabel.setText("IDs disponibles: " + text); }
    public void displayOrder(String details) { resultArea.setText(details); }
    public void displayMessage(String message) { resultArea.setText(message); }
}