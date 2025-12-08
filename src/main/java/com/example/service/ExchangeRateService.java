package com.example.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class ExchangeRateService {

    private static final Logger log = LoggerFactory.getLogger(ExchangeRateService.class);
    private final OkHttpClient httpClient = new OkHttpClient();

    // API pública y gratuita para obtener la tasa de conversión EUR -> USD
    private static final String API_URL = "https://api.exchangerate-api.com/v4/latest/EUR";
    
    /**
     * Obtiene el tipo de cambio EUR/USD de un servicio externo.
     * @return El tipo de cambio (ej. 1.08) o 1.0 (fallback) si falla la API.
     */
    public double getEurToUsdRate() {
        Request request = new Request.Builder()
                .url(API_URL)
                .build();

        try (Response response = httpClient.newCall(request).execute()) {
            
            if (!response.isSuccessful() || response.body() == null) {
                log.error("API call failed with status code: {}", response.code());
                return 1.0; 
            }

            String jsonResponse = response.body().string();
            return parseRate(jsonResponse);

        } catch (IOException e) {
            log.error("Error connecting to exchange rate API.", e);
            return 1.0; // Fallback
        }
    }

    // Método para parsear el JSON y extraer la tasa USD
    private double parseRate(String jsonResponse) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(jsonResponse);
            
            // La API devuelve la tasa bajo "rates" -> "USD"
            double rate = root.path("rates").path("USD").asDouble(0.0);
            
            if (rate > 0.0) {
                return rate;
            } else {
                log.error("Could not parse valid EUR/USD rate from response.");
                return 1.0;
            }
            
        } catch (Exception e) {
            log.error("Error parsing JSON response.", e);
            return 1.0;
        }
    }
}