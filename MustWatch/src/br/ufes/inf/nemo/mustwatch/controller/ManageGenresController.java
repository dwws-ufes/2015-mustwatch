package br.ufes.inf.nemo.mustwatch.controller;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import br.ufes.inf.nemo.mustwatch.application.ManageGenresService;
import br.ufes.inf.nemo.mustwatch.domain.Genre;
import br.ufes.inf.nemo.util.ejb3.application.CrudService;
import br.ufes.inf.nemo.util.ejb3.controller.CrudController;

@Named("manageGenresController")
@SessionScoped
public class ManageGenresController extends CrudController<Genre> {

	/** Serialization id. */
	private static final long serialVersionUID = 1L;

	@EJB
	ManageGenresService manageGenreService;

	@Override
	protected CrudService<Genre> getCrudService() {
		// TODO Auto-generated method stub
		return manageGenreService;
	}

	@Override
	protected Genre createNewEntity() {
		// TODO Auto-generated method stub
		return new Genre();
	}

	@Override
	protected void initFilters() {
		// TODO Auto-generated method stub
		
	}


	public ManageGenresController(){
		viewPath = "/core/managegenres/";
	    bundleName = "msgs";
	}

}
