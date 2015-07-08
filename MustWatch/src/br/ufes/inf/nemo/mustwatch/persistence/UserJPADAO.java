package br.ufes.inf.nemo.mustwatch.persistence;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import br.ufes.inf.nemo.mustwatch.domain.User;
import br.ufes.inf.nemo.mustwatch.persistence.UserDAO;
import br.ufes.inf.nemo.util.ejb3.persistence.BaseJPADAO;

@Stateless
public class UserJPADAO extends BaseJPADAO<User> implements UserDAO {
	/** Serialization id. */
	private static final long serialVersionUID = 1L;
	
	@PersistenceContext 
	private EntityManager entityManager;

	@Override
	public Class<User> getDomainClass() {
		return User.class;
	}

	@Override
	protected EntityManager getEntityManager() {
		return entityManager;
	}

	@Override
	public User retrieveByLogin(String login) {
		String jpql = "select u from User u where u.login = '" + login
				+ "'";
		TypedQuery<User> query = entityManager.createQuery(jpql,
				User.class);
		User u = query.getSingleResult();
		return u;
	}
}
