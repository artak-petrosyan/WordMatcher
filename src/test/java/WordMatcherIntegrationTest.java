import org.junit.Test;
import com.illumio.assessment.dictionary.Trie;
import com.illumio.assessment.WordMatcher;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNull;

import java.io.IOException;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class WordMatcherIntegrationTest {

	@Test
	public void testWordMatcher() throws IOException {
		ClassLoader classLoader = getClass().getClassLoader();

		Trie predefinedWords = WordMatcher
				.loadPredefinedWords(classLoader.getResource("predefined_words.txt").getFile());
		assertTrue(predefinedWords.search("name"));
		assertTrue(predefinedWords.search("detect"));
		assertTrue(predefinedWords.search("ai"));
		assertFalse(predefinedWords.search("word"));

		Map<String, Integer> matchCounts = WordMatcher
				.findMatches(classLoader.getResource("matching_text.txt").getFile(), predefinedWords);
		System.out.println("====================================================");
		System.out.println("Predefined word\t" + " Match count");
		System.out.println("----------------------------------------------------");
		for (Map.Entry<String, Integer> entry : matchCounts.entrySet()) {
			System.out.println(entry.getKey() + " \t\t\t" + entry.getValue());
		}
		System.out.println("====================================================");
		assertNull(matchCounts.get("detect"));
		assertEquals(2, matchCounts.get("name").intValue());
		assertEquals(1, matchCounts.get("ai").intValue());
	}

}
