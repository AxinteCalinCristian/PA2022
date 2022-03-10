package utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.time.*;

public class AssignmentProblem {
	private ArrayList<Event> events;
	private ArrayList<Room> rooms;
	
	public AssignmentProblem()
	{
		this.events = new ArrayList<Event>();
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
	 * Checks if the event is already registered. If not, it is added to the list.
	 * @param e event to be added
	 */
	public void addEvent(Event e)
	{
		for(Event e1 : events)
		{
			if(e1.equals(e))
			{
				System.out.println("Event " + e.toString() + " already registered");
				return;
			}
		}
		events.add(e);
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
		events.clear();
	}
	
	
	private Map<Room, LocalTime> roomsMap;
	
	/**
	 * Prints a possible solution for the given problem.
	 */
	public void printSolution()
	{
		this.roomsMap = new HashMap<>();
		HashSet<Room> usedRooms = new HashSet<>();
		
		for(Room r : rooms)
		{
			roomsMap.put(r, LocalTime.MIN);
		}
		
		System.out.println("(A possible) Solution:");
		
		events.sort((e1, e2) -> e1.getEndTime().compareTo(e2.getEndTime()));
		
		
		LocalTime current_end_time = LocalTime.MIN;
		
		for(Event e : events)
		{
			if(e.getEndTime().compareTo(current_end_time) == 1)
			{
				current_end_time = e.getEndTime();
			}
			
			Room r = getAvailableRoom(current_end_time, Duration.between(e.getStartTime(), current_end_time), e.getSize());
			
			if(r == null)
			{
				System.out.println("Failed solution");
				return;
			}
			
			roomsMap.put(r, current_end_time);
			usedRooms.add(r);
			
			System.out.println(e.getName() + " -> " + r.getName());
		}
		
		System.out.println("Total number of used rooms: " + usedRooms.size() + '\n');
	}
	
	
	/**
	 * @param current_end_time end time for the current event
	 * @param duration duration of the current event
	 * @param size size of the current event
	 * @return an available room, best suited for the event. If none are found, returns null.
	 */
	private Room getAvailableRoom(LocalTime current_end_time, Duration duration, Integer size)
	{
		Room potentialRoom = null;
		LocalTime best_end_time = LocalTime.MAX;
		Integer best_capacity = 0;
		
		for(Entry<Room, LocalTime> r : roomsMap.entrySet())
		{
			if(r.getValue().compareTo(current_end_time.minus(duration)) <= 0  && r.getKey().getCapacity() >= size)
			{
				if((best_end_time.compareTo(current_end_time.minus(duration)) == 1 || (best_end_time.compareTo(current_end_time.minus(duration)) == 0 && best_capacity > r.getKey().getCapacity())))
				{
					best_end_time=current_end_time.minus(duration);
					best_capacity=r.getKey().getCapacity();
					potentialRoom = r.getKey();
				}
			}
		}
		
		return potentialRoom;
	}
}
