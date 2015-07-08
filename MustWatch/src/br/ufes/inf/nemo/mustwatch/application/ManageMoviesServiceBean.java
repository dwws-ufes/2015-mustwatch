package br.ufes.inf.nemo.mustwatch.application;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.ufes.inf.nemo.mustwatch.domain.Director;
import br.ufes.inf.nemo.mustwatch.domain.Movie;
import br.ufes.inf.nemo.mustwatch.domain.Rating;
import br.ufes.inf.nemo.mustwatch.persistence.DirectorDAO;
import br.ufes.inf.nemo.mustwatch.persistence.MovieDAO;
import br.ufes.inf.nemo.mustwatch.persistence.RatingDAO;
import br.ufes.inf.nemo.util.ejb3.application.CrudServiceBean;
import br.ufes.inf.nemo.util.ejb3.persistence.BaseDAO;

@Stateless
public class ManageMoviesServiceBean extends CrudServiceBean<Movie> implements ManageMoviesService{
	/** Serialization id. */
	private static final long serialVersionUID = 1L;

	@EJB
	private MovieDAO movieDAO;
	
	@EJB
	private RatingDAO ratingDAO;
	
	@EJB
	private DirectorDAO directorDAO;

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
	
	@Override
	public void doRateService(long id, int value, String comment){
 
		Movie movie = movieDAO.retrieveById(id);
		Rating rat= new Rating();
		rat.setValue(value);
		rat.setComment(comment);
		rat.setMovie(movie);
		ratingDAO.save(rat);
	}
	
	
	
}
