package com.java4.config;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class DBConnection {

	public static EntityManager getEntityManager() {

		EntityManagerFactory managerFactory = Persistence.createEntityManagerFactory("dbConnect");

		return managerFactory.createEntityManager();
	}
}
