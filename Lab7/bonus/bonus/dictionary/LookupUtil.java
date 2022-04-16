package bonus.dictionary;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LookupUtil {
	private Dictionary dictionary;
	private Trie trie = new Trie();
	
	public LookupUtil(Dictionary dictionary) {
		this.dictionary = dictionary;
		trie.constructPrefixTree(dictionary);
	}
	
	/**
	 * Changes the current dictionary to this.
	 * @param dictionary
	 */
	public void changeDict(Dictionary dictionary) {
		this.dictionary = dictionary;
		trie.constructPrefixTree(dictionary);
	}
	
	/**
	 * Performs a search for words containing the given prefix, using the specified method.
	 * @param prefix
	 * @param type
	 * @return the list of words.
	 */
	public List<String> prefixSearch(String prefix, LookupType type) {
		Set<String> words = null;
		long start = System.nanoTime();
		
		if(type == LookupType.Multithreaded) {
			words = new HashSet<>(multithreadedSearch(prefix));
		}
		else if(type == LookupType.PrefixTree) {
			words = new HashSet<>(prefixTreeSearch(prefix));
		}
		
		System.out.println("Type: " + type + ", Time(ns): " + (System.nanoTime() - start));
		return new ArrayList<>(words);
	}
	
	private List<String> multithreadedSearch(String prefix) {
		List<String> words = Collections.synchronizedList(new ArrayList<>());
		
		this.dictionary.getDictionary().parallelStream()
		.filter(word -> word.startsWith(prefix))
		.forEach(word -> words.add(word));
		
		return words;
	}
	
	private  List<String> prefixTreeSearch(String prefix) {
		List<String> words = new ArrayList<>(trie.getWordsList(prefix));
		return words;
	}
}
