package br.ufes.inf.nemo.mustwatch.persistence;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.ufes.inf.nemo.mustwatch.domain.Director;
import br.ufes.inf.nemo.util.ejb3.persistence.BaseJPADAO;
@Stateless
public class DirectorJPADAO extends BaseJPADAO<Director> implements DirectorDAO {

	/** Serialization id. */
	private static final long serialVersionUID = 1L;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public EntityManager getEntityManager(){
		return entityManager;
	}
	
	@Override
	public Class<Director> getDomainClass() {
		// TODO Auto-generated method stub
		return Director.class;
	}
}
