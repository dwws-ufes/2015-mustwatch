package br.ufes.inf.nemo.mustwatch.controller;

import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import br.ufes.inf.nemo.mustwatch.application.ManageCountriesService;
import br.ufes.inf.nemo.mustwatch.domain.Country;
import br.ufes.inf.nemo.util.ejb3.application.CrudService;
import br.ufes.inf.nemo.util.ejb3.controller.CrudController;


@Named("manageCountriesController")
@SessionScoped
public class ManageCountriesControler extends CrudController<Country>{
	/** Serialization id. */
	private static final long serialVersionUID = 1L;
	private List<Country> allCountries;
	@EJB
	ManageCountriesService manageCountriesService;

	public void init() {
		allCountries = manageCountriesService.getDAO().retrieveAll();
    }
	
	@Override
	protected CrudService<Country> getCrudService() {
		// TODO Auto-generated method stub
		return manageCountriesService;
	}

	@Override
	protected Country createNewEntity() {
		// TODO Auto-generated method stub
		return new Country();
	}

	@Override
	protected void initFilters() {
		// TODO Auto-generated method stub
		
	}
	
	

	public ManageCountriesControler(){
		viewPath = "/core/managecountries/";
	    bundleName = "msgs";
	}

	public List<Country> getAllCountries() {
		return allCountries;
	}

	public void setAllCountries(List<Country> allCountries) {
		this.allCountries = allCountries;
	}

}
