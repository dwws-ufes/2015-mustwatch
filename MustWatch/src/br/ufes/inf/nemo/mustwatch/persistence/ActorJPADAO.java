package br.ufes.inf.nemo.mustwatch.persistence;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.ufes.inf.nemo.mustwatch.domain.Actor;
import br.ufes.inf.nemo.util.ejb3.persistence.BaseJPADAO;

@Stateless
public class ActorJPADAO extends BaseJPADAO<Actor> implements ActorDAO {
	/** Serialization id. */
	private static final long serialVersionUID = 1L;

	@PersistenceContext
	private EntityManager entityManager;
	
	public EntityManager getEntityManager(){
		return entityManager;
	}

	@Override
	public Class<Actor> getDomainClass() {
		// TODO Auto-generated method stub
		return Actor.class;
	}
	

}
