package com.andersonmarques.jsf.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;

/**
 * DAO com operações genéricas para ser usado por qualquer classe
 * @author Anderson
 *
 * @param <T>
 */
public class DAO<T> {
	
	private EntityManagerFactory factory = Persistence.createEntityManagerFactory("exemplo-apostila-caelum");
	private EntityManager entityManager;
	private final Class<T> classe;
	
	public DAO(Class<T> classe) {
		this.classe = classe;
	}
	
	public void gravar(T t) {
		iniciarTransacao();
		entityManager.persist(t);
		entityManager.getTransaction().commit();
		fecharConexao();
	}
	
	public void remover(Integer id) {
		iniciarTransacao();
		T recuperado = entityManager.find(classe, id);
		entityManager.remove(recuperado);
		entityManager.getTransaction().commit();
		fecharConexao();
	}
	
	public void atualizar(T t) {
		iniciarTransacao();
		entityManager.merge(t);
		entityManager.getTransaction().commit();
		fecharConexao();
	}
	
	public List<T> listarTodos() {
		abrirConexao();
		List<T> resultados = entityManager.createQuery("SELECT t FROM "+classe.getName()+" t", classe)
				.getResultList();
		fecharConexao();

		return resultados;
	}
	
	public T buscarPorId(Integer id){
		abrirConexao();
		T resultado = entityManager.find(classe, id);
		fecharConexao();
		return resultado;
	}
	
	public Integer contarTodos() {
		abrirConexao();
		Integer resultado = entityManager.createQuery("SELECT Count(n) FROM livro n", Integer.class)
				.getSingleResult();
		fecharConexao();
		return resultado;
	}
	
	/**
	 * Executa um select com valor mínimo e máximo de resultados.
	 * 
	 * @param inicio
	 * @param fim
	 * @return
	 */
	public List<T> listarTodosPaginado(int inicio, int fim) {
		abrirConexao();
		CriteriaQuery<T> query = entityManager.getCriteriaBuilder().createQuery(classe);
		query.select(query.from(classe));
		List<T> resultados = entityManager
				.createQuery(query)
				.setFirstResult(inicio)
				.setMaxResults(fim)
				.getResultList();
		return resultados;
	}

	private void abrirConexao() {
		entityManager = factory.createEntityManager();
	}
	
	private void iniciarTransacao() {
		abrirConexao();
		entityManager.getTransaction().begin();
	}
	
	private void fecharConexao() {
		if(entityManager.isOpen()) {
			entityManager.close();
		}
	}
}
