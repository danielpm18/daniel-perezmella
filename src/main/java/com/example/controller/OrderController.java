package com.example.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.model.Order;
import com.example.view.OrderView;

public class OrderController {

    private static final Logger log = LoggerFactory.getLogger(OrderController.class);

    private OrderView view;
    private List<Order> orders;

    public OrderController(OrderView view, List<Order> orders) {
        this.view = view;
        this.orders = orders;

        // Añadir listener al botón de búsqueda
        this.view.getSearchButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchOrder();
            }
        });
    }

    /**
     * Busca el pedido introducido por ID en la lista de pedidos
     */
    private void searchOrder() {
        String id = view.getSearchId().trim();

        if (id.isEmpty()) {
            view.displayMessage("Please enter an Order ID.");
            return;
        }

        log.info("Searching for order with ID: {}", id);

        Order foundOrder = null;
        for (Order o : orders) {
            if (o.getId().equalsIgnoreCase(id)) {
                foundOrder = o;
                break;
            }
        }

        if (foundOrder != null) {
            log.info("Order found: {}", foundOrder.getId());
            double gross = foundOrder.getGrossTotal();
            double discounted = foundOrder.getDiscountedTotal();

            StringBuilder details = new StringBuilder();
            details.append("Order ID: ").append(foundOrder.getId()).append("\n")
                    .append("Gross Total: ").append(gross).append("\n")
                    .append("Discounted Total: ").append(discounted).append("\n\n")
                    .append("Articles:\n");

            foundOrder.getArticles().forEach(a -> {
                details.append("- ").append(a.getName()).append(" x").append(a.getQuantity())
                        .append(" → ").append(a.getDiscountedAmount()).append("\n");
            });

            view.displayOrder(details.toString());
        } else {
            log.warn("Order not found for ID: {}", id);
            view.displayMessage("Order not found.");
        }
    }
}
