package problem1.dataAccessLayer.utilites;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EMUtil {
	private static EntityManagerFactory emf;
	
	static {
		emf = Persistence.createEntityManagerFactory("bookUnit");
	}
	
	public static EntityManager getEntityManager() {
		return emf.createEntityManager();
	}
}
