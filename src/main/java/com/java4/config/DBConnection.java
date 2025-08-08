package com.java4.config;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DBConnection {

	public static EntityManager getEntityManager() {

		EntityManagerFactory managerFactory = Persistence.createEntityManagerFactory("dbConnect");

		return managerFactory.createEntityManager();
	}
}
