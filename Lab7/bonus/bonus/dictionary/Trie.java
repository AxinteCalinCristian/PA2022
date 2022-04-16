package bonus.dictionary;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Represents a Trie, an information retrieval data structure based on a provided dictionary.
 * @author Calin Axinte
 */
public class Trie {
	
	/**
	 * Represents a node in the Trie, which helps store the required information.
	 * @author Calin Axinte
	 */
	private class TrieNode {
        Map<Character, TrieNode> children;
        char c;
        boolean isWord;
 
        public TrieNode(char c) {
            this.c = c;
            children = new HashMap<>();
        }
 
        public TrieNode() {
            children = new HashMap<>();
        }
        
        /**
         * Inserts a word in the Trie.
         * @param word
         */
        public void insert(String word) {
            if (word == null || word.isEmpty())
                return;
            char firstChar = word.charAt(0);
            TrieNode child = children.get(firstChar);
            if (child == null) {
                child = new TrieNode(firstChar);
                children.put(firstChar, child);
            }
 
            if (word.length() > 1)
                child.insert(word.substring(1));
            else
                child.isWord = true;
        }
 
    }
 
    private TrieNode root;
    
    /**
     * Recursive function that searches for matches on the current string buffer
     * @param root
     * @param list
     * @param curr
     */
    private void getWordsHelper(TrieNode root, List<String> list, StringBuffer curr) {
        if (root.isWord) {
            list.add(curr.toString());
        }
 
        if (root.children == null || root.children.isEmpty())
            return;
 
        for (TrieNode child : root.children.values()) {
        	getWordsHelper(child, list, curr.append(child.c));
            curr.setLength(curr.length() - 1);
        }
    }
    
    /**
     * Constructs the Trie (prefix tree) from the given dictionary
     * @param dictionary
     */
	public void constructPrefixTree(Dictionary dictionary) {
		root = new TrieNode();
		for(String s : dictionary.getDictionary()) {
			root.insert(s);
		}
	}
	
	/**
	 * Returns all words that match the given prefix.
	 * @param prefix
	 * @return the list of words.
	 */
	public List<String> getWordsList(String prefix) {
		List<String> words = new ArrayList<>();
        TrieNode lastNode = root;
        StringBuffer curr = new StringBuffer();
        for (char c : prefix.toCharArray()) {
            lastNode = lastNode.children.get(c);
            if (lastNode == null)
                return words;
            curr.append(c);
        }
        getWordsHelper(lastNode, words, curr);
        return words;
	}
}
