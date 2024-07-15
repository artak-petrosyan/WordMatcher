package com.illumio.assessment.dictionary;

/**
 * Trie (also known as a prefix tree) is a specialized tree-like data structure used for 
 * efficient storage and retrieval of strings. It provides fast operations for inserting,
 * searching, and removing strings, with search time proportional to the length of the
 * string being searched.
 */
public class Trie {
	/**
     * The root node of the Trie.
     */
    private TrieNode root;

    /**
     * Constructs an empty Trie.
     */
    public Trie() {
        root = new TrieNode();
    }
    
    /**
     * Checks if the Trie is empty (contains no words).
     *
     * @return true if the Trie is empty, false otherwise.
     */
    public boolean isEmpty() {
        return root.hasNoChild();
    }

    /**
     * Adds a word to the Trie.
     *
     * @param word The word to be added.
     */
    public void add(String word) {
        TrieNode node = root;
        for (int i = 0; i < word.length(); i++){
            char c = word.charAt(i);        
            node = node.addIfAbsent(c);
        }
       node.setEndOfWord();
    }
    
    /**
     * Removes a word from the Trie.
     *
     * @param word The word to be removed.
     */
    public void remove(String word) {
        remove(word, 0, root);
    }

    /**
     * Recursive helper method for removing a word from the Trie.
     *
     * @param word       The word to be removed.
     * @param charIndex  The index of the current character being processed.
     * @param currentNode The current TrieNode being traversed.
     * @return true if the word was successfully removed, false otherwise.
     */
    private boolean remove(String word, int charIndex, TrieNode currentNode) {
    	// check if reached the end of the word
        if ( charIndex == word.length()) {
            if (!currentNode.isEndOfWord()) {
                return false;
            }
            currentNode.setEndOfWord(false);
            return currentNode.hasNoChild();
        }
        char currentChar = word.charAt(charIndex);
        TrieNode node = currentNode.getChildNode(currentChar);
        if (node == null) {
            return false;
        }
        charIndex++;

        if (remove(word, charIndex, node) && !node.isEndOfWord()) {
        	currentNode.removeChild(currentChar);
            return currentNode.hasNoChild();
        }
        return false;
    }
    
    /**
     * Searches for a word in the Trie.
     *
     * @param word The word to be searched.
     * @return true if the word is found, false otherwise.
     */
    public boolean search(String word) {
        TrieNode current = root;
        for (int i = 0; i < word.length(); i++){
            char c = word.charAt(i);  
            TrieNode node = current.getChildNode(c);
            if (node == null) {
                return false;
            }
            current = node;
        }
        return current.isEndOfWord();
    }

}