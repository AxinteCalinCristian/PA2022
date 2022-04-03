package bonus.utils;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import bonus.window.GameGrid;
import bonus.window.GridIntersection;
import lombok.extern.java.Log;

/**
 * The game AI. Opposes the human player.
 * @author Calin Axinte
 */
@Log
public abstract class GameAI {
	
	private static Set<GridIntersection> selectedComponent = new HashSet<>();
	private static Set<Pair<GridIntersection, GridIntersection>> sticks = new HashSet<>();
	private static Map<GridIntersection, Set<GridIntersection>> adj = new HashMap<>();
	private static GridIntersection dummy = new GridIntersection();
	
	/**
	 * Registers the first move and processes the game grid and available moves.
	 * @param i
	 * @param gameGrid
	 */
	public static void registerFirstMove(GridIntersection i, GameGrid gameGrid) {
		selectedComponent.clear();
		sticks.clear();
		sticks = new HashSet<>(gameGrid.getSticks());
		DFS(i);
		createSubGraph();
		checkHasPerfectMatching();
	}
	
	private static void DFS(GridIntersection node) {
		selectedComponent.add(node);
		for(Pair<GridIntersection, GridIntersection> stick : sticks) {
			if(stick.first().equals(node) && !selectedComponent.contains(stick.second())) {
				DFS(stick.second());
			}
			else if(stick.second().equals(node) && !selectedComponent.contains(stick.first())) {
				DFS(stick.first());
			}
		}
	}
	
	private static void createSubGraph() {
		adj.put(dummy, new HashSet<>());
		for(Pair<GridIntersection, GridIntersection> stick : sticks) {
			if(selectedComponent.contains(stick.first()) && selectedComponent.contains(stick.first())) {
				if(!adj.containsKey(stick.first())) {
					adj.put(stick.first(), new HashSet<>());
				}
				if(!adj.containsKey(stick.second())) {
					adj.put(stick.second(), new HashSet<>());
				}
				adj.get(stick.first()).add(stick.second());
				adj.get(stick.second()).add(stick.first());
			}
		}
	}
	
	/**
	 * Checks whether the player has a definite win by playing optimally.
	 */
	private static void checkHasPerfectMatching() {
		if(adj.size() % 2 == 0 || adj.size() < 2) {
			System.out.println("CHECK");
			log.warning("Player has chosen a non-perfect matching connected component.");
	    	return;
	    }
		
		Map<GridIntersection, GridIntersection> pairU = new HashMap<>();
		Map<GridIntersection, GridIntersection> pairV = new HashMap<>();
		Map<GridIntersection, Integer> dist = new HashMap<>();
		
		for(GridIntersection s : adj.keySet()) {
			pairU.put(s, dummy);
			pairV.put(s, dummy);
			dist.put(s, Integer.MAX_VALUE);
		}
	
	    Integer result = 0;
	
	    while (BFS(pairU, pairV, dist)) {
	    	for(GridIntersection v : adj.keySet()) {
	    		if(pairU.get(v).equals(dummy) && DFS(v, pairU, pairV, dist)) {
	    			result++;
	    		}
	    	}
	    }
	    
	    if(result * 2  == adj.size() - 1) {
	    	log.warning("Player has chosen a perfect matching connected component. If played optimally, the AI wins.");
	    } else {
	    	log.warning("Player has chosen a non-perfect matching connected component.");
	    }
	}
	
	/**
	 * Performs a BFS search on a graph
	 * @param pairU
	 * @param pairV
	 * @param dist
	 * @return true if there are still vertices to be processed, else false
	 */
	private static Boolean BFS(Map<GridIntersection, GridIntersection> pairU, Map<GridIntersection, GridIntersection> pairV, Map<GridIntersection, Integer> dist) {
		Queue<GridIntersection> queue = new LinkedList<>();
		 
		for(GridIntersection u : adj.keySet()) {
			if(pairU.get(u).equals(dummy)) {
				dist.put(u, 0);
				queue.add(u);
			}
			else
				dist.put(u, Integer.MAX_VALUE);
		}
		dist.put(dummy, Integer.MAX_VALUE);
		
	    while (!queue.isEmpty()) {
	    	GridIntersection u = queue.poll();
	        
	        if (dist.get(u) < dist.get(dummy)) {
	        	
	        	if(!adj.containsKey(u)) {
	        		continue;
	        	}
	        	
	            for(GridIntersection s : adj.get(u))
	            {
	            	GridIntersection v = s;
	                
	                if (pairV.get(v) != null && dist.get(pairV.get(v)) == Integer.MAX_VALUE) {
	                	dist.put(pairV.get(v), dist.get(u) + 1);
	                	queue.add(pairV.get(v));
	                }
	            }
	        }
	    }
	    return (dist.get(dummy) != Integer.MAX_VALUE);
	}
	
	/**
	 * Performs a DFS search on the graph
	 * @param u
	 * @param pairU
	 * @param pairV
	 * @param dist
	 * @return true if there are still vertices to be processed, else false
	 */
	private static Boolean DFS(GridIntersection u, Map<GridIntersection, GridIntersection> pairU, Map<GridIntersection, GridIntersection> pairV, Map<GridIntersection, Integer> dist) {
		 if (!u.equals(dummy)) {
			 if(adj.containsKey(u)) {
				 for(GridIntersection s : adj.get(u)) {
					 GridIntersection v = s;
					 
					 if(pairV.get(v) != null && dist.get(pairV.get(v)) == dist.get(u) + 1) {
						 if(DFS(pairV.get(v), pairU, pairV, dist)) {
							 pairV.put(v, u);
							 pairU.put(u, v);
							 return true;
						 }
					 }
				 }
			 }
			 dist.put(u, Integer.MAX_VALUE);
		     return false;
		 }
		 return true;
	}
	
	public static GridIntersection makeMove() {
		for(Pair<GridIntersection, GridIntersection> stick : sticks) {
			if(stick.first().getActive() && !stick.second().getActive()) {
				return stick.second();
			}
			else if(stick.second().getActive() && !stick.first().getActive()) {
				return stick.first();
			}
		}
		
		return null;
	}

} 
