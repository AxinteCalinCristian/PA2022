package utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.time.*;

public class EventGraph {
	private Map<Event, ArrayList<Event>> adjacency_list;
	private ArrayList<Color> colors;
	private ArrayList<Room> rooms;
	
	public EventGraph()
	{
		this.adjacency_list = new HashMap<>();
		this.colors = new ArrayList<Color>();
		this.rooms = new ArrayList<Room>();
	}
	
	/**
	 * Checks if the room is already registered. If not, it is added to the list.
	 * @param r room to be added
	 */
	public void addRoom(Room r)
	{
		for(Room r1 : rooms)
		{
			if(r1.equals(r))
			{
				System.out.println("Room " + r.toString() + " already registered");
				return;
			}
		}
		rooms.add(r);
	}
	
	/**
	 * @param a first event
	 * @param b second event
	 * @return true if the events overlap, otherwise false
	 */
	private boolean checkEventsOverlap(Event a, Event b)
	{
		if(a.getStartTime().compareTo(b.getEndTime()) <= 0)
		{
			return true;
		}
		else if(b.getStartTime().compareTo(a.getEndTime()) <= 0)
		{
			return true;
		}
		else if(a.getStartTime().compareTo(b.getStartTime()) == 0)
		{
			return true;
		}
		else if(a.getEndTime().compareTo(b.getEndTime()) == 0)
		{
			return true;
		}
		
		return false;
	}
	
	/**
	 * Checks if the event is already registered. If not, it is added to the list.
	 * @param e event to be added
	 */
	public void addEvent(Event e)
	{
		for(Map.Entry<Event, ArrayList<Event>> e1 : adjacency_list.entrySet())
		{
			if(e1.getKey().equals(e))
			{
				System.out.println("Event " + e.toString() + " already registered");
				return;
			}
		}
		
		adjacency_list.put(e, new ArrayList<Event>());
		
		for(Map.Entry<Event, ArrayList<Event>> e1 : adjacency_list.entrySet())
		{
			if(!(e1.getKey().equals(e)) && checkEventsOverlap(e1.getKey(), e))
			{
				adjacency_list.get(e).add(e1.getKey());
				adjacency_list.get(e1.getKey()).add(e);
			}
		}
	}
	
	/**
	 * Clears the rooms list.
	 */
	public void clearRooms()
	{
		rooms.clear();
	}
	
	/**
	 * Clears the events list.
	 */
	public void clearEvents()
	{
		adjacency_list.clear();
	}
	
	/**
	 * Prints a possible solution for the given problem.
	 */
	public void printSolution()
	{
		Collection<Vertex> colorlessVertices = new ArrayList<>();
		ArrayList<Vertex> graph = new ArrayList<>();
		Map<Vertex, Color> color = new HashMap<>();
		
		for(Map.Entry<Event, ArrayList<Event>> e : adjacency_list.entrySet())
		{
			Vertex v = new Vertex(0, 0, e.getKey());
			colorlessVertices.add(v);
			graph.add(v);
			color.put(v, null);
		}
		
		while (colorlessVertices.size() > 0) {
			PriorityQueue<Vertex> current_vertex = new PriorityQueue<Vertex>(colorlessVertices);

			Vertex selectedVertex = current_vertex.remove();
			
			Collection<Vertex> adjacentVertices = getAdjacentVertices(selectedVertex, graph);

			SortedSet<Color> adjColorCodes = new TreeSet<Color>();
			for (Vertex adjVertex: adjacentVertices) {
				if (color.get(adjVertex) != null) {
					adjColorCodes.add(color.get(adjVertex));
				}
			}
			
			SortedSet<Color> tempColors = new TreeSet<Color>();
			
			for(Color c : colors)
			{
				tempColors.add(c);
			}
			
			tempColors.removeAll(adjColorCodes);
			Color newColor;
			if (tempColors.size() > 0) {
				newColor = tempColors.first();
			} else {
				newColor = getNewColor(selectedVertex);
			}
			color.put(selectedVertex, newColor);

			colorlessVertices.remove(selectedVertex);

			for (Vertex v: adjacentVertices) {
				Collection<Vertex> adjVertices = getAdjacentVertices(v, graph);
				SortedSet<Color> adjColCodes = new TreeSet<Color>();
				for (Vertex adjVertex: adjVertices) {
					if (color.get(adjVertex) != null) {
						adjColCodes.add(color.get(adjVertex));
					}
				}
				
				int idx = graph.indexOf(v);
				v.setDegree(adjColCodes.size());
				graph.set(idx , v);
			}
		}
		
		for(Map.Entry<Vertex, Color> entry : color.entrySet())
		{
			System.out.println(entry.getKey().getEvent().getName() + " -> " + entry.getValue().getRoom().getName());
		}
		
		Set<Room> used = new HashSet<Room>();
		
		for(Color c : colors)
		{
			used.add(c.getRoom());
		}
		
		System.out.println("Total number of used rooms: " + used.size() + '\n');
	}
	
	/**
	 * @param selectedVertex
	 * @return a new color for the selected vertex
	 */
	private Color getNewColor(Vertex selectedVertex)
	{
		Color c = new Color(rooms.get(0), new Pair<LocalTime, LocalTime>(selectedVertex.getEvent().getStartTime(), selectedVertex.getEvent().getEndTime()));
		Room best_match = null;
		for(Room r : rooms)
		{
			if((best_match == null && selectedVertex.getEvent().getSize() <= r.getCapacity()) || (selectedVertex.getEvent().getSize() <= r.getCapacity() && r.getCapacity() < best_match.getCapacity()))
			{
				best_match = r;
				c.setRoom(best_match);
			}
			
			if(colors.contains(c))
			{
				best_match = null;
			}
			
		}
		
		if(colors.contains(c))
		{
			return c;
		}
		
		colors.add(c);
		return c;
	}
	
	/**
	 * 
	 * @param selectedVertex
	 * @param graph
	 * @return all adjacent vertices in the subgraph for the selected vertex
	 */
	private Collection<Vertex> getAdjacentVertices(Vertex selectedVertex, ArrayList<Vertex> graph)
	{
		Collection<Vertex> adjacentVertices = new ArrayList<>();
		
		for(Event e : adjacency_list.get(selectedVertex.getEvent()))
		{
			for(Vertex v : graph)
			{
				if(v.getEvent().equals(e))
				{
					adjacentVertices.add(v);
					break;
				}
			}	
		}
		
		return adjacentVertices;
	}
	
}
