package br.ufes.inf.nemo.mustwatch.controller;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import br.ufes.inf.nemo.mustwatch.application.ManageDistributionCompaniesService;
import br.ufes.inf.nemo.mustwatch.domain.DistributionCompany;
import br.ufes.inf.nemo.util.ejb3.application.CrudService;
import br.ufes.inf.nemo.util.ejb3.controller.CrudController;


@Named("manageDistributionController")
@SessionScoped
public class ManageDistributionCompaniesController extends CrudController<DistributionCompany>{
	
	/** Serialization id. */
	private static final long serialVersionUID = 1L;

	@EJB
	ManageDistributionCompaniesService manageDistributionCompaniesService;

	@Override
	protected CrudService<DistributionCompany> getCrudService() {
		return manageDistributionCompaniesService;
	}

	@Override
	protected DistributionCompany createNewEntity() {
		return new DistributionCompany();
	}

	@Override
	protected void initFilters() {
		
	}


	public ManageDistributionCompaniesController(){
		viewPath = "/core/manageDistributionCompanies/";
	    bundleName = "msgs";
	}


}
