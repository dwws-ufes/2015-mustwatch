package br.ufes.inf.nemo.mustwatch.application;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.ufes.inf.nemo.mustwatch.domain.DistributionCompany;
import br.ufes.inf.nemo.mustwatch.persistence.DistributionCompanyDAO;
import br.ufes.inf.nemo.util.ejb3.application.CrudServiceBean;
import br.ufes.inf.nemo.util.ejb3.persistence.BaseDAO;


@Stateless
public class ManageDistributionCompaniesServiceBean extends CrudServiceBean<DistributionCompany> implements ManageDistributionCompaniesService {

	/** Serialization id. */
	private static final long serialVersionUID = 1L;
	
	@EJB
	private DistributionCompanyDAO distributionCompanyDAO;
	
	@Override
	public BaseDAO<DistributionCompany> getDAO() {
		return distributionCompanyDAO;
	}

	@Override
	protected DistributionCompany createNewEntity() {
		return new DistributionCompany();
	}


}
