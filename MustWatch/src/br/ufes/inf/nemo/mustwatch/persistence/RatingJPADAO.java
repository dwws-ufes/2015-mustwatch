package br.ufes.inf.nemo.mustwatch.persistence;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.ufes.inf.nemo.mustwatch.domain.Rating;
import br.ufes.inf.nemo.util.ejb3.persistence.BaseJPADAO;

@Stateless
public class RatingJPADAO extends BaseJPADAO<Rating> implements RatingDAO {

	/** Serialization id. */
	private static final long serialVersionUID = 1L;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public EntityManager getEntityManager(){
		return entityManager;
	}
	
	@Override
	public Class<Rating> getDomainClass() {
		// TODO Auto-generated method stub
		return Rating.class;
	}

}
