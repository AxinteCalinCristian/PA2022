package homework.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.java.Log;


/**
 * Represents a dictionary.
 * @author Calin Axinte
 */
@Log
@Getter
@Setter
public class Dictionary {
	private List<String> dictionary = Collections.synchronizedList(new ArrayList<>());
	
	private String pathToDictFile;
	
	public Dictionary(String pathToDictFile) {
		initDictionary(pathToDictFile);
	}
	
	private void initDictionary(String pathToDictFile) {
		Set<String> words = new HashSet<>();
		Scanner file = null;
		
		try {
			file = new Scanner(new File(pathToDictFile));
			while (file.hasNextLine()) {
				String line = file.nextLine();
				
				for (String word : line.split(" ")) {
					words.add(word);
				}
			}
		} catch (FileNotFoundException e) {
			log.severe("Filed to read dictionary from file " + pathToDictFile);
			e.printStackTrace();
		}
		finally {
			if(file != null)
				file.close();
		}
		
		this.dictionary = Collections.synchronizedList(new ArrayList<String>(words));
	}
	
	/**
	 * Changes words for the dictionary to the ones at the given location.
	 * @param pathToDictFile
	 */
	public void changeDictionary(String pathToDictFile) {
		dictionary.clear();
		initDictionary(pathToDictFile);
	}
	
	/**
	 * Checks if the dictionary contains the specified word.
	 * @param word
	 * @return
	 */
	public boolean containsWord(String word) {
		if(dictionary != null && dictionary.contains(word)) {
			return true;
		}
		
		return false;
	}
}
