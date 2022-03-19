package main;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;

import utils.*;

public class Main {
	public static void main(String[] args) {
		List<Intersection> inters = IntStream.rangeClosed(0, 8)
				 .mapToObj(i -> new Intersection("v" + i) )
				 .toList();
		Set<Intersection> intersections = new HashSet<>(inters);
		
		for(Intersection i : intersections) {
			System.out.print(i.getName() + ' ');
		}
		System.out.print('\n');
		
		List<Street> streets = new LinkedList<>();
		streets.add(new Street("1", 2, inters.get(0), inters.get(1)));
		streets.add(new Street("2", 2, inters.get(0), inters.get(2)));
		streets.add(new Street("3", 2, inters.get(0), inters.get(3)));
		streets.add(new Street("4", 3, inters.get(1), inters.get(4)));
		streets.add(new Street("5", 2, inters.get(1), inters.get(2)));
		streets.add(new Street("6", 2, inters.get(2), inters.get(6)));
		streets.add(new Street("7", 2, inters.get(2), inters.get(5)));
		streets.add(new Street("8", 1, inters.get(2), inters.get(3)));
		streets.add(new Street("9", 3, inters.get(3), inters.get(5)));
		streets.add(new Street("10", 1, inters.get(4), inters.get(7)));
		streets.add(new Street("11", 2, inters.get(4), inters.get(8)));
		streets.add(new Street("12", 1, inters.get(4), inters.get(5)));
		streets.add(new Street("13", 3, inters.get(5), inters.get(8)));
		streets.add(new Street("14", 1, inters.get(6), inters.get(7)));
		streets.add(new Street("15", 1, inters.get(6), inters.get(8)));
		
		streets.sort((s1, s2) -> s1.compareTo(s2));
		for(Street s : streets) {
			System.out.print(s.getName() + "(len=" + s.getLength() + ") ");
		}
	}
}