package pa_lab2;

import utils.*;

public class Main {
	public static void main(String[] args)
	{
		Event c1 = new Event("C1", 100, 8, 10);
		Event c2 = new Event("C2", 100, 10, 12);
		Event l1 = new Event("L1", 30, 8, 10);
		Event l2 = new Event("L2", 30, 8, 10);
		Event l3 = new Event("L3", 30, 10, 12);
		
		Room r1 = new Room("401", 30, RoomType.Lab);
		Room r2 = new Room("403", 30, RoomType.Lab);
		Room r3 = new Room("405", 30, RoomType.Lab);
		Room r4 = new Room("309", 100, RoomType.LectureHall);
		
		System.out.println(c1.toString() + ", " + c2.toString() + ", " 
				+ l1.toString() + ", " + l2.toString() + ", " + l3.toString());
		System.out.println(r1.toString() + ", " + r2.toString() + ", " 
				+ r3.toString() + ", " + r4.toString());
	}
}
