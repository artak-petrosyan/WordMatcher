package com.illumio.assessment.dictionary;

import java.util.HashMap;
import java.util.Map;

class TrieNode {
	
    private final Map<Character, TrieNode> childNodes = new HashMap<>();
    private boolean endOfWord;

    
    TrieNode getChildNode(char c) {
        return childNodes.get(c);
    }
    
    TrieNode addIfAbsent(char c) {
        return childNodes.computeIfAbsent(c, n -> new TrieNode());
    }
    
    public void removeChild(char c) {
		childNodes.remove(c);
	}
    
    boolean hasNoChild() {
    	return childNodes.isEmpty();
    }

    boolean isEndOfWord() {
        return endOfWord;
    }
    
    void setEndOfWord() {
        this.endOfWord = true;
    }

    void setEndOfWord(boolean endOfWord) {
        this.endOfWord = endOfWord;
    }
}

	
