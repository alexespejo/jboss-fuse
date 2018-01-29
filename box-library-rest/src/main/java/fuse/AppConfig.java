package org.box.fuse;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class AppConfig {
	private static EntityManager em;
	
	public static EntityManager init(){
		EntityManagerFactory emf = Persistence
                .createEntityManagerFactory("EmployeePU");
        em = emf.createEntityManager();
        return em;
	}
}
