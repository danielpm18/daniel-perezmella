package com.example.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.model.Order;
import com.example.view.OrderView;
import com.example.service.ExchangeRateService; // NUEVA DEPENDENCIA

public class OrderController {

    private static final Logger log = LoggerFactory.getLogger(OrderController.class);

    private OrderView view;
    private List<Order> orders;
    
    // NUEVO: Instancia del servicio para obtener la tasa de cambio
    private final ExchangeRateService exchangeRateService = new ExchangeRateService(); 

    public OrderController(OrderView view, List<Order> orders) {
        this.view = view;
        this.orders = orders;
        
        // Cargar el icono de la aplicación (Punto 1)
        this.view.loadAppIcon(); 

        // Añadir listener al botón de búsqueda
        this.view.getSearchButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchOrder();
            }
        });
    }

    /**
     * Busca el pedido introducido por ID en la lista de pedidos y muestra los totales en EUR y USD.
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
            
            // 1. Obtener la tasa de cambio real
            double eurToUsdRate = exchangeRateService.getEurToUsdRate();
            log.info("Fetched EUR/USD Rate: {}", String.format("%.4f", eurToUsdRate));
            
            // 2. Calcular los totales
            double grossEur = foundOrder.getGrossTotal();
            double discountedEur = foundOrder.getDiscountedTotal();
            
            double grossUsd = grossEur * eurToUsdRate;
            double discountedUsd = discountedEur * eurToUsdRate;

            // 3. Formatear los detalles para la vista
            StringBuilder details = new StringBuilder();
            details.append("--- Order Details ---\n");
            details.append("ID: ").append(foundOrder.getId()).append("\n");
            details.append("Exchange Rate (EUR/USD): ").append(String.format("%.4f", eurToUsdRate)).append("\n");
            details.append("---------------------\n");
            
            details.append("Gross Total:\n");
            details.append("  - EUR: €").append(String.format("%.2f", grossEur)).append("\n");
            details.append("  - USD: $").append(String.format("%.2f", grossUsd)).append("\n");
            
            details.append("Discounted Total:\n");
            details.append("  - EUR: €").append(String.format("%.2f", discountedEur)).append("\n");
            details.append("  - USD: $").append(String.format("%.2f", discountedUsd)).append("\n");
            
            details.append("\nArticles:\n");
            foundOrder.getArticles().forEach(a -> {
                // Mostrar el total descontado de cada artículo
                details.append("- ").append(a.getName()).append(" x").append(a.getQuantity())
                        .append(" → €").append(String.format("%.2f", a.getDiscountedAmount())).append("\n");
            });

            view.displayOrder(details.toString());
        } else {
            log.warn("Order not found for ID: {}", id);
            view.displayMessage("Order not found.");
        }
    }
}