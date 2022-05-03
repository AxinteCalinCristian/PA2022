package homework.main;

import homework.utils.Database;
import homework.utils.Utils;

public class Homework {
	public static void main(String[] args) {
		Database.initConnection();
		
//		Utils.populateDatabase("./concap.csv");
		System.out.println("Brasilia -- Budapest : " + Utils.getDistance(Database.getCity("Brasilia").get(0), Database.getCity("Budapest").get(0)) + " KM");
		System.out.println("Helsinki -- Paris : " + Utils.getDistance(Database.getCity("Helsinki").get(0), Database.getCity("Paris").get(0)) + " KM");
		System.out.println("New Delhi -- Georgetown : " + Utils.getDistance(Database.getCity("New Delhi").get(0), Database.getCity("Georgetown").get(0)) + " KM");
		Utils.displayCities();
		Database.closeConnection();
	}
}
