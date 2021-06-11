package br.com.fiap.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import br.com.fiap.model.Usuario;
import br.com.fiap.singleton.EntityManagerFactorySingleton;

public class UsuarioDao {

	public void salvar(Usuario usuario) {
		EntityManager em = EntityManagerFactorySingleton.getInstance().createEntityManager();
		em.getTransaction().begin();
		em.persist(usuario);
		em.getTransaction().commit();	
		em.close();
	}
	
	public List<Usuario> listar() {
		EntityManager em = EntityManagerFactorySingleton.getInstance().createEntityManager();
		String jpql = "SELECT p FROM Usuario p";
		TypedQuery<Usuario> query = em.createQuery(jpql, Usuario.class);
		List<Usuario> usuarios = query.getResultList();
		em.close();
		return usuarios;	
	}

	public Usuario buscar(Long id) {
		EntityManager em = EntityManagerFactorySingleton.getInstance().createEntityManager();
		Usuario usuario = em.find(Usuario.class, id);
		em.close();
		return usuario;
	}

	public void atualizar(Usuario usuario) {
		EntityManager em = EntityManagerFactorySingleton.getInstance().createEntityManager();
		em.getTransaction().begin();
		em.merge(usuario);
		em.flush();
		em.getTransaction().commit();
		em.close();
	}

	public void deletar(Long id) {
		EntityManager em = EntityManagerFactorySingleton.getInstance().createEntityManager();
		em.getTransaction().begin();
		String jpql = "DELETE FROM Usuario p WHERE id = " + id;
		Query query = em.createQuery(jpql);
		query.executeUpdate();
		em.getTransaction().commit();	
		em.close();
	}

	public boolean exist(Usuario usuario) {
		EntityManager em = EntityManagerFactorySingleton.getInstance().createEntityManager();
		TypedQuery<Usuario> query = em.createQuery("SELECT u FROM Usuario u WHERE u.email = :email AND u.senha = :senha", Usuario.class);
		query.setParameter("email", usuario.getEmail());
		query.setParameter("senha", usuario.getSenha());
		try {
			query.getSingleResult();
		} catch (Exception e) {
			em.close();
			return false;		
		}
		em.close();
		return true;
	}
}