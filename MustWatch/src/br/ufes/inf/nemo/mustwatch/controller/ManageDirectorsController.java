package br.ufes.inf.nemo.mustwatch.controller;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import br.ufes.inf.nemo.mustwatch.application.ManageDirectorsService;
import br.ufes.inf.nemo.mustwatch.domain.Director;
import br.ufes.inf.nemo.util.ejb3.application.CrudService;
import br.ufes.inf.nemo.util.ejb3.controller.CrudController;

@Named("manageDirectorsController")
@SessionScoped
public class ManageDirectorsController extends CrudController<Director> {

	/** Serialization id. */
	private static final long serialVersionUID = 1L;
	
	@EJB
	private ManageDirectorsService manageDirectorsService;

	@Override
	protected CrudService<Director> getCrudService() {
		// TODO Auto-generated method stub
		return manageDirectorsService;
	}

	@Override
	protected Director createNewEntity() {
		// TODO Auto-generated method stub
		return new Director();
	}
	
	public ManageDirectorsController() {
	    viewPath = "/core/managedirectors/";
	    bundleName = "msgs";
	}

	@Override
	protected void initFilters() {
		// TODO Auto-generated method stub
		
	}
}
