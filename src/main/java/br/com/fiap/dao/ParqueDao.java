package br.com.fiap.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import br.com.fiap.model.Parque;
import br.com.fiap.singleton.EntityManagerFactorySingleton;

public class ParqueDao {

	public void salvar(Parque parque) {
		EntityManager em = EntityManagerFactorySingleton.getInstance().createEntityManager();
		em.getTransaction().begin();
		em.persist(parque);
		em.getTransaction().commit();	
		em.close();
	}
	
	public List<Parque> listar() {
		EntityManager em = EntityManagerFactorySingleton.getInstance().createEntityManager();
		String jpql = "SELECT p FROM Parque p";
		TypedQuery<Parque> query = em.createQuery(jpql, Parque.class);
		List<Parque> parques = query.getResultList();
		em.close();
		return parques;	
	}

	public Parque buscar(Long id) {
		EntityManager em = EntityManagerFactorySingleton.getInstance().createEntityManager();
		Parque parque = em.find(Parque.class, id);
		em.close();
		return parque;
	}

	public void atualizar(Parque parque) {
		EntityManager em = EntityManagerFactorySingleton.getInstance().createEntityManager();
		em.getTransaction().begin();
		em.merge(parque);
		em.flush();
		em.getTransaction().commit();
		em.close();
	}

	public void deletar(Long id) {
		EntityManager em = EntityManagerFactorySingleton.getInstance().createEntityManager();
		em.getTransaction().begin();
		String jpql = "DELETE FROM Parque p WHERE id = " + id;
		Query query = em.createQuery(jpql);
		query.executeUpdate();
		em.getTransaction().commit();	
		em.close();
	}
}