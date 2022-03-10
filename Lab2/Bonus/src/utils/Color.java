package utils;

import java.time.LocalTime;

public class Color implements Comparable<Color>{
	private Room room;
	private Pair<LocalTime, LocalTime> times;
	
	Color(Room room, Pair<LocalTime, LocalTime> times)
	{
		this.room = room;
		this.times = times;
	}
	
	public Room getRoom() {
		return room;
	}
	public void setRoom(Room room) {
		this.room = room;
	}
	public Pair<LocalTime, LocalTime> getTimes() {
		return times;
	}
	public void setTimes(Pair<LocalTime, LocalTime> times) {
		this.times = times;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
            return true;
        }

        if (!(obj instanceof Color)) {
            return false;
        }
        
        Color c = (Color) obj;
        
        if(this.room.equals(c.getRoom()) && checkIntervalsOverlap(this.times.first(), this.times.second(), c.getTimes().first(), c.getTimes().second()))
        {
        	return true;
        }
        
        return false;
	}

	private boolean checkIntervalsOverlap(LocalTime a_start, LocalTime a_end, LocalTime b_start, LocalTime b_end)
	{
		if(a_start.compareTo(b_end) < 0)
		{
			return true;
		}
		else if(b_start.compareTo(a_end) < 0)
		{
			return true;
		}
		if(a_start.compareTo(b_start) == 0)
		{
			return true;
		}
		else if(a_end.compareTo(b_end) == 0)
		{
			return true;
		}
		return false;
	}
	
	@Override
	public int compareTo(Color o) {
		if(o == null)
		{
			return -1;
		}
		
		if(this.equals(o))
		{
			return 0;
		}
		
		else if(this.times.first().compareTo(o.getTimes().first()) < 0 || 
				(this.times.first().compareTo(o.getTimes().first()) == 0 && this.times.second().compareTo(o.getTimes().second()) < 0))
		{
			return -1;
		}
		
		return 1;
	}
}
