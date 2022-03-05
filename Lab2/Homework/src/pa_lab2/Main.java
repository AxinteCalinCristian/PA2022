package pa_lab2;

import utils.*;

public class Main {
	public static void main(String[] args)
	{	
		AssignmentProblem pb = new AssignmentProblem();
		pb.addEvent(new Event("C1", 100, 8, 10));
		pb.addEvent(new Event("C2", 100, 10, 12));
		pb.addEvent(new Event("L1", 30, 8, 10));
		pb.addEvent(new Event("L2", 30, 8, 10));
		pb.addEvent(new Event("L3", 30, 10, 12));
		
		pb.addRoom(new Lab("401", 30, "Windows"));
		pb.addRoom(new Lab("403", 30, "Linux"));
		pb.addRoom(new Lab("405", 30, "Windows"));
		pb.addRoom(new LectureHall("309", 100, true));

		pb.printSolution();
	}
}
