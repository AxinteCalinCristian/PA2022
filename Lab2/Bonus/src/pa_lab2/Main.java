package pa_lab2;

import java.time.LocalTime;
import java.util.concurrent.TimeUnit;

import utils.*;

public class Main {
	public static void main(String[] args)
	{	
		final long startTimeAP = System.nanoTime();
		
		AssignmentProblem pb = new AssignmentProblem();
		pb.addEvent(new Event("C1", 100, LocalTime.of(8,  0), LocalTime.of(10,  0)));
		pb.addEvent(new Event("C2", 100, LocalTime.of(10,  0), LocalTime.of(12,  0)));
		pb.addEvent(new Event("L1", 30, LocalTime.of(8,  0), LocalTime.of(10,  0)));
		pb.addEvent(new Event("L2", 30, LocalTime.of(8,  0), LocalTime.of(10,  0)));
		pb.addEvent(new Event("L3", 30, LocalTime.of(10,  0), LocalTime.of(12,  0)));
		
		pb.addRoom(new Lab("401", 30, "Windows 10"));
		pb.addRoom(new Lab("403", 30, "Linux"));
		pb.addRoom(new Lab("405", 30, "Windows 7"));
		pb.addRoom(new LectureHall("309", 100, true));

		pb.printSolution();
		
		System.out.println("Duration for the greedy approach(ms): " + TimeUnit.SECONDS.convert(System.nanoTime() - startTimeAP, TimeUnit.MILLISECONDS));
		
		final long startTimeEG = System.nanoTime();
		
		EventGraph eg = new EventGraph();
		eg.addEvent(new Event("C1", 100, LocalTime.of(8,  0), LocalTime.of(10,  0)));
		eg.addEvent(new Event("C2", 100, LocalTime.of(10,  0), LocalTime.of(12,  0)));
		eg.addEvent(new Event("L1", 30, LocalTime.of(8,  0), LocalTime.of(10,  0)));
		eg.addEvent(new Event("L2", 30, LocalTime.of(8,  0), LocalTime.of(10,  0)));
		eg.addEvent(new Event("L3", 30, LocalTime.of(10,  0), LocalTime.of(12,  0)));
		
		eg.addRoom(new Lab("401", 30, "Windows 10"));
		eg.addRoom(new Lab("403", 30, "Linux"));
		eg.addRoom(new Lab("405", 30, "Windows 7"));
		eg.addRoom(new LectureHall("309", 100, true));

		eg.printSolution();
		
		System.out.println("Duration for the greedy approach(ms): " + TimeUnit.SECONDS.convert(System.nanoTime() - startTimeEG, TimeUnit.MILLISECONDS));
		
	}
}
