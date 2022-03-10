package utils;

import java.time.LocalTime;
import java.util.Random;

public class RandomDataGenerator {
	
	private String[] os = {"Windows 10", "Windows 7", "Windows XP", "Windows Vista", "Linux", "MacOS"};
	
	public RandomDataGenerator(){}
	
	public Event generateEvent()
	{
		Random rand = new Random();
		Event e = new Event();
		e.setSize(rand.nextInt(10, 500));
		String name = "";
		name += rand.nextInt(48, 48 + 26);
		name += rand.nextInt(1, 100);
		e.setName(name);
		
		int time = rand.nextInt(6, 24);
		e.setStartTime(LocalTime.of(time, 0));
		time = Math.min(23, time + rand.nextInt(2, 12));
		e.setEndTime(LocalTime.of(time, 0));
		
		return e;
	}
	
	public Room generateRoom()
	{
		Random rand = new Random();
		int choice = rand.nextInt(2);
		
		if(choice == 0)
		{
			Lab r = new Lab();
			r.setCapacity(rand.nextInt(10, 500));
			String name = "L";
			name += rand.nextInt(1, 500);
			r.setName(name);
			r.setRunningOS(os[rand.nextInt(0, 6)]);
			return r;
		}
		else 
		{
			LectureHall r = new LectureHall();
			r.setCapacity(rand.nextInt(10, 500));
			String name = "C";
			name += rand.nextInt(1, 500);
			r.setName(name);
			r.setHasProjector(true);
			return r;
		}
		
	}
}
