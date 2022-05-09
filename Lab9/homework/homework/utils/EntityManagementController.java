package homework.utils;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public abstract class EntityManagementController {
	public static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("mapLocations");
}
