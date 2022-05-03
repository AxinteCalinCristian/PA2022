package bonus.main;

import bonus.utils.Database;
import bonus.utils.Utils;

public class Bonus {
	public static void main(String[] args) {
		Database.initDB();
		
//		Utils.populateDatabase("./concap.csv");
//		Utils.addDummyData(1000);
//		Utils.addSisterRelationships(1200);
//		System.out.println("Brasilia -- Budapest : " + Utils.getDistance(Database.getCity("Brasilia").get(0), Database.getCity("Budapest").get(0)) + " KM");
//		System.out.println("Helsinki -- Paris : " + Utils.getDistance(Database.getCity("Helsinki").get(0), Database.getCity("Paris").get(0)) + " KM");
//		System.out.println("New Delhi -- Georgetown : " + Utils.getDistance(Database.getCity("New Delhi").get(0), Database.getCity("Georgetown").get(0)) + " KM");
		Utils.runBronKerbosch();
	}
}
