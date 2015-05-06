package br.ufes.inf.nemo.mustwatch.controller;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import br.ufes.inf.nemo.mustwatch.application.ManageActorsService;
import br.ufes.inf.nemo.mustwatch.domain.Actor;
import br.ufes.inf.nemo.util.ejb3.application.CrudService;
import br.ufes.inf.nemo.util.ejb3.controller.CrudController;


@Named("manageActorsController")
@SessionScoped
public class ManageActorsController extends CrudController<Actor>{
	/** Serialization id. */
	private static final long serialVersionUID = 1L;

	@EJB
	ManageActorsService manageActorService;

	@Override
	protected CrudService<Actor> getCrudService() {
		// TODO Auto-generated method stub
		return manageActorService;
	}

	@Override
	protected Actor createNewEntity() {
		// TODO Auto-generated method stub
		return new Actor();
	}

	@Override
	protected void initFilters() {
		// TODO Auto-generated method stub
		
	}


	public ManageActorsController(){
		viewPath = "/core/manageactors/";
	    bundleName = "msgs";
	}

}
