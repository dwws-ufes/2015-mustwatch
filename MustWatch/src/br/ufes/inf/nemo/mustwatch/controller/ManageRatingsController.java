package br.ufes.inf.nemo.mustwatch.controller;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import br.ufes.inf.nemo.mustwatch.application.ManageRatingsService;
import br.ufes.inf.nemo.mustwatch.domain.Rating;
import br.ufes.inf.nemo.util.ejb3.application.CrudService;
import br.ufes.inf.nemo.util.ejb3.controller.CrudController;

@Named("manageRatingsController")
@SessionScoped
public class ManageRatingsController extends CrudController<Rating> {
	
	/** Serialization id. */
	private static final long serialVersionUID = 1L;
	
	@EJB
	private ManageRatingsService manageRatingsService;
	
	@Override
	protected CrudService<Rating> getCrudService() {
		// TODO Auto-generated method stub
		return manageRatingsService;
	}

	@Override
	protected Rating createNewEntity() {
		// TODO Auto-generated method stub
		return new Rating();
	}

	@Override
	protected void initFilters() {
		// TODO Auto-generated method stub
		
	}
	
	public ManageRatingsController() {
	    viewPath = "/core/manageRatings/";
	    bundleName = "msgs";
	}

}
