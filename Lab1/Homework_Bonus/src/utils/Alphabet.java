package utils;

import java.util.ArrayList;

public class Alphabet 
{
	private ArrayList<Character> letters;
	
	/**
	 * Generates a new Alphabet object
	 */
	
	public Alphabet()
	{
		this.letters = new ArrayList<Character>();
	}
	
	/**
	 * Given a list of individual letters, it creates an alphabet
	 * @param args
	 */
	
	void createAlphabet(String[] args)
	{
		for(String s : args)
		{
			letters.add(s.charAt(0));
		}
	}
	
	/**
	 * Generates random words of given size, based on the provided letters
	 * @param size
	 * @return the generated word
	 */
	
	public String getRandomWord(int size) 
	{
		String word = "";
		
		for(int i = 0; i < size; i++)
		{
			int idx = (int) Math.floor(Math.random() * letters.size());
			word += letters.get(idx);
		}
		
		return word;
	}
}
