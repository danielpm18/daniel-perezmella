import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import com.example.Searcher;

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

    @Test
    void testSearchWordEmptyList() {
        List<String> list = Collections.emptyList();
        assertFalse(Searcher.searchWord("algo", list));
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

    @Test
    void testGetWordByIndexEmptyList() {
        List<String> list = Collections.emptyList();
        assertNull(Searcher.getWordByIndex(list, 0));
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

    @Test
    void testSearchByPrefixEmptyList() {
        List<String> list = Collections.emptyList();
        List<String> result = Searcher.searchByPrefix("car", list);
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

    @Test
    void testFilterByKeywordEmptyList() {
        List<String> list = Collections.emptyList();
        List<String> result = Searcher.filterByKeyword("algo", list);
        assertTrue(result.isEmpty());
    }

    // ---- searchExactPhrase ----
    @Test
    void testSearchExactPhraseFound() {
        List<String> list = Arrays.asList("hola mundo", "adiós mundo");
        assertTrue(Searcher.searchExactPhrase("hola mundo", list));
    }

    @Test
    void testSearchExactPhraseBug() {
        List<String> list = Arrays.asList("primero", "hola mundo", "otro");
        assertTrue(Searcher.searchExactPhrase("hola mundo", list));
    }

    @Test
    void testSearchExactPhraseNotFound() {
        List<String> list = Arrays.asList("uno", "dos", "tres");
        assertFalse(Searcher.searchExactPhrase("cuatro", list));
    }

    @Test
    void testSearchExactPhraseEmptyList() {
        List<String> list = Collections.emptyList();
        assertFalse(Searcher.searchExactPhrase("algo", list));
    }

    // ---- defensivo: listas nulas (para robustez extra) ----
    @Test
    void testNullListsReturnExpectedValues() {
        assertThrows(NullPointerException.class, () -> Searcher.searchWord("test", null));
        assertThrows(NullPointerException.class, () -> Searcher.searchByPrefix("a", null));
        assertThrows(NullPointerException.class, () -> Searcher.filterByKeyword("x", null));
        assertThrows(NullPointerException.class, () -> Searcher.searchExactPhrase("a", null));
    }
}
