import static org.junit.jupiter.api.Assertions.*;


import com.illumio.assessment.dictionary.Trie;

import org.junit.Test;


import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TrieUnitTest {

	@Test
	public void emptyTrieCheck() {
		Trie trie = new Trie();

		assertTrue(trie.isEmpty());
	}

	@Test
	public void notEmptyTrieCheck() {
		Trie trie = new Trie();
		trie.add("word");

		assertFalse(trie.isEmpty());
	}
	
	@Test
	public void checkingAddWord() {
		Trie trie = new Trie();
		assertFalse(trie.search("word"));
		trie.add("word");
		assertTrue(trie.search("word"));
	}
	
	@Test
	public void checkingRemoveWord() {
		Trie trie = new Trie();
		trie.add("crossword");
		trie.add("word");
		
		assertTrue(trie.search("crossword"));
		assertTrue(trie.search("word"));
		
		trie.remove("word");
		assertTrue(trie.search("crossword"));
		assertFalse(trie.search("word"));
		
		trie.remove("crossword");
		assertFalse(trie.search("crossword"));
		assertTrue(trie.isEmpty());
	}

	@Test
	public void checkingWordsSearch() {
		Trie trie = createATrie();

		assertFalse(trie.search("illumio"));
		assertFalse(trie.search("words"));

		assertTrue(trie.search("Illumio"));
		assertTrue(trie.search("Technical"));
		assertTrue(trie.search("Assessment"));
		assertTrue(trie.search("word"));
		assertTrue(trie.search("match"));

	}

	private Trie createATrie() {
		Trie trie = new Trie();

		trie.add("Illumio");
		trie.add("Technical");
		trie.add("Assessment");
		trie.add("word");
		trie.add("match");

		return trie;
	}
}