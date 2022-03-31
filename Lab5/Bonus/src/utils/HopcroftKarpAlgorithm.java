package utils;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;


public abstract class HopcroftKarpAlgorithm {
	private static Map<String, Set<String>> adj = new HashMap<>();
	private static Map<String, Item> itemsMapping = new HashMap<>();
	private static String dummyStr = "dummyStr";
	
	public static void printSolution(Catalog catalog) {
		constructAdjList(catalog);
		
		Map<String, String> pairU = new HashMap<>();
		Map<String, String> pairV = new HashMap<>();
		Map<String, Integer> dist = new HashMap<>();
		
		for(String s : adj.keySet()) {
			pairU.put(s, dummyStr);
			pairV.put(s, dummyStr);
			dist.put(s, Integer.MAX_VALUE);
		}

        Integer result = 0;

        while (BFS(pairU, pairV, dist)) {
        	for(String v : adj.keySet()) {
        		if(pairU.get(v).equals(dummyStr) && DFS(v, pairU, pairV, dist)) {
        			result++;
        		}
        	}
        }
        
        System.out.println("Size of Maximum cardinality matching = " + result + ", and Size of Minimum edge cover = " + Math.abs(adj.size() - result));
        System.out.println("Matches: ");
        for(Map.Entry<String, String> entry : pairV.entrySet()) {
        	if(!entry.getValue().equals(dummyStr) && !entry.getKey().equals(dummyStr)) {
        		System.out.println("[ " + entry.getKey() + " ] -- [ " + entry.getValue() + " ]");
        	}
        }
	}
	
    private static Boolean BFS(Map<String, String> pairU, Map<String, String> pairV, Map<String, Integer> dist) {
    	Queue<String> queue = new LinkedList<>();
    	 
    	for(String u : adj.keySet()) {
    		if(pairU.get(u).equals(dummyStr)) {
    			dist.put(u, 0);
    			queue.add(u);
    		}
    		else
    			dist.put(u, Integer.MAX_VALUE);
    	}
    	dist.put(dummyStr, Integer.MAX_VALUE);
    	
        while (!queue.isEmpty()) {
            String u = queue.poll();
            
            if (dist.get(u) < dist.get(dummyStr)) {
            	
                for(String s : adj.get(u))
                {
                    String v = s;
                    
                    if (pairV.get(v) != null && dist.get(pairV.get(v)) == Integer.MAX_VALUE) {
                    	dist.put(pairV.get(v), dist.get(u) + 1);
                    	queue.add(pairV.get(v));
                    }
                }
            }
        }
        return (dist.get(dummyStr) != Integer.MAX_VALUE);
    }
    
    private static Boolean DFS(String u, Map<String, String> pairU, Map<String, String> pairV, Map<String, Integer> dist) {
		 if (!u.equals(dummyStr)) {
			 for(String s : adj.get(u)) {
				 String v = s;
				 
				 if(pairV.get(v) != null && dist.get(pairV.get(v)) == dist.get(u) + 1) {
					 if(DFS(pairV.get(v), pairU, pairV, dist)) {
						 pairV.put(v, u);
						 pairU.put(u, v);
						 return true;
					 }
				 }
			 }
			 dist.put(u, Integer.MAX_VALUE);
		     return false;
		 }
		 return true;
    }
        
	private static void constructAdjList(Catalog catalog) {
		Set<Item> items = catalog.getItems();
		adj.put(dummyStr, new HashSet<>());
		for(Item i : items) {
			String i_str = i.toString();
			itemsMapping.put(i_str, i);
			
			List<String> keywords = i.getKeywords();
			for(String kw : keywords) {
				if(!adj.containsKey(kw)) {
					adj.put(kw, new HashSet<>());
				}
				if(!adj.containsKey(i_str)) {
					adj.put(i_str, new HashSet<>());
				}
				adj.get(kw).add(i_str);
				adj.get(i_str).add(kw);
			}
		}
	}
}
