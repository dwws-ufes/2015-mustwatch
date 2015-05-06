package br.ufes.inf.nemo.mustwatch.application;


import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.ufes.inf.nemo.mustwatch.domain.Genre;
import br.ufes.inf.nemo.mustwatch.persistence.GenreDAO;
import br.ufes.inf.nemo.util.ejb3.application.CrudServiceBean;
import br.ufes.inf.nemo.util.ejb3.persistence.BaseDAO;

@Stateless
public class ManageGenresServiceBean extends CrudServiceBean<Genre> implements
		ManageGenresService {

	/** Serialization id. */
	private static final long serialVersionUID = 1L;
	
	@EJB
	private GenreDAO genreDAO;
	
	@Override
	public BaseDAO<Genre> getDAO() {
		// TODO Auto-generated method stub
		return genreDAO;
	}

	@Override
	protected Genre createNewEntity() {
		// TODO Auto-generated method stub
		return new Genre();
	}
	

}
