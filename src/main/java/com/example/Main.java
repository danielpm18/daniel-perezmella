package com.example;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.util.List;

public class Main {

    public static final Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {

        log.info("Starting Order Management System...");

        try {
            // Crear el ObjectMapper
            ObjectMapper mapper = new ObjectMapper();

            // Cargar el archivo JSON desde resources
            InputStream inputStream = Main.class.getResourceAsStream("/orders.json");

            // Parsear la lista de pedidos
            List<Order> orders = mapper.readValue(inputStream, new TypeReference<List<Order>>() {});

            // Log de cada pedido cargado
            for (Order order : orders) {
                log.debug("Loaded order: {}", order.getId());
            }

            log.info("Successfully loaded {} orders from JSON.", orders.size());

        } catch (Exception e) {
            log.error("Error loading orders.json", e);
        }
    }
}
