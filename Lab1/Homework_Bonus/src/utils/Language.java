package utils;

import java.util.ArrayList;
import java.util.Arrays;

public class Language 
{
	private Alphabet alphabet;
	
	private int size;
	private int word_length;
	private ArrayList<String> words;
	
	/**
	 * Takes the input arguments, in order: size, word_length, individual letters of an alphabet
	 * @param args
	 */
	
	public Language(String[] args)
	{
		this.alphabet = new Alphabet();
		this.words = new ArrayList<String>();
		
		this.size = Integer.parseInt(args[0]);
		this.word_length = Integer.parseInt(args[1]);
		
		alphabet.createAlphabet(Arrays.copyOfRange(args, 2, args.length));
		populateLanguage();
	}
	
	/**
	 * Generates size number of random words based on the current language
	 */
	
	private void populateLanguage()
	{
		for(int i = 0; i < size; i++)
		{
			String current_word = alphabet.getRandomWord(word_length);
			
			words.add(current_word);
		}
	}
	
	/**
	 * @param index
	 * @return the word in the current language at the given index
	 */
	
	public String getWordAt(int index)
	{
		return words.get(index);
	}
	
	/**
	 * @return the entire list of words in the language
	 */
	
	public ArrayList<String> getLanguage()
	{
		return words;
	}
	
	/**
	 * @return language size
	 */
	
	public int getSize()
	{
		return size;
	}
}
