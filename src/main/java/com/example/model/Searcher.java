package com.example.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase utilitaria para realizar búsquedas en listas de Strings.
 */
public class Searcher {

    /**
     * Verifica si la frase exacta existe en la lista.
     */
    public static boolean searchExactPhrase(String phrase, List<String> list) {
        for (String item : list) {
            if (item.equals(phrase)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Verifica si la palabra existe en la lista (búsqueda simple).
     */
    public static boolean searchWord(String word, List<String> list) {
        return list.contains(word);
    }

    /**
     * Obtiene el elemento por índice de forma segura.
     */
    public static String getWordByIndex(List<String> list, int index) {
        if (index >= 0 && index < list.size()) {
            return list.get(index);
        }
        return null; // Evita IndexOutOfBounds
    }

    /**
     * Busca elementos que comiencen con un prefijo dado.
     */
    public static List<String> searchByPrefix(String prefix, List<String> list) {
        List<String> results = new ArrayList<>();
        for (String element : list) {
            if (element.startsWith(prefix)) {
                results.add(element);
            }
        }
        return results;
    }

    /**
     * Filtra todos los elementos que contienen una palabra clave dada.
     */
    public static List<String> filterByKeyword(String keyword, List<String> list) {
        List<String> results = new ArrayList<>();
        for (String element : list) {
            if (element.contains(keyword)) {
                results.add(element);
            }
        }
        return results;
    }
}
