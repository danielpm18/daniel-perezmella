

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import com.daniel.Searcher;

class SearcherTestCase {

    // ---- searchWord ----
    @Test
    void testSearchWordExists() {
        List<String> list = Arrays.asList("casa", "perro", "gato");
        assertTrue(Searcher.searchWord("perro", list));
    }

    @Test
    void testSearchWordNotExists() {
        List<String> list = Arrays.asList("casa", "perro", "gato");
        assertFalse(Searcher.searchWord("ratón", list));
    }

    // ---- getWordByIndex ----
    @Test
    void testGetWordByValidIndex() {
        List<String> list = Arrays.asList("casa", "perro", "gato");
        assertEquals("perro", Searcher.getWordByIndex(list, 1));
    }

    @Test
    void testGetWordByInvalidIndex() {
        List<String> list = Arrays.asList("casa", "perro", "gato");
        assertNull(Searcher.getWordByIndex(list, -1));
        assertNull(Searcher.getWordByIndex(list, 10));
    }

    // ---- searchByPrefix ----
    @Test
    void testSearchByPrefixMatches() {
        List<String> list = Arrays.asList("casa", "carro", "perro", "carpeta");
        List<String> result = Searcher.searchByPrefix("car", list);
        assertTrue(result.contains("carro"));
        assertTrue(result.contains("carpeta"));
        assertFalse(result.contains("perro"));
    }

    @Test
    void testSearchByPrefixNoMatches() {
        List<String> list = Arrays.asList("casa", "carro", "perro");
        List<String> result = Searcher.searchByPrefix("zzz", list);
        assertTrue(result.isEmpty());
    }

    // ---- filterByKeyword ----
    @Test
    void testFilterByKeywordExists() {
        List<String> list = Arrays.asList("me gusta el perro", "el gato duerme", "perro grande");
        List<String> result = Searcher.filterByKeyword("perro", list);
        assertEquals(2, result.size());
    }

    @Test
    void testFilterByKeywordNotExists() {
        List<String> list = Arrays.asList("me gusta el perro", "el gato duerme", "perro grande");
        List<String> result = Searcher.filterByKeyword("ratón", list);
        assertTrue(result.isEmpty());
    }

    // ---- (Avanzado) searchExactPhrase ----
    @Test
    void testSearchExactPhraseFound() {
        List<String> list = Arrays.asList("hola mundo", "adiós mundo");
        assertTrue(Searcher.searchExactPhrase("hola mundo", list));
    }

    @Test
    void testSearchExactPhraseBug() {
        List<String> list = Arrays.asList("primero", "hola mundo", "otro");
        // si la implementación solo mira el primero, este test fallaría
        assertTrue(Searcher.searchExactPhrase("hola mundo", list));
    }
}
