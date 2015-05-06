package br.ufes.inf.nemo.mustwatch.persistence;


import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.ufes.inf.nemo.mustwatch.domain.DistributionCompany;
import br.ufes.inf.nemo.util.ejb3.persistence.BaseJPADAO;

@Stateless
public class DistributionCompanyJPADAO extends BaseJPADAO<DistributionCompany> implements DistributionCompanyDAO {
	
	/** Serialization id. */
	private static final long serialVersionUID = 1L;

	@PersistenceContext
	private EntityManager entityManager;
	
	public EntityManager getEntityManager(){
		return entityManager;
	}

	@Override
	public Class<DistributionCompany> getDomainClass() {
		return DistributionCompany.class;
	}

}
