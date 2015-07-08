package br.ufes.inf.nemo.mustwatch.application;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.ufes.inf.nemo.mustwatch.domain.Country;
import br.ufes.inf.nemo.mustwatch.persistence.CountryDAO;
import br.ufes.inf.nemo.util.ejb3.application.CrudServiceBean;
import br.ufes.inf.nemo.util.ejb3.persistence.BaseDAO;

@Stateless
public class ManageCountriesServiceBean extends CrudServiceBean<Country> implements ManageCountriesService {

	/** Serialization id. */
	private static final long serialVersionUID = 1L;
	
	@EJB
	private CountryDAO countryDAO;
	
	@Override
	public BaseDAO<Country> getDAO() {
		// TODO Auto-generated method stub
		return countryDAO;
	}

	@Override
	protected Country createNewEntity() {
		// TODO Auto-generated method stub
		return new Country();
	}
	
	@Override
	public void saveTome(Country country){
		countryDAO.save(country);
	}

}
