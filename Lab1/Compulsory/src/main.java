
/**
 * Compulsory part of PA Laboratory 1
 * @author Calin Axinte
 */

public class main {
	public static void main(String[] args)
	{
		System.out.println("Hello world!");
		
		String[] languages = {"C", "C++", "C#", "Python", "Go", "Rust", "JavaScript", "PHP", "Swift", "Java"};
		
		int n =  (int) (Math.random() * 1000000);
		
		n *= 3;
		n += Integer.parseInt("10101", 2);
		n += Integer.parseInt("FF", 16);
		n *= 6;
		
		int result;
		
		do {
			result = 0;
			while(n > 0)
			{
				result += n % 10;
				n /= 10;
			}
			n = result;
		}
		while(n > 9);
		
		System.out.println( "Willy-nilly, this semester I will learn " + languages[result] + ".");
	}
}
