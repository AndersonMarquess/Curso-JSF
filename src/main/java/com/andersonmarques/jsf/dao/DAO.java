package com.andersonmarques.jsf.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * DAO com operações genéricas para ser usado por qualquer classe
 * @author Anderson
 *
 * @param <T>
 */
public class DAO<T> {
	
	private EntityManagerFactory factory = Persistence.createEntityManagerFactory("exemplo-apostila-caelum");
	private EntityManager entityManager;
	
	public void gravar(T t) {
		entityManager = factory.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(t);
		entityManager.getTransaction().commit();
		fechar();
	}
	
	private void fechar() {
		if(entityManager.isOpen()) {
			entityManager.close();
		}
	}
}
