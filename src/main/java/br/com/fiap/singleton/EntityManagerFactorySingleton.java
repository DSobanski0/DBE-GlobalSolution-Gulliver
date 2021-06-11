package br.com.fiap.singleton;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerFactorySingleton {
	
	private static EntityManagerFactory fabrica;

	public static EntityManagerFactory getInstance() {
		if (fabrica == null) {
			fabrica = Persistence.createEntityManagerFactory("gulliver-persistence-unit");
		}

		return fabrica;
	}

	private EntityManagerFactorySingleton() {
	}
}