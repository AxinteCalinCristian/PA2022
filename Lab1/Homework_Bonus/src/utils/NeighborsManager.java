package utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class NeighborsManager 
{
	private Language lang;
	private Boolean[][] adjacency_matrix;
	private Map<String, ArrayList<String>> adjacency_list;
	
	private ArrayList<ArrayList<Integer>> graph;
	private ArrayList<ArrayList<Integer>> cycles;
	private int no_of_cycles;
	final int N = 10000000;
	/**
	 * Creates adjacency containers for a given language
	 * @param language
	 */
	
	public NeighborsManager(Language language)
	{
		this.lang = language;
		this.adjacency_matrix = new Boolean[lang.getSize()][lang.getSize()];
		this.adjacency_list = new HashMap<>();
		this.graph = new ArrayList<ArrayList<Integer>>(lang.getSize());
		this.no_of_cycles = 0;
		
		populateAdjacencyContainers();
	}
	
	/**
	 * Populates both the adjacency matrix and the adjacency list for the current object
	 */
	
	private void populateAdjacencyContainers()
	{
		for(int i = 0; i < lang.getSize(); i++)
		{
			for(int j = 0; j < lang.getSize(); j++)
			{
				adjacency_matrix[i][j] = false;
				
				if(i != j && checkHasCommonLetter(lang.getWordAt(i), lang.getWordAt(j)))
				{
					adjacency_matrix[i][j] = true;
					if(!(adjacency_list.containsKey(lang.getWordAt(i))))
					{
						adjacency_list.put(lang.getWordAt(i), new ArrayList<String>());
						graph.add(new ArrayList<Integer>());
					}
					
					adjacency_list.get(lang.getWordAt(i)).add(lang.getWordAt(j));
					graph.get(i).add(j);
				}
			}
		}
	}
	
	/**
	 * Prints the adjacency list for the current object, each line having the following format:
	 * " Word : [List of neighboring words] "
	 */
	
	public void printAdjacencyList()
	{
		for(Map.Entry<String, ArrayList<String>> entry : adjacency_list.entrySet())
		{
			System.out.println(entry.getKey() + " : " + entry.getValue());
		}
	}
	
	/**
	 * Given two input words, checks whether they do or don't have any common letters
	 * @param s1 first string (word)
	 * @param s2 second string (word)
	 * @return true, if the words contain at least a common letter, false otherwise
	 */
	
	private boolean checkHasCommonLetter(String s1, String s2)
	{
		for(char c1: s1.toCharArray())
		{
			for(char c2: s2.toCharArray())
			{
				if(c1 == c2)
				{
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * Returns all cyclic subsets (in a cyclic subset all consecutive words are neighbors, including first and last word)
	 * @return map of cyclic subset lists
	 */
	
	public Map<Integer, ArrayList<String>> getAllCyclicSubsets()
	{
		Map<Integer, ArrayList<String>> cycles_list = new HashMap<>();
		
		int[] color = new int[N];
        int[] par = new int[N];
        int[] mark = new int[N];
        
	    dfs(0, -1, color, mark, par);
	    
	    this.cycles = new ArrayList<ArrayList<Integer>>();
	    
	    for(int i = 0; i < N; i++)
	    {
	    	cycles.add(new ArrayList<Integer>());
	    }
	    
	    for (int i = 0; i < N; i++)
        {
            if (mark[i] != 0)
            {
            	cycles.get(mark[i]).add(i);
            }
        }
	    
	    cycles.removeIf(n -> (n.size() < 3)); // must respect k >= 3
	    
	    for (int i = 0; i < cycles.size(); i++)
        {
            cycles_list.put(i, getCorrespondingWords(cycles.get(i)));
        }
	    
		return cycles_list;
		
	}
	
	/**
	 * Converts the list of indexes to a list of the words corresponding to said indexes
	 * @param indexes
	 * @return the list of corresponding words
	 */
	
	private ArrayList<String> getCorrespondingWords(ArrayList<Integer> indexes)
	{
		ArrayList<String> word_list = new ArrayList<String>();
		
		for(Integer idx : indexes)
		{
			word_list.add(lang.getWordAt(idx));
		}
		
		return word_list;
	}
	
	/**
	 * DFS function based on partial and full coloring of graph that allows processing of vertices 
	 * with the goal of calculating all the cycles in the given graph
	 * @param u current vertex
	 * @param p parent of current vertex
	 * @param color array containing the current coloring of the graph
	 * @param mark array of marked vertices
	 * @param par array of partially visited vertices
	 */
	
	private void dfs(int u, int p, int[] color, int[] mark, int[] par)
	{
		if (color[u] == 2)
		{
			return;
		}

		if (color[u] == 1)
		{
			 no_of_cycles++;
			 int cur = p;
			 
			 if(cur != -1)
			 {
				 mark[cur] = no_of_cycles;
				 while (cur != u)
				 {
				     cur = par[cur];
				     mark[cur] = no_of_cycles;
				 }
			 }
				 
			 return;
		}
		
		par[u] = p;
		color[u] = 1;

		for (int v : graph.get(u))
		{
			 if (v == par[u])
			 {
			     continue;
			 }
			 dfs(v, u, color, mark, par);
		}
		
		color[u] = 2;
	}
	
	/**
	 * Returns the largest cyclic subset (in a cyclic subset all consecutive words are neighbors, including first and last word)
	 * @return largest cyclic subset word list
	 */
	
	public ArrayList<String> getLargestCyclicSubset()
	{
		int idx = 0, mx = 0;
		 Map<Integer, ArrayList<String>> cycles = getAllCyclicSubsets();
		 
		for(Map.Entry<Integer, ArrayList<String>> entry : cycles.entrySet())
		{
			if(entry.getValue().size() > mx)
			{
				mx = entry.getValue().size();
				idx = entry.getKey();
			}
		}
		
		return cycles.get(idx);
	}
}
