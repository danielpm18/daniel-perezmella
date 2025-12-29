package com.example.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.example.model.Order;
import com.example.model.Article;
import com.example.view.OrderView;
import com.example.service.ExchangeRateService;
import com.fasterxml.jackson.databind.ObjectMapper;

public class OrderController {

    private static final Logger log = LoggerFactory.getLogger(OrderController.class);
    private OrderView view;
    private List<Order> orders;
    private final ExchangeRateService exchangeRateService = new ExchangeRateService();
    private ObjectMapper mapper = new ObjectMapper();

    public OrderController(OrderView view, List<Order> orders) {
        this.view = view;
        this.orders = orders;
        this.view.loadAppIcon();
        
        updateIdList(); 

        // Registro de Listeners
        this.view.getSearchButton().addActionListener(e -> searchOrder());
        this.view.getDeleteButton().addActionListener(e -> deleteOrder());
        this.view.getCreateButton().addActionListener(e -> showCreateOrderForm());
        this.view.getEditButton().addActionListener(e -> editOrderArticles());
    }

    private void updateIdList() {
        StringBuilder sb = new StringBuilder();
        for (Order o : orders) {
            sb.append("[").append(o.getId()).append("] ");
        }
        view.setIdListText(sb.toString());
    }

    private void saveToFile() {
        try {
            // Se guarda en la ruta física del proyecto para persistencia real
            File file = new File("src/main/resources/orders.json");
            mapper.writerWithDefaultPrettyPrinter().writeValue(file, orders);
            log.info("Archivo JSON actualizado correctamente.");
        } catch (Exception e) {
            log.error("Error al persistir cambios", e);
            JOptionPane.showMessageDialog(view, "Error crítico al guardar en JSON.");
        }
    }

    private void searchOrder() {
        String id = view.getSearchId().trim();
        if (id.isEmpty()) return;

        Order found = orders.stream()
                .filter(o -> o.getId().equalsIgnoreCase(id))
                .findFirst().orElse(null);

        if (found != null) {
            double rate = exchangeRateService.getEurToUsdRate();
            StringBuilder ds = new StringBuilder("--- DETALLES DEL PEDIDO ---\n");
            ds.append("ID: ").append(found.getId()).append("\n");
            ds.append("Total EUR: €").append(String.format("%.2f", found.getDiscountedTotal())).append("\n");
            ds.append("Total USD: $").append(String.format("%.2f", found.getDiscountedTotal() * rate)).append("\n\n");
            ds.append("ARTÍCULOS:\n");
            for (Article a : found.getArticles()) {
                ds.append("- ").append(a.toString()).append("\n");
            }
            view.displayOrder(ds.toString());
        } else {
            view.displayMessage("Pedido no encontrado.");
        }
    }

    private void deleteOrder() {
        String id = view.getSearchId().trim();
        Order found = orders.stream().filter(o -> o.getId().equalsIgnoreCase(id)).findFirst().orElse(null);

        if (found != null) {
            orders.remove(found);
            saveToFile();
            updateIdList();
            view.displayMessage("Pedido " + id + " eliminado.");
        } else {
            view.displayMessage("ID no encontrado para borrar.");
        }
    }

    private void showCreateOrderForm() {
        String newId = JOptionPane.showInputDialog(view, "ID del nuevo pedido:");
        if (newId == null || newId.trim().isEmpty()) return;

        if (orders.stream().anyMatch(o -> o.getId().equalsIgnoreCase(newId))) {
            JOptionPane.showMessageDialog(view, "Error: El ID ya existe.");
            return;
        }

        String artName = JOptionPane.showInputDialog(view, "Nombre del producto:");
        Order newOrder = new Order(newId, new ArrayList<>());
        newOrder.getArticles().add(new Article(artName, 1, 50.0, 0.0));

        orders.add(newOrder);
        saveToFile();
        updateIdList();
        view.displayMessage("Pedido creado.");
    }

    // --- CASO DE USO OPCIONAL: EDITAR ---
    private void editOrderArticles() {
        String id = view.getSearchId().trim();
        Order found = orders.stream().filter(o -> o.getId().equalsIgnoreCase(id)).findFirst().orElse(null);

        if (found != null && !found.getArticles().isEmpty()) {
            // Por simplicidad, editamos el primer artículo encontrado en el pedido
            Article a = found.getArticles().get(0);
            
            String nQty = JOptionPane.showInputDialog(view, "Nueva cantidad para " + a.getName() + ":", a.getQuantity());
            String nDisc = JOptionPane.showInputDialog(view, "Nuevo descuento (%) para " + a.getName() + ":", a.getDiscount());
            
            if (nQty != null && nDisc != null) {
                try {
                    a.setQuantity(Integer.parseInt(nQty));
                    a.setDiscount(Double.parseDouble(nDisc));
                    saveToFile();
                    searchOrder(); // Refrescar la vista con los nuevos totales
                    JOptionPane.showMessageDialog(view, "Pedido actualizado con éxito.");
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(view, "Error: Introduce números válidos.");
                }
            }
        } else {
            view.displayMessage("Busca un pedido válido para editar sus artículos.");
        }
    }
}