package br.ufes.inf.nemo.mustwatch.application;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.ufes.inf.nemo.mustwatch.domain.Movie;
import br.ufes.inf.nemo.mustwatch.persistence.MovieDAO;
import br.ufes.inf.nemo.util.ejb3.application.CrudServiceBean;
import br.ufes.inf.nemo.util.ejb3.persistence.BaseDAO;

@Stateless
public class ManageMoviesServiceBean extends CrudServiceBean<Movie> implements ManageMoviesService{
	/** Serialization id. */
	private static final long serialVersionUID = 1L;

	@EJB
	private MovieDAO movieDAO;

	@Override
	public BaseDAO<Movie> getDAO() {
		// TODO Auto-generated method stub
		return movieDAO;
	}

	@Override
	protected Movie createNewEntity() {
		// TODO Auto-generated method stub
		return new Movie();
	}
}
