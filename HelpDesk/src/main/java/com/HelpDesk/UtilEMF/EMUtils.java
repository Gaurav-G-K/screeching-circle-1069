package com.HelpDesk.UtilEMF;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class EMUtils {

	static EntityManagerFactory emf;
	
	static {
		
		emf = Persistence.createEntityManagerFactory("HelpDesk"); 
	}
	
	public static EntityManager createConection() {
		
		return emf.createEntityManager();
	}
	
}
